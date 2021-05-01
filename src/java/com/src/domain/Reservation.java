/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.src.domain;

import com.src.domain.config.LocalDateConf;
import java.time.LocalDate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author emmanuel
 */
@Entity
public class Reservation {
    @Id
    private int tid;
    private String names;
    private String email;
    private String phone;
    private double amount;
    private boolean reserved;
    
    @Convert(converter = LocalDateConf.class)
    private LocalDate dates;
    private String time;
    private int peaple;
    private String message;

    public Reservation() {
    }

    public Reservation(int tid, String names, String email, String phone,LocalDate dates, String time, int peaple, String message) {
        this.tid = tid;
        this.names = names;
        this.email = email;
        this.phone = phone;
        this.dates = dates;
        this.time = time;
        this.peaple = peaple;
        this.message = message;
    }
    
    public Reservation(int tid, String names, String email, String phone, double amount, boolean reserved, LocalDate dates, String time, int peaple, String message) {
        this.tid = tid;
        this.names = names;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.reserved = reserved;
        this.dates = dates;
        this.time = time;
        this.peaple = peaple;
        this.message = message;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public LocalDate getDates() {
        return dates;
    }

    public void setDates(LocalDate dates) {
        this.dates = dates;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPeaple() {
        return peaple;
    }

    public void setPeaple(int peaple) {
        this.peaple = peaple;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
