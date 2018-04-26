/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserDetailsDAO;
import utils.Mailer;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "ChangeActiveUserServlet", urlPatterns = {"/ChangeActiveUserServlet"})
public class ChangeActiveUserServlet extends HttpServlet {

    private static String page = "SearchUserServlet";

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
        String phone = request.getParameter("phone");
        String active = request.getParameter("active");
        String url = page;
        try {
            if (phone != null && !phone.isEmpty() && active != null && !active.isEmpty()) {
                boolean status = !active.equals("true");
                if (changeActive(phone, status)){
                    String email = getUserEmail(phone);
                    if (status){
                        Mailer.sendUnlockedUserMail(email, phone);
                    } else {
                        Mailer.sendLockedUserMail(email, phone);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            log(ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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

    private boolean changeActive(String phone, boolean active) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.changeActive(phone, active);
    }
    
    private String getUserEmail(String phone) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.getUserEmail(phone);
    }
}
