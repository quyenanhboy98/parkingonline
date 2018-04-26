/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;
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
@WebServlet(name = "RecoveryPasswordServlet", urlPatterns = {"/RecoveryPasswordServlet"})
public class RecoveryPasswordServlet extends HttpServlet {

    private static String page = "login.jsp";

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
        String token = request.getParameter("token");
        String url = page;
        try {
            if (token != null && checkToken(token) != null) {
                String[] encodeToken = Encrypt.verifyToken(token);
                if (encodeToken == null) {
                    removeToken(token);
                    request.setAttribute("LOGINSTATUS", "Link khôi phục mật khẩu đã hết hạn. Vui lòng thử lại.");
                } else {
                    String phone = encodeToken[0];
                    String mail = encodeToken[1];
                    String newPwd = UUID.randomUUID().toString().replace("-", "").substring(0,8);
                    if (changePass(phone, Encrypt.EncryptPwd(newPwd))) {
                        removeToken(token);
                        Mailer.sendNewPassMail(mail, newPwd);
                        request.setAttribute("LOGINSTATUS", "Vui lòng kiểm tra email. Mật khẩu mới đã được gửi.");
                    } else {
                        removeToken(token);
                        request.setAttribute("LOGINSTATUS", "Có lỗi xảy ra. Vui lòng thử lại.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
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

    private String checkToken(String token) throws SQLException, ClassNotFoundException {
        ForgotPasswordDAO dao = new ForgotPasswordDAO();
        return dao.checkToken(token);
    }

    private boolean removeToken(String token) throws SQLException, ClassNotFoundException {
        ForgotPasswordDAO dao = new ForgotPasswordDAO();
        return dao.removeToken(token);
    }

    private boolean changePass(String phone, String pass) throws ClassNotFoundException, SQLException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.changePass(phone, pass);
    }

}
