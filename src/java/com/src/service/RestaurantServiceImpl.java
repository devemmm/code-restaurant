/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.src.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.src.dao.GeneralDao;
import com.src.domain.Account;
import com.src.domain.Reservation;
import com.src.helps.AccountError;

/**
 *
 * @author emmanuel
 */
public class RestaurantServiceImpl implements RestaurantService {

    Account account = new Account();
    AccountError accountError = new AccountError();
    GeneralDao<Account> accountDao = new GeneralDao<>(Account.class);
    Reservation reservation = new Reservation();
    GeneralDao<Reservation> reservationDao = new GeneralDao<>(Reservation.class);

    //   START OF Hash Password PROCCESS
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        byte[] hash = digest.digest(password.getBytes());
        return bytesToStringHex(hash);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToStringHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    // END OF HASHING PROCCESS
    @Override
    public boolean isValidAccount(String email) {
        return accountDao.findBySTRING_PK(email) == null ? false : true;
    }

    @Override
    public Account signin(String email, String passworld) {
        if (!isValidAccount(email)) {
            throw new RuntimeException("Account not fount !");
        }
        try {
            account = accountDao.findBySTRING_PK(email);
            if (account.getPassword().toString().equals(hashPassword(passworld))) {
                return account;
            }
            throw new RuntimeException("Wrong Password");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Account signup(Account acct) {
        if (isValidAccount(acct.getEmail())) {
            throw new RuntimeException("Account already exist !");
        }

        try {
            acct.setPassword(hashPassword(acct.getPassword()));
            return accountDao.create(acct);
        } catch (Exception e) {
            throw new RuntimeException("Internal server error :" + e.getMessage());
        }
    }

    @Override
    public boolean signout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //PROCESS OF TABLE Validation
    private boolean isValidTable(int tid) {
        return reservationDao.findByINT_PK(tid) == null ? false : true;
    }

    @Override
    public Reservation findTableInformation(int tid) {
        return reservationDao.findByINT_PK(tid);
    }

    // END OF TABLE VALIDATION
    @Override
    public Reservation reserveTable(Reservation res) {
        if (!isValidAccount(res.getEmail())) {
            throw new RuntimeException("Account not exist");
        }
        try {
            Reservation table = findTableInformation(res.getTid());
            if (table == null) {
                throw new RuntimeException("Table Not Found");
            }
            if (table.isReserved()) {
                throw new RuntimeException("Table reserved Try Others");
            }
            res.setReserved(true);
            return reservationDao.update(res);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
