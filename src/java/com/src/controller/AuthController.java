/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.src.controller;

import com.src.model.AuthModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author emmanuel
 */
@WebServlet(name = "AuthController", urlPatterns = {"/authController.do"})
public class AuthController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            RequestDispatcher signinPage = request.getRequestDispatcher("signin.jsp");
            RequestDispatcher signupPage = request.getRequestDispatcher("signup.jsp");
            RequestDispatcher indexPage = request.getRequestDispatcher("index.jsp");

            AuthModel authModel = (AuthModel) request.getAttribute("authMode");

            if (authModel == null) {
                authModel = new AuthModel();
                request.setAttribute("authMode", authModel);
            }

            switch (request.getParameter("auth")) {
                case "Signin":
                    if (authModel.applyRequestValue(request)) {
                        signinPage.forward(request, response);
                    } else if (authModel.processValidation()) {
                        signinPage.forward(request, response);
                    } else if (authModel.involkeApplication(request)) {
                        signinPage.forward(request, response);
                    } else {
                        indexPage.forward(request, response);
                    }
                    break;
                case "Signup":
                    if (authModel.applyRequestValue(request)) {
                        signupPage.forward(request, response);
                    } else if (authModel.processValidation()) {
                        signupPage.forward(request, response);
                    } else if (authModel.involkeApplication(request)) {
                        signupPage.forward(request, response);
                    } else {
                        signinPage.forward(request, response);
                    }
                    break;
                default:
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
