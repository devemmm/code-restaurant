/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.src.model;

import com.src.domain.Account;
import com.src.service.RestaurantService;
import com.src.service.RestaurantServiceImpl;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author emmanuel
 */
public class AuthModel extends HttpServlet {

    private String inEmail = "";
    private String inPassword = "";

    private String emailMsg = "";
    private String passwordMsg = "";

    // Signup VALUE
    private String inFname = "";
    private String inLname = "";
    private String inPhone = "";
    private String inPassword2 = "";

    private String fnameMsg = "";
    private String lnameMsg = "";
    private String phoneMsg = "";
    private String password2Msg = "";

    //COMON
    private String action = "Signin";
    private boolean error = false;
    private String sysMsg = "";

    RestaurantService restaurantService = new RestaurantServiceImpl();
    Account account = new Account();

    public AuthModel() {
    }

    public boolean applyRequestValue(HttpServletRequest request) {
        action = request.getParameter("auth");

        switch (action) {
            case "Signin":
                inEmail = request.getParameter("email");
                inPassword = request.getParameter("password");
                break;
            case "Signup":
                inEmail = request.getParameter("email");
                inFname = request.getParameter("fname");
                inLname = request.getParameter("lname");
                inPhone = request.getParameter("phone");
                inPassword = request.getParameter("password");
                inPassword2 = request.getParameter("password2");
                break;
            default:
                break;
        }

        return error;
    }

    public boolean processValidation() {

        switch (action) {
            case "Signin":
                if (inEmail.length() < 6) {
                    emailMsg = "Email Minimum length Should be 6 in length";
                    error = true;
                }
                break;
            case "Signup":
                if (!inPassword.equals(inPassword2)) {
                    password2Msg = "Password Does Not Mutch";
                    error = true;
                }
                break;
            default:
                break;
        }

        return error;
    }

    public void appllyModelView() {
        if (action.toString().equals("Signup")) {
            account = new Account(inEmail, inFname, inLname, inPhone, inPassword);
        }
    }

    public boolean involkeApplication(HttpServletRequest request) {
        switch (action) {
            case "Signin":
                try {
                    Account retAccount = restaurantService.signin(inEmail, inPassword);
                    HttpSession session = request.getSession(true);

                    Account account = (Account) session.getAttribute("AccountData");
                    account = retAccount;
                    session.setAttribute("AccountData", retAccount);
                } catch (Exception e) {
                    sysMsg = e.getMessage();
                    error = true;
                }
                break;
            case "Signup":
                try {
                    account = new Account(inEmail, inFname, inLname, inPhone, inPassword);
                    restaurantService.signup(account);
                } catch (Exception e) {
                    sysMsg = e.getMessage();
                    error = true;
                }
                break;
            default:
                break;
        }
        return error;
    }

    public String getInEmail() {
        return inEmail;
    }

    public void setInEmail(String inEmail) {
        this.inEmail = inEmail;
    }

    public String getInPassword() {
        return inPassword;
    }

    public void setInPassword(String inPassword) {
        this.inPassword = inPassword;
    }

    public String getEmailMsg() {
        return emailMsg;
    }

    public void setEmailMsg(String emailMsg) {
        this.emailMsg = emailMsg;
    }

    public String getPasswordMsg() {
        return passwordMsg;
    }

    public void setPasswordMsg(String passwordMsg) {
        this.passwordMsg = passwordMsg;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getSysMsg() {
        return sysMsg;
    }

    public void setSysMsg(String sysMsg) {
        this.sysMsg = sysMsg;
    }

    public String getInFname() {
        return inFname;
    }

    public void setInFname(String inFname) {
        this.inFname = inFname;
    }

    public String getInLname() {
        return inLname;
    }

    public void setInLname(String inLname) {
        this.inLname = inLname;
    }

    public String getInPhone() {
        return inPhone;
    }

    public void setInPhone(String inPhone) {
        this.inPhone = inPhone;
    }

    public String getInPassword2() {
        return inPassword2;
    }

    public void setInPassword2(String inPassword2) {
        this.inPassword2 = inPassword2;
    }

    public String getFnameMsg() {
        return fnameMsg;
    }

    public void setFnameMsg(String fnameMsg) {
        this.fnameMsg = fnameMsg;
    }

    public String getLnameMsg() {
        return lnameMsg;
    }

    public void setLnameMsg(String lnameMsg) {
        this.lnameMsg = lnameMsg;
    }

    public String getPhoneMsg() {
        return phoneMsg;
    }

    public void setPhoneMsg(String phoneMsg) {
        this.phoneMsg = phoneMsg;
    }

    public String getPassword2Msg() {
        return password2Msg;
    }

    public void setPassword2Msg(String password2Msg) {
        this.password2Msg = password2Msg;
    }
}
