/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.WorkingTimeDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WorkingTimeDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "AddWorkingTimeServlet", urlPatterns = {"/AddWorkingTimeServlet"})
public class AddWorkingTimeServlet extends HttpServlet {

    private final String page = "GetListWorkingScheduleServlet";

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
        String workingTimeID = request.getParameter("workingTimeID");
        String timeStartStr = request.getParameter("timeStart");
        String timeEndStr = request.getParameter("timeEnd");

        String url = page;
        boolean isError = false;

        try {
            if (workingTimeID == null || parkID == null || timeStartStr == null || timeEndStr == null) {
                isError = true;
                request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại.");

                return;
            }
            if (workingTimeID.trim().isEmpty()) {
                request.setAttribute("WORKINGTIMEEMPTY", "Vui lòng bấm Tạo mới trước khi thêm.");
                isError = true;
            }
            Time timeStart = null;
            Time timeEnd = null;
            try {
                timeStart = Time.valueOf(LocalTime.parse(timeStartStr.trim()));
            } catch (Exception ex) {
                isError = true;
                request.setAttribute("TIMESTARTERROR", "Giờ bắt đầu phải có dạng hh:mm");
            }

            try {
                timeEnd = Time.valueOf(LocalTime.parse(timeEndStr.trim()));
            } catch (Exception ex) {
                isError = true;
                request.setAttribute("TIMEENDERROR", "Giờ kết thúc phải có dạng hh:mm");
            }
            if (!isError) {
                WorkingTimeDTO workingTime = new WorkingTimeDTO(workingTimeID, parkID, timeStart, timeEnd);
                if (!addWorkingTime(workingTime)) {
                    isError = true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            isError = true;
            log(e.getMessage());
        } finally {
            if (isError) {
                request.setAttribute("WKTIDNEW", workingTimeID);
            } else {
                request.setAttribute("SUCCESS", "Ca làm việc " + workingTimeID + " đăng ký thành công.");
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

    private boolean addWorkingTime(WorkingTimeDTO workingTime) throws SQLException, ClassNotFoundException {
        WorkingTimeDAO dao = new WorkingTimeDAO();
        return dao.addWorkingTime(workingTime);
    }
}
