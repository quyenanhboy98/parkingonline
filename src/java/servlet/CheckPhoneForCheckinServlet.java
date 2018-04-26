/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.BookingDTO;
import entity.EmployeeDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookingDAO;
import model.UserDetailsDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "CheckPhoneForCheckinServlet", urlPatterns = {"/CheckPhoneForCheckinServlet"})
public class CheckPhoneForCheckinServlet extends HttpServlet {

    private static final String page = "checkin.jsp";

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
        String url = page;

        try {
            if (phone == null) {
                request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại");
                return;
            }

            if (!phone.matches("^0\\d{9,10}$")) {
                request.setAttribute("ERROR", "SĐT không đúng định dạng.");
                return;
            }

            boolean validPhoneIsUser = validPhoneIsUser(phone);
            if (validPhoneIsUser) {
                BookingDTO booking = getCurrentBooking(phone);
                if (booking != null) {
                    HttpSession session = request.getSession();
                    EmployeeDTO emp = (EmployeeDTO) session.getAttribute("USERDTO");
                    if (booking.getParkID().equals(emp.getParkID())) {
                        request.setAttribute("BOOKING", booking);
                    } else {
                        request.setAttribute("ERROR", "Tài khoản đang đặt chỗ ở bãi xe khác.");
                    }
                } else {
                    request.setAttribute("NOTBOOKING", "Tài khoản hiện chưa đặt chỗ ở bãi xe này.");
                }
            } else {
                request.setAttribute("ERROR", "Không tìm thấy tài khoản này.");
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

    private boolean validPhoneIsUser(String phone) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.validPhoneIsUser(phone);
    }

    private BookingDTO getCurrentBooking(String phone) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.getCurrentBooking(phone);
    }
}
