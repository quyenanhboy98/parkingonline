/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.BillDTO;
import entity.BookingDTO;
import entity.UserDetailsDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BillDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "SearchListBillsOfUserServlet", urlPatterns = {"/SearchListBillsOfUserServlet"})
public class SearchListBillsOfUserServlet extends HttpServlet {

    private static final String page = "listuserbill.jsp";

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

        String url = page;
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        try {
            if (fromDate == null || toDate == null || fromDate.isEmpty() || toDate.isEmpty()) {
                return;
            }

            Timestamp from = Timestamp.valueOf(fromDate + " 00:00:00");
            Timestamp to = Timestamp.valueOf(toDate + " 23:59:59");

            List<BookingDTO> listBooking = new ArrayList<>();
            List<BillDTO> listBill = new ArrayList<>();
            HttpSession session = request.getSession();
            UserDetailsDTO user = (UserDetailsDTO) session.getAttribute("USERDTO");
            String phone = user.getPhone();

            searchListBillsOfUser(phone, listBooking, listBill, from, to);
            if (!listBooking.isEmpty() && !listBill.isEmpty()) {
                request.setAttribute("LISTBOOKING", listBooking);
                request.setAttribute("LISTBILL", listBill);
            } else {
                request.setAttribute("LISTEMPTY", "Không tìm thấy kết quả dựa trên lựa chọn tìm kiếm.");
            }
        } catch (ClassNotFoundException | SQLException e) {

            log(e.getMessage());
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

    public void searchListBillsOfUser(String phone, List<BookingDTO> listBooking, List<BillDTO> listBill, Timestamp checkinTime, Timestamp checkoutTime) throws SQLException, ClassNotFoundException {
        BillDAO dao = new BillDAO();
        dao.searchListBillsOfUser(phone, listBooking, listBill, checkinTime, checkoutTime);
    }
}
