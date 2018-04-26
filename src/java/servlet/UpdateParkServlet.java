/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.ParkDTO;
import entity.ParkError;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ParkDAO;
import utils.Validate;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "UpdateParkServlet", urlPatterns = {"/UpdateParkServlet"})
public class UpdateParkServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String page = "parkdetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String parkID = request.getParameter("parkID");
        String slotStr = request.getParameter("slot");
        int slot = 0;
        String priceStr = request.getParameter("price");
        int price = 0;
        String parkName = request.getParameter("parkName");
        String parkAddress = request.getParameter("parkAddress");
        String latStr = request.getParameter("lat");
        float lat = 0;
        String lngStr = request.getParameter("lng");
        float lng = 0;
        String url = page;
        ParkDTO dto = null;
        ParkError error = new ParkError();
        boolean isError = false;
        try {
            if (parkID == null || slotStr == null || parkName == null || parkAddress == null || priceStr == null
                    || latStr == null || lngStr == null) {
                isError = true;
                error.setCreateError("Thất bại. Có lỗi xảy ra. Vui lòng thử lại.");
                return;
            }

            if (parkID.isEmpty()) {
                isError = true;
                error.setParkIDIsEmtpy("ID không thể bỏ trống.");
            }
            if (parkName.isEmpty()) {
                isError = true;
                error.setParkNameIsEmpty("Tên không thể bỏ trống.");
            }
            if (parkAddress.isEmpty()) {
                isError = true;
                error.setParkAddressIsEmpty("Địa chỉ không thế bỏ trống.");
            }

            if (parkName.contains("<") || parkName.contains(">")) {
                isError = true;
                error.setParkNameIsWrongFormat("Tên bãi xe sai định dạng.");
            }

            if (parkID.contains("<") || parkID.contains(">")) {
                isError = true;
                error.setParkIDIsWrongFormat("Mã bãi xe sai định dạng.");
            }

            if (parkAddress.contains("<") || parkAddress.contains(">")) {
                isError = true;
                error.setParkAddressIsWrongFormat("Địa chỉ bãi xe sai định dạng.");
            }

            if (slotStr.isEmpty() || !slotStr.matches("^\\d+$")) {
                isError = true;
                error.setParkSpaceIsWrongFormat("Số chỗ đậu phải là số nguyên dương.");
            } else {
                slot = Integer.parseInt(slotStr);
                if (slot <= 0) {
                    isError = true;
                    error.setParkSpaceIsWrongFormat("Số chỗ đậu phải là lớn hơn 0.");
                }
            }

            if (priceStr.isEmpty() || !priceStr.matches("^\\d+$")) {
                isError = true;
                error.setParkCostIsWrongFormat("Tiền phí phải là số nguyên dương.");
            } else {
                price = Integer.parseInt(priceStr);
                if (price <= 0) {
                    isError = true;
                    error.setParkCostIsWrongFormat("Tiền phí phải là số nguyên dương.");
                } else if (!checkFullSlot(parkID)) {
                    isError = true;
                    error.setCreateError("Không thể cập nhật số lượng của bãi xe đang có khách.");
                }
            }

            if (!Validate.validLocationPoint(latStr)) {
                isError = true;
                error.setParkLatitudeIsWrongFormat("Kinh độ không đúng định dạng.");
            } else {
                lat = Float.parseFloat(latStr);
            }

            if (!Validate.validLocationPoint(lngStr)) {
                isError = true;
                error.setParkLongtitudeIsWrongFormat("Vĩ độ không đúng định dạng.");
            } else {
                lng = Float.parseFloat(lngStr);
            }

            dto = new ParkDTO(parkID, parkName, parkAddress, slot, price, lat, lng);

            if (!isError) {
                if (!updatePark(dto)) {
                    isError = true;
                    error.setCreateError("Cập nhật thất bại. Có lỗi xảy ra. Vui lòng thử lại.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log(e.getMessage());
        } finally {
            if (isError) {
                request.setAttribute("ERROR", error);
            } else {
                request.setAttribute("SUCCESS", "Cập nhật thành công.");
            }
            request.setAttribute("PARK", dto);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    private boolean updatePark(ParkDTO park) throws ClassNotFoundException, SQLException {
        ParkDAO dao = new ParkDAO();
        return dao.updatePark(park);
    }

    private boolean checkFullSlot(String parkID) throws ClassNotFoundException, SQLException {
        ParkDAO dao = new ParkDAO();
        return dao.checkFullSlot(parkID);
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
