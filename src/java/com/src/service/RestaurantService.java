/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.src.service;

import com.src.domain.Account;
import com.src.domain.Reservation;

/**
 *
 * @author emmanuel
 */
public interface RestaurantService {
    public boolean isValidAccount(String email);
    public Account signin(String email, String passworld);
    public Account signup(Account acct);
    public boolean signout();
    public Reservation findTableInformation(int tid);
    public Reservation reserveTable(Reservation res);
}
