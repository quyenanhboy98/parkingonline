/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.EmployeeDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EmployeeDAO;
import model.ParkDAO;
import utils.Mailer;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "ChangeActiveParkServlet", urlPatterns = {"/ChangeActiveParkServlet"})
public class ChangeActiveParkServlet extends HttpServlet {

    private static String page = "SearchParkServlet";

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
        String parkID = request.getParameter("parkID");
        String active = request.getParameter("active");
        String url = page;
        try {
            if (parkID != null && !parkID.isEmpty() && active != null && !active.isEmpty()) {
                boolean status = !active.equals("true");
                if (changeStatus(parkID, status)) {
                    List<EmployeeDTO> list = getListEmployee(parkID);
                    if (status) {
                        for (EmployeeDTO dto : list) {
                            Mailer.sendUnlockedParkMail(dto.getEmail(), dto.getParkID(), dto.getParkName());
                        }
                    } else {
                        for (EmployeeDTO dto : list) {
                            Mailer.sendLockedParkMail(dto.getEmail(), dto.getParkID(), dto.getParkName());
                        }
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

    private boolean changeStatus(String parkID, boolean active) throws SQLException, ClassNotFoundException {
        ParkDAO dao = new ParkDAO();
        return dao.changeActive(parkID, active);
    }

    private List<EmployeeDTO> getListEmployee(String parkID) throws SQLException, ClassNotFoundException {
        EmployeeDAO dao = new EmployeeDAO();
        return dao.getListEmployee(parkID);
    }
}
