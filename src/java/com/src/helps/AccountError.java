/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.src.helps;

/**
 *
 * @author emmanuel
 */
public class AccountError {
    private String emailError = "";
    private String PasswordError= "";
    private String generalError = "";

    public AccountError() {
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return PasswordError;
    }

    public void setPasswordError(String PasswordError) {
        this.PasswordError = PasswordError;
    }

    public String getGeneralError() {
        return generalError;
    }

    public void setGeneralError(String generalError) {
        this.generalError = generalError;
    }
}
