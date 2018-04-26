/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.EmployeeDTO;
import entity.UserDetailsDTO;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Validate;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "GetDetailsUserServlet", urlPatterns = {"/GetDetailsUserServlet"})
public class GetDetailsUserServlet extends HttpServlet {

    private final String page = "GetListWorkingTimeForEmployeeServlet";
    private final String userPage = "userdetails.jsp";

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
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String gender = request.getParameter("sex");
        String date = request.getParameter("dob");
        String parkID = request.getParameter("parkID");
        String parkName = request.getParameter("parkName");
        String timeStartStr = request.getParameter("timeStart");
        String timeEndStr = request.getParameter("timeEnd");
        String roleStr = request.getParameter("role");
        String pointStr = request.getParameter("point");
        String parkIDStaff = request.getParameter("parkIDStaff");
        String url = page;

        if (email == null || phone == null || fullname == null || address == null || gender == null || date == null) {
            return;
        }

        boolean sex = gender.equals("true");

        Time timeStart = null;
        Time timeEnd = null;
        if (timeStartStr != null && timeEndStr != null
                && !timeStartStr.isEmpty() && !timeEndStr.isEmpty()) {
            timeStart = Time.valueOf(timeStartStr.trim());
            timeEnd = Time.valueOf(timeEndStr.trim());
        }

        int role = 0;
        if (roleStr != null && !roleStr.isEmpty()) {
            role = Integer.parseInt(roleStr);
        }

        int point = 0;
        if (pointStr != null && !pointStr.isEmpty()) {
            point = Integer.parseInt(pointStr.trim());

        }

        Date dob = Validate.validDate(date);
        if (dob == null) {
            return;
        }
        if (parkIDStaff == null) {
            url = userPage;
            UserDetailsDTO user = new UserDetailsDTO(phone, fullname, email, address, sex, dob);
            user.setRole(role);
            user.setPoint(point);
            request.setAttribute("PROFILEUSER", user);
        } else {
            EmployeeDTO emp = new EmployeeDTO(phone, fullname, email, address, sex, dob, parkID, parkName, timeStart, timeEnd);
            request.setAttribute("PROFILEUSER", emp);
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
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
