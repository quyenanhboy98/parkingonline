/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ForgotPasswordDAO;
import model.UserDetailsDAO;
import utils.Encrypt;
import utils.Mailer;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/ForgotPasswordServlet"})
public class ForgotPasswordServlet extends HttpServlet {

    private static String page = "recoverypassword.jsp";
    private static final String TOKENPARAM = "RecoveryPassword?token=";
    private static final String APPDOMAIN = "http://localhost:8084/ParkingOnline/";

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
            if (phone == null) return;
            
            String email = getUserEmail(phone);
            if (email != null) {
                String token = Encrypt.generateToken(phone, email);
                String link = APPDOMAIN + TOKENPARAM + token;
                if (storeToken(token)){
                    request.setAttribute("RECOVERYSTATUS", "Vui lòng kiểm tra email. Link khôi phục mật khẩu đã được gửi.");
                    Mailer.sendRecoveryMail(email, link);
                } else {
                    request.setAttribute("RECOVERYSTATUS", "Có lỗi xảy ra. Vui lòng thử lại.");
                }
            } else {
                request.setAttribute("RECOVERYSTATUS", "SĐT chưa được đăng ký.");
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

    private String getUserEmail(String phone) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.getUserEmail(phone);
    }
    
    private boolean storeToken(String token) throws SQLException, ClassNotFoundException {
        ForgotPasswordDAO dao = new ForgotPasswordDAO();
        return dao.addToken(token);
    }
}
