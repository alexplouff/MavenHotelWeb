/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Hotel;
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

//        String login = request.getParameter("loginID");
//        HttpSession session = request.getSession();
//        ServletContext loginID = request.getServletContext();
//        if (((String) login) == null || ((String) login).length() < 2) {
//            response.sendRedirect("errorPage.html");
//        } else {
//            session.setAttribute("login", login);
//            RequestDispatcher view = request.getRequestDispatcher(response.encodeURL("hc"));
//            view.forward(request, response);
//        }
        
        HotelService hs = new HotelService(new HotelDAO());
        List<Hotel> list = null;
        
        // View All
        if (list == null) {
            try {
                list = new ArrayList<>(hs.findAllHotels());
                request.setAttribute("list", list);
            } catch (Exception ex) {
                ex.getLocalizedMessage();
            }
        }
        // Delete
        String enteredBtn = request.getParameter("id");
        if (enteredBtn != null) {
            try {
                hs.deleteRecord(enteredBtn);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }

        // Edit
        String column = request.getParameter("column");
        String primaryKey = request.getParameter("primary_key");
        String value = request.getParameter("value");
        if (column != null || primaryKey != null || value != null) {
            try {
                hs.editRecord(column, value, primaryKey);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }

        // Create
        String name = request.getParameter("hotel_name");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        if (name != null || street != null || city != null || state != null) {
            try {
                hs.createRecord(name, street, city, state);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }

        //Search Wizard
        String hotelName = request.getParameter("searchByName");
        if (hotelName != null) {
            try {
                list = new ArrayList<>(hs.getHotelsByName(hotelName));
                request.setAttribute("list", list);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }

        String hotelCity = request.getParameter("searchByCtiy");
        if (hotelCity != null) {
            try {
                list = new ArrayList<>(hs.getHotelsByCity(hotelCity));
                request.setAttribute("list", list);
            } catch (Exception e){
                e.getLocalizedMessage();
            }
        }    
        
        String hotelState = request.getParameter("searchByState");
        if(hotelState != null){
            try{
                list = new ArrayList<>(hs.getHotelsByState(hotelState));
                request.setAttribute("list", list);
            } catch (Exception e){
                e.getLocalizedMessage();
            }
        }
        
        RequestDispatcher view
                = request.getRequestDispatcher(response.encodeURL(RESULT_PAGE));
        view.forward(request, response);
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
