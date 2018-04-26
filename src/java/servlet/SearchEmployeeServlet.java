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
import javax.servlet.http.HttpSession;
import model.EmployeeDAO;

/**
 *
 * @author junge
 */
@WebServlet(name = "SearchEmployeeServlet", urlPatterns = {"/SearchEmployeeServlet"})
public class SearchEmployeeServlet extends HttpServlet {

    private final String page = "GetListParkForEmployeeServlet";

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
        String value = request.getParameter("searchValue");
        String srcPage = request.getParameter("srcPage");

        List<EmployeeDTO> listEmployee = null;
        String url = page;
        try {

            if (parkID == null || value == null || srcPage == null) {
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("SELECTEDPARK", parkID);

            listEmployee = searchListEmployee(value, parkID);

        } catch (SQLException | ClassNotFoundException e) {
            log(e.getMessage());
        } finally {
            request.setAttribute("LISTEMPLOYEE", listEmployee);
            if (listEmployee == null) {
                request.setAttribute("LISTEMPTY", "Không tìm thấy nhân viên theo kết quả tìm kiếm.");
            }
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

    private List<EmployeeDTO> searchListEmployee(String value, String parkID) throws SQLException, ClassNotFoundException {
        EmployeeDAO dao = new EmployeeDAO();
        return dao.searchListEmployee(value, parkID);
    }

    private List<EmployeeDTO> searchListStaff(String value, String parkID) throws SQLException, ClassNotFoundException {
        EmployeeDAO dao = new EmployeeDAO();
        return dao.searchListStaff(value, parkID);
    }
}
