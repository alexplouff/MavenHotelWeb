/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.HotelDAO;
import models.HotelService;

/**
 *
 * @author Alex
 */
@WebServlet(name = "CRUDController", urlPatterns = {"/crud"})
public class CRUDController extends HttpServlet {

    private static final String RESULT_PAGE = "view_records.jsp";

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

        Object login = request.getParameter("loginID");
        HttpSession session = request.getSession();
        ServletContext loginID = request.getServletContext();

//        if (((String) login) == null || ((String) login).length() < 2) {
//            response.sendRedirect("errorPage.html");
//        } else {
//            session.setAttribute("login", login);
//            RequestDispatcher view = request.getRequestDispatcher(response.encodeURL("hc"));
//            view.forward(request, response);
//        }
        HotelService hs = new HotelService(new HotelDAO());

        // View All
        try {
            List list = hs.findAllHotels();
            request.setAttribute("list", list);
        } catch (Exception ex) {
            ex.getLocalizedMessage();
        }

        // Delete
        try {
            String enteredBtn = request.getParameter("id");
            hs.deleteRecord(enteredBtn);
        } catch (Exception e) {

        }

        // edit
        try {
            String column = request.getParameter("column");
            String primaryKey = request.getParameter("primary_key");
            String value = request.getParameter("value");

            hs.editRecord(column, value, primaryKey);

        } catch (Exception e) {

        }

        // create
        try {
            String hotelName = request.getParameter("hotel_name");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            
                RequestDispatcher view
                = request.getRequestDispatcher(response.encodeURL(RESULT_PAGE));
                
            if (hotelName == null) {
                view.forward(request, response);
        
            } else {
                hs.createRecord(hotelName, street, city, state);
                view.forward(request, response);
            }

        } catch (Exception ex) {
            ex.getLocalizedMessage();
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
