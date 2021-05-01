<%-- 
    Document   : reserve
    Created on : Mar 24, 2021, 10:12:25 PM
    Author     : emmanuel
--%>

<%@page import="com.src.helps.ReservationError"%>
<%@page import="com.src.domain.Reservation"%>
<%@page import="com.src.service.RestaurantServiceImpl"%>
<%@page import="com.src.service.RestaurantService"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int tid = Integer.parseInt(request.getParameter("tid"));
    String names = request.getParameter("names");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    LocalDate dates = LocalDate.parse(request.getParameter("dates"));
    String time = request.getParameter("time");
    int peaple = Integer.parseInt(request.getParameter("people"));
    String message = request.getParameter("message");
    
    ReservationError reservationErrorData = (ReservationError)session.getAttribute("reservationErrorData");
    if(reservationErrorData == null){
        reservationErrorData = new ReservationError();
        session.setAttribute("reservationErrorData", reservationErrorData);
    }
    
    try{
        RestaurantService restaurantService = new RestaurantServiceImpl();
        Reservation  reservation = new Reservation(tid, names, email, phone, dates, time, peaple, message);
        
        Reservation retTable =  restaurantService.reserveTable(reservation);
        
        if(retTable == null){
            reservationErrorData.setReservationError("Table Not Found");
            %>
                <jsp:forward page="index.jsp"/>
            <%
            return;
        }
        reservationErrorData.setReservationError("Thannk you For Booking this table");
        %>
           <jsp:forward page="index.jsp"/>
        <%
    }catch(Exception e){
        reservationErrorData.setReservationError(e.getMessage());
        %>
           <jsp:forward page="index.jsp"/>
        <%
    }
    
%>

<%--<jsp:forward page="index.jsp"/>--%>
