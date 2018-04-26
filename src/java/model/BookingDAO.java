/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class BookingDAO implements Serializable {

    private final static int STATUS_BOOKING = 1;
    private final static int STATUS_CHECKIN = 2;
    private final static int STATUS_COMPLETED = 3;
    private final static int STATUS_CANCELED = 4;
    private final static int STATUS_EXPIRED = 5;

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public BookingDAO() {
    }

    public BookingDTO getCurrentBooking(String phone) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT B.BookingID, B.ParkID, P.ParkName, B.BookTime, B.ExpiredTime "
                    + "FROM booking AS B, park AS P "
                    + "WHERE B.ParkID = P.ParkID "
                    + "AND Phone = ? AND Status = ? ";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            preStm.setInt(2, STATUS_BOOKING);
            rs = preStm.executeQuery();

            if (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setParkID(rs.getString("ParkID"));
                booking.setParkName(rs.getString("ParkName"));
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setExpiredTime(rs.getTimestamp("ExpiredTime"));
                booking.setPhone(phone);
                booking.setStatus(STATUS_BOOKING);
                return booking;
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;

    }

    public boolean checkStatusBooking(String bookingID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT Status FROM booking WHERE BookingID = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, bookingID);
            rs = preStm.executeQuery();

            if (rs.next()) {
                int status = rs.getInt("Status");
                return status == STATUS_BOOKING;
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return false;
    }

    public Timestamp getBookineExperiedTime(String bookingID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT ExpiredTime FROM booking WHERE BookingID = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, bookingID);
            rs = preStm.executeQuery();

            if (rs.next()) {
                return rs.getTimestamp("ExpiredTime");
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public BookingDTO getBookingDetail(int bookingID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM booking WHERE BookingID = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, bookingID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setPhone(rs.getString("Phone"));
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setExpiredTime(rs.getTimestamp("ExpiredTime"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setCheckoutTime(rs.getTimestamp("CheckoutTime"));
                booking.setStatus(rs.getInt("Status"));
                booking.setParkID(rs.getString("ParkID"));
                return booking;
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public List<BookingDTO> getListBookingOfUser(String phone) throws SQLException, ClassNotFoundException {
        List<BookingDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT P.ParkID, P.ParkName, B.Phone, B.BookingID, B.BookTime, B.ExpiredTime, B.CheckinTime, B.CheckoutTime, B.Status "
                    + "FROM booking AS B, park AS P "
                    + "WHERE Phone = ? AND B.ParkID = P.ParkID "
                    + "ORDER BY BookingID DESC";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            rs = preStm.executeQuery();
            while (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setPhone(phone);
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setExpiredTime(rs.getTimestamp("ExpiredTime"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setCheckoutTime(rs.getTimestamp("CheckoutTime"));
                booking.setStatus(rs.getInt("Status"));
                booking.setParkID(rs.getString("ParkID"));
                booking.setParkName(rs.getString("ParkName"));
                result.add(booking);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<BookingDTO> getListBooking(String parkID) throws SQLException, ClassNotFoundException {
        if (parkID == null) {
            parkID = "";
        }
        List<BookingDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT P.ParkID, P.ParkName, B.Phone, B.BookingID, B.BookTime, B.ExpiredTime, B.CheckinTime, B.CheckoutTime, B.Status "
                    + "FROM booking AS B, park AS P "
                    + "WHERE B.ParkID like ? AND B.ParkID = P.ParkID "
                    + "ORDER BY BookingID DESC";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + parkID + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setPhone(rs.getString("Phone"));
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setExpiredTime(rs.getTimestamp("ExpiredTime"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setCheckoutTime(rs.getTimestamp("CheckoutTime"));
                booking.setStatus(rs.getInt("Status"));
                booking.setParkID(rs.getString("ParkID"));
                booking.setParkName(rs.getString("ParkName"));
                result.add(booking);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<BookingDTO> searchListBookingOfUser(String phone, Timestamp fromDate, Timestamp toDate, String status) throws SQLException, ClassNotFoundException {
        List<BookingDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT P.ParkID, P.ParkName, B.Phone, B.BookingID, B.BookTime, B.ExpiredTime, B.CheckinTime, B.CheckoutTime, B.Status "
                    + "FROM booking AS B, park AS P "
                    + "WHERE Phone = ? AND B.ParkID = P.ParkID "
                    + "AND B.BookTime >= ? "
                    + "AND B.BookTime <= ? "
                    + "AND B.Status like ? "
                    + "ORDER BY BookingID DESC";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            preStm.setTimestamp(2, fromDate);
            preStm.setTimestamp(3, toDate);
            preStm.setString(4, "%" + status + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setPhone(phone);
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setExpiredTime(rs.getTimestamp("ExpiredTime"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setCheckoutTime(rs.getTimestamp("CheckoutTime"));
                booking.setStatus(rs.getInt("Status"));
                booking.setParkID(rs.getString("ParkID"));
                booking.setParkName(rs.getString("ParkName"));
                result.add(booking);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<BookingDTO> searchListBooking(String parkID, Timestamp fromDate, Timestamp toDate, String status) throws SQLException, ClassNotFoundException {
        List<BookingDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT P.ParkID, P.ParkName, B.Phone, B.BookingID, B.BookTime, B.ExpiredTime, B.CheckinTime, B.CheckoutTime, B.Status "
                    + "FROM booking AS B, park AS P "
                    + "WHERE B.ParkID like ? AND B.ParkID = P.ParkID "
                    + "AND B.BookTime >= ? "
                    + "AND B.BookTime <= ? "
                    + "AND B.Status like ? "
                    + "ORDER BY BookingID DESC";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + parkID + "%");
            preStm.setTimestamp(2, fromDate);
            preStm.setTimestamp(3, toDate);
            preStm.setString(4, "%" + status + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setPhone(rs.getString("Phone"));
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setExpiredTime(rs.getTimestamp("ExpiredTime"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setCheckoutTime(rs.getTimestamp("CheckoutTime"));
                booking.setStatus(rs.getInt("Status"));
                booking.setParkID(rs.getString("ParkID"));
                booking.setParkName(rs.getString("ParkName"));
                result.add(booking);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<BookingDTO> getListExpiredBooking() throws SQLException, ClassNotFoundException {
        List<BookingDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT BookingID, ParkID, Phone, ExpiredTime FROM booking WHERE Status = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, STATUS_BOOKING);
            rs = preStm.executeQuery();

            Timestamp current = new Timestamp(System.currentTimeMillis());

            while (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setPhone(rs.getString("Phone"));
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setParkID(rs.getString("ParkID"));
                booking.setExpiredTime(rs.getTimestamp("ExpiredTime"));
                if (booking.getExpiredTime().before(current)) {
                    result.add(booking);
                }
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<BookingDTO> getListBookingLockedPark() throws SQLException, ClassNotFoundException {
        List<BookingDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT B.BookingID, B.ParkID, P.ParkName, B.Phone, B.ExpiredTime FROM booking AS B, park AS P "
                    + "WHERE B.ParkID = P.ParkID "
                    + "AND P.Active = FALSE "
                    + "AND Status = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, STATUS_BOOKING);
            rs = preStm.executeQuery();

            while (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setPhone(rs.getString("Phone"));
                booking.setParkName(rs.getString("ParkName"));
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setParkID(rs.getString("ParkID"));
                booking.setExpiredTime(rs.getTimestamp("ExpiredTime"));
                result.add(booking);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public boolean updateBookingLockedPark(BookingDTO booking) throws ClassNotFoundException, SQLException {

        try {
            conn = Util.getConnection();

            conn.setAutoCommit(false);

            String updateBooking = "UPDATE booking SET Status = ? WHERE BookingID = ?";
            String updatePark = "UPDATE park SET EmptySlot = EmptySlot + 1 WHERE ParkID = ?";

            PreparedStatement updateBookingPreStm = conn.prepareStatement(updateBooking);
            PreparedStatement updateParkPreStm = conn.prepareStatement(updatePark);

            updateBookingPreStm.setInt(1, STATUS_CANCELED);
            updateBookingPreStm.setInt(2, booking.getBookingID());
            updateBookingPreStm.executeUpdate();

            updateParkPreStm.setString(1, booking.getParkID());
            updateParkPreStm.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            conn.rollback();
            return false;
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }

    public void updateExpiredBooking(List<BookingDTO> list) throws ClassNotFoundException, SQLException {

        try {
            conn = Util.getConnection();

            conn.setAutoCommit(false);

            String updateBooking = "UPDATE booking SET Status = ? WHERE BookingID = ?";
            String updatePoint = "UPDATE userdetails SET Point = Point - 10  WHERE Phone = ?";
            String updatePark = "UPDATE park SET EmptySlot = EmptySlot + 1 WHERE ParkID = ?";

            PreparedStatement updateBookingPreStm = conn.prepareStatement(updateBooking);
            PreparedStatement updatePointPreStm = conn.prepareStatement(updatePoint);
            PreparedStatement updateParkPreStm = conn.prepareStatement(updatePark);

            for (BookingDTO booking : list) {

                updateBookingPreStm.setInt(1, STATUS_EXPIRED);
                updateBookingPreStm.setInt(2, booking.getBookingID());
                updateBookingPreStm.executeUpdate();

                updatePointPreStm.setString(1, booking.getPhone());
                updatePointPreStm.executeUpdate();

                updateParkPreStm.setString(1, booking.getParkID());
                updateParkPreStm.executeUpdate();

            }

            conn.commit();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            conn.rollback();
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }

    public boolean cancelBooking(BookingDTO booking, int point) throws ClassNotFoundException, SQLException {
        boolean isError = false;
        try {
            conn = Util.getConnection();

            conn.setAutoCommit(false);

            String updateBooking = "UPDATE booking SET Status = ? WHERE BookingID = ?";
            String updatePoint = "UPDATE userdetails SET Point = Point - ?  WHERE Phone = ?";
            String updatePark = "UPDATE park SET EmptySlot = EmptySlot + 1 WHERE ParkID = ?";

            PreparedStatement updateBookingPreStm = conn.prepareStatement(updateBooking);
            PreparedStatement updatePointPreStm = conn.prepareStatement(updatePoint);
            PreparedStatement updateParkPreStm = conn.prepareStatement(updatePark);

            updateBookingPreStm.setInt(1, STATUS_CANCELED);
            updateBookingPreStm.setInt(2, booking.getBookingID());
            updateBookingPreStm.executeUpdate();

            updatePointPreStm.setInt(1, point);
            updatePointPreStm.setString(2, booking.getPhone());
            updatePointPreStm.executeUpdate();

            updateParkPreStm.setString(1, booking.getParkID());
            updateParkPreStm.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            isError = true;
            System.out.println("Error: " + e.getMessage());
            conn.rollback();
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return !isError;
    }

    public boolean addBooking(BookingDTO booking) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "INSERT INTO booking(ParkID, Phone, BookTime, ExpiredTime, Status) VALUES(?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, booking.getParkID());
            preStm.setString(2, booking.getPhone());
            preStm.setTimestamp(3, booking.getBookTime());
            preStm.setTimestamp(4, booking.getExpiredTime());
            preStm.setInt(5, booking.getStatus());

            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public boolean checkinBooking(String bookingID, String cardID, Timestamp checkinTime) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();
            String sql = "UPDATE booking SET Status = ?, CheckinTime = ?, CardID = ? WHERE BookingID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, STATUS_CHECKIN);
            preStm.setTimestamp(2, checkinTime);
            preStm.setString(3, cardID);
            preStm.setString(4, bookingID);

            return (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }

    public BookingDTO getCheckinBookingByID(String bookingID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM booking WHERE Status = ? AND BookingID = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, STATUS_CHECKIN);
            preStm.setString(2, bookingID);
            rs = preStm.executeQuery();

            if (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setCardID(rs.getString("CardID"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setExpiredTime(rs.getTimestamp("ExpiredTime"));
                booking.setParkID(rs.getString("ParkID"));
                booking.setPhone(rs.getString("Phone"));
                booking.setStatus(STATUS_BOOKING);
                return booking;
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public BookingDTO getCheckinBookingByCardID(String cardID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM booking WHERE Status = ? AND cardID = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, STATUS_CHECKIN);
            preStm.setString(2, cardID);
            rs = preStm.executeQuery();

            if (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setBookTime(rs.getTimestamp("BookTime"));
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setCardID(rs.getString("CardID"));
                booking.setCheckinTime(rs.getTimestamp("CheckinTime"));
                booking.setExpiredTime(rs.getTimestamp("ExpiredTime"));
                booking.setParkID(rs.getString("ParkID"));
                booking.setPhone(rs.getString("Phone"));
                booking.setStatus(STATUS_BOOKING);
                return booking;
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public boolean checkoutBooking(BookingDTO booking, int point) throws SQLException, ClassNotFoundException {
        boolean isError = false;
        try {
            conn = Util.getConnection();

            conn.setAutoCommit(false);

            String updateBooking = "UPDATE booking SET Status = ?, CheckoutTime = ?, CardID = NULL WHERE BookingID = ?";
            String updatePoint = "UPDATE userdetails SET Point = Point + ?  WHERE Phone = ?";
            String updatePark = "UPDATE park SET EmptySlot = EmptySlot + 1 WHERE ParkID = ?";

            PreparedStatement updateBookingPreStm = conn.prepareStatement(updateBooking);
            PreparedStatement updatePointPreStm = conn.prepareStatement(updatePoint);
            PreparedStatement updateParkPreStm = conn.prepareStatement(updatePark);

            updateBookingPreStm.setInt(1, STATUS_COMPLETED);
            updateBookingPreStm.setTimestamp(2, booking.getCheckoutTime());
            updateBookingPreStm.setInt(3, booking.getBookingID());
            updateBookingPreStm.executeUpdate();

            updatePointPreStm.setInt(1, point);
            updatePointPreStm.setString(2, booking.getPhone());
            updatePointPreStm.executeUpdate();

            updateParkPreStm.setString(1, booking.getParkID());
            updateParkPreStm.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            isError = true;
            System.out.println("Error: " + e.getMessage());
            conn.rollback();
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return !isError;
    }

    public boolean checkCardIDForCheckin(String cardID, String parkID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT Status FROM booking WHERE CardID = ? AND ParkID = ?";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, cardID);
            preStm.setString(2, parkID);
            rs = preStm.executeQuery();

            while (rs.next()) {
                int status = rs.getInt("Status");
                if (status == STATUS_CHECKIN) {
                    return true;
                }
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return false;
    }

}
