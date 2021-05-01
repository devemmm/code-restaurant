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

/**
 *
 * @author emmanuel
 */
public class DataInitialisation {

    public static void main(String[] args) throws Exception {
//        createAccount();
        initialiseReservationData();
    }

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
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

    public static void createAccount() {
        try {

            String email = "email@gmail.com";
            String fname = "fname";
            String lname = "lname";
            String phone = "0788596281";
            String password = "password";
            String password2 = "password";

            if (!password.toString().equals(password2)) {
                System.out.println("Wrong Password");
            }

            RestaurantService restaurantService = new RestaurantServiceImpl();
            GeneralDao<Account> acctDao = new GeneralDao<Account>(Account.class);
            Account account = new Account(email, fname, lname, phone, password);
//        System.out.println(email + " "+ fname + " "+ lname+ " " + phone + " "+password + " " + password2);

            acctDao.create(account);

            System.out.println("Account Created Successfull");

//        if(retAccount == null){
//            return;
//        }
        } catch (Exception e) {
            System.out.println("Error Message:" + e.getMessage());
        }
    }

    public static void initialiseReservationData() {
        Reservation reservation1 = new Reservation(1, null, null, null, 555000.0, false, null, null, 0, null);
        Reservation reservation2 = new Reservation(2, null, null, null, 450000.0, false, null, null, 0, null);
        Reservation reservation3 = new Reservation(3, null, null, null, 420400.0, false, null, null, 0, null);
        Reservation reservation4 = new Reservation(4, null, null, null, 400000.0, false, null, null, 0, null);
        Reservation reservation5 = new Reservation(5, null, null, null, 380000.0, false, null, null, 0, null);
        Reservation reservation6 = new Reservation(6, null, null, null, 320000.0, false, null, null, 0, null);
        Reservation reservation7 = new Reservation(7, null, null, null, 280000.0, false, null, null, 0, null);
        Reservation reservation8 = new Reservation(8, null, null, null, 220000.0, false, null, null, 0, null);
        Reservation reservation9 = new Reservation(9, null, null, null, 150000.0, false, null, null, 0, null);
        Reservation reservation10 = new Reservation(10, null, null, null, 555000.0, false, null, null, 0, null);
        
        GeneralDao<Reservation> reservationDao = new GeneralDao<>(Reservation.class);

        try {
            reservationDao.create(reservation1);
            reservationDao.create(reservation2);
            reservationDao.create(reservation3);
            reservationDao.create(reservation4);
            reservationDao.create(reservation5);
            reservationDao.create(reservation6);
            reservationDao.create(reservation7);
            reservationDao.create(reservation8);
            reservationDao.create(reservation9);
            reservationDao.create(reservation10);
        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());
        }
    }

}
