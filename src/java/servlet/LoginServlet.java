/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.EmployeeDTO;
import entity.UserDetailsDTO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EmployeeDAO;
import model.UserDetailsDAO;
import utils.Encrypt;
import utils.Time;

/**
 *
 * @author junge
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String success = "index.jsp";
    private final String user = "user.jsp";
    private final String fail = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = fail;
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        try {
            if (phone != null && password != null) {
                String pwd = Encrypt.EncryptPwd(password);
                UserDetailsDTO dto = checkLogin(phone, pwd);
                if (dto != null) {
                    if (dto.isActive()) {
                        HttpSession session = request.getSession();
                        switch (dto.getRole()) {
                            case 1:
                                session.setAttribute("USERDTO", dto);
                                url = success;
                                break;
                            case 2:
                                dto.setPoint(getUserPoint(dto.getPhone()));
                                session.setAttribute("USERDTO", dto);
                                url = user;
                                break;
                            default:
                                EmployeeDTO emp = checkActivationParkEmployee(dto);
                                if (emp != null) {
                                    if ((checkWorkingTime(emp.getPhone()) && dto.getRole() == 4) || dto.getRole() == 3) {
                                        session.setAttribute("USERDTO", emp);
                                        url = success;
                                    } else {
                                        request.setAttribute("LOGINSTATUS", "Tài khoản của bạn hiện đang không trong ca trực!");
                                    }
                                } else {
                                    request.setAttribute("LOGINSTATUS", "Tài khoản thuộc về bãi xe đã bị khoá.");
                                }
                                break;
                        }
                    } else {
                        request.setAttribute("LOGINSTATUS", "Tài khoản đã bị khoá.");
                    }
                } else {
                    request.setAttribute("LOGINSTATUS", "Sai tài khoản hoặc mật khẩu. Vui lòng thử lại.");
                }
            }
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException ex) {
            request.setAttribute("LOGINSTATUS", "Có lỗi xảy ra. Vui lòng thử lại.");
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

    private UserDetailsDTO checkLogin(String phone, String pwd) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.checkLogin(phone, pwd);
    }

    private Integer getUserPoint(String phone) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.getUserPoint(phone);
    }

    private EmployeeDTO checkActivationParkEmployee(UserDetailsDTO dto) throws SQLException, ClassNotFoundException {
        EmployeeDAO dao = new EmployeeDAO();
        return dao.checkActivationParkEmployee(dto);
    }

    private boolean checkWorkingTime(String phone) throws SQLException, ClassNotFoundException {
        EmployeeDAO dao = new EmployeeDAO();
        List<LocalTime> listTime = dao.checkEmployeeWorkingTime(phone);
        if (listTime == null) {
            return false;
        }
        return Time.compareWorkingTime(listTime);
    }
}
