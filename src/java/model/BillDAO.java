/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.jdbc.Statement;
import entity.BillDTO;
import entity.BookingDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.Util;

/**
 *
 * @author Yuu
 */
public class BillDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public BillDAO() {
    }

    public int addBill(BillDTO bill) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();
            String sql = "INSERT INTO bill(Money, Point, BookingID, EmpId) VALUES(?,?,?,?)";

            preStm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStm.setFloat(1, bill.getMoney());
            preStm.setInt(2, bill.getPoint());
            preStm.setInt(3, bill.getBookingID());
            preStm.setString(4, bill.getEmpID());

            boolean result = (preStm.executeUpdate() > 0);
            if (result) {
                rs = preStm.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return -1;
    }

    public void getListBillsOfUser(String phone, List<BookingDTO> listBooking, List<BillDTO> listBill) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT B.BillID, B.BookingID, P.ParkName, BO.BookTime, BO.CheckinTime, BO.CheckoutTime, B.Money FROM bill AS B, booking AS BO, park AS P, userdetails AS U "
                    + "WHERE B.BookingID = BO.BookingID "
                    + "AND BO.Phone = U.Phone "
                    + "AND BO.ParkID = P.ParkID "
                    + "ANd U.Phone = ? ";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);

            rs = preStm.executeQuery();

            while (rs.next()) {
                BillDTO bill = new BillDTO();
                bill.setBillID(rs.getInt("BillID"));
                bill.setBookingID(rs.getInt("BookingID"));
                bill.setMoney(rs.getFloat("Money"));
                listBill.add(bill);

                BookingDTO booking = new BookingDTO();
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setParkName(rs.getString("ParkName"));
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setCheckoutTime(rs.getTimestamp("CheckoutTime"));
                listBooking.add(booking);
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }

    public void getListBills(String parkID, List<BookingDTO> listBooking, List<BillDTO> listBill) throws SQLException, ClassNotFoundException {
        if (parkID == null) {
            parkID = "";
        }
        try {
            conn = Util.getConnection();

            String sql = "SELECT B.BillID, B.BookingID, P.ParkName, BO.BookTime, BO.CheckinTime, BO.CheckoutTime, B.Money FROM bill AS B, booking AS BO, park AS P, userdetails AS U "
                    + "WHERE B.BookingID = BO.BookingID "
                    + "AND BO.Phone = U.Phone "
                    + "AND BO.ParkID = P.ParkID "
                    + "ANd BO.ParkID like ? ";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + parkID + "%");

            rs = preStm.executeQuery();

            while (rs.next()) {
                BillDTO bill = new BillDTO();
                bill.setBillID(rs.getInt("BillID"));
                bill.setBookingID(rs.getInt("BookingID"));
                bill.setMoney(rs.getFloat("Money"));
                listBill.add(bill);

                BookingDTO booking = new BookingDTO();
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setParkName(rs.getString("ParkName"));
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setCheckoutTime(rs.getTimestamp("CheckoutTime"));
                listBooking.add(booking);
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }

    public BillDTO getBillDetail(String billID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM bill WHERE BillID = ? ";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, billID);

            rs = preStm.executeQuery();

            if (rs.next()) {
                BillDTO bill = new BillDTO();
                bill.setBillID(rs.getInt("BillID"));
                bill.setBookingID(rs.getInt("BookingID"));
                bill.setEmpID(rs.getString("EmpID"));
                bill.setMoney(rs.getFloat("Money"));
                return bill;
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public void searchListBillsOfUser(String phone, List<BookingDTO> listBooking, List<BillDTO> listBill, Timestamp checkinTime, Timestamp checkoutTime) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT B.BillID, B.BookingID, P.ParkName, BO.BookTime, BO.CheckinTime, BO.CheckoutTime, B.Money FROM bill AS B, booking AS BO, park AS P, userdetails AS U "
                    + "WHERE B.BookingID = BO.BookingID "
                    + "AND BO.Phone = U.Phone "
                    + "AND BO.ParkID = P.ParkID "
                    + "ANd U.Phone = ? "
                    + "AND BO.CheckinTime >= ? "
                    + "AND BO.CheckoutTime <= ? ";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            preStm.setTimestamp(2, checkinTime);
            preStm.setTimestamp(3, checkoutTime);

            rs = preStm.executeQuery();

            while (rs.next()) {
                BillDTO bill = new BillDTO();
                bill.setBillID(rs.getInt("BillID"));
                bill.setBookingID(rs.getInt("BookingID"));
                bill.setMoney(rs.getFloat("Money"));
                listBill.add(bill);

                BookingDTO booking = new BookingDTO();
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setParkName(rs.getString("ParkName"));
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setCheckoutTime(rs.getTimestamp("CheckoutTime"));
                listBooking.add(booking);
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }

    public void searchListBills(String parkID, List<BookingDTO> listBooking, List<BillDTO> listBill, Timestamp checkinTime, Timestamp checkoutTime) throws SQLException, ClassNotFoundException {
        if (parkID == null) parkID = "";
        try {
            conn = Util.getConnection();

            String sql = "SELECT B.BillID, B.BookingID, P.ParkName, BO.BookTime, BO.CheckinTime, BO.CheckoutTime, B.Money FROM bill AS B, booking AS BO, park AS P, userdetails AS U "
                    + "WHERE B.BookingID = BO.BookingID "
                    + "AND BO.Phone = U.Phone "
                    + "AND BO.ParkID = P.ParkID "
                    + "ANd BO.ParkID like ? "
                    + "AND BO.CheckinTime >= ? "
                    + "AND BO.CheckoutTime <= ? ";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + parkID + "%");
            preStm.setTimestamp(2, checkinTime);
            preStm.setTimestamp(3, checkoutTime);

            rs = preStm.executeQuery();

            while (rs.next()) {
                BillDTO bill = new BillDTO();
                bill.setBillID(rs.getInt("BillID"));
                bill.setBookingID(rs.getInt("BookingID"));
                bill.setMoney(rs.getFloat("Money"));
                listBill.add(bill);

                BookingDTO booking = new BookingDTO();
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setParkName(rs.getString("ParkName"));
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setCheckoutTime(rs.getTimestamp("CheckoutTime"));
                listBooking.add(booking);
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }
}
