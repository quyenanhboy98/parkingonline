/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.ParkDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Util;

/**
 *
 * @author Yuu
 */
public class ParkDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public ParkDAO() {
    }

    public boolean checkActive(String parkID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();
            String sql = "SELECT Active FROM park WHERE ParkID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, parkID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("Active");
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return false;
    }

    public boolean addPark(ParkDTO park) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "INSERT INTO park(ParkID, ParkName, Slot, EmptySlot, Price, Lat, Lng, ParkAddress) VALUES(?,?,?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, park.getParkID());
            preStm.setString(2, park.getParkName());
            preStm.setInt(3, park.getSlot());
            preStm.setInt(4, park.getSlot());
            preStm.setInt(5, park.getPrice());
            preStm.setFloat(6, park.getLat());
            preStm.setFloat(7, park.getLng());
            preStm.setString(8, park.getParkAddress());
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public List<ParkDTO> getListParkActive() throws SQLException, ClassNotFoundException {
        List<ParkDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM park WHERE Active = TRUE";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                ParkDTO park = new ParkDTO();
                park.setParkID(rs.getString("ParkID"));
                park.setParkName(rs.getString("ParkName"));
                park.setParkAddress(rs.getString("ParkAddress"));
                park.setSlot(rs.getInt("Slot"));
                park.setPrice(rs.getInt("Price"));
                park.setEmptySlot(rs.getInt("EmptySlot"));
                park.setLat(rs.getFloat("Lat"));
                park.setLng(rs.getFloat("Lng"));
                park.setActive(rs.getBoolean("Active"));
                result.add(park);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<ParkDTO> getListPark() throws SQLException, ClassNotFoundException {
        List<ParkDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM park";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                ParkDTO park = new ParkDTO();
                park.setParkID(rs.getString("ParkID"));
                park.setParkName(rs.getString("ParkName"));
                park.setParkAddress(rs.getString("ParkAddress"));
                park.setSlot(rs.getInt("Slot"));
                park.setPrice(rs.getInt("Price"));
                park.setEmptySlot(rs.getInt("EmptySlot"));
                park.setLat(rs.getFloat("Lat"));
                park.setLng(rs.getFloat("Lng"));
                park.setActive(rs.getBoolean("Active"));
                result.add(park);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<ParkDTO> searchListParkByAddress(String value) throws SQLException, ClassNotFoundException {
        List<ParkDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT * "
                    + "FROM park WHERE ParkID LIKE ? OR ParkName LIKE ? OR ParkAddress LIKE ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + value + "%");
            preStm.setString(2, "%" + value + "%");
            preStm.setString(3, "%" + value + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                ParkDTO park = new ParkDTO();
                park.setParkID(rs.getString("ParkID"));
                park.setParkName(rs.getString("ParkName"));
                park.setParkAddress(rs.getString("ParkAddress"));
                park.setSlot(rs.getInt("Slot"));
                park.setPrice(rs.getInt("Price"));
                park.setEmptySlot(rs.getInt("EmptySlot"));
                park.setLat(rs.getFloat("Lat"));
                park.setLng(rs.getFloat("Lng"));
                park.setActive(rs.getBoolean("Active"));
                result.add(park);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public boolean updatePark(ParkDTO park) throws ClassNotFoundException, SQLException {
        boolean isUpdate = false;
        try {
            conn = Util.getConnection();
            String sql = "UPDATE park SET Slot = ?, EmptySlot = ?, ParkName = ?, ParkAddress = ?, Lat = ?, Lng = ? "
                    + "WHERE ParkID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, park.getSlot());
            preStm.setInt(2, park.getSlot());
            preStm.setString(3, park.getParkName());
            preStm.setString(4, park.getParkAddress());
            preStm.setFloat(5, park.getLat());
            preStm.setFloat(6, park.getLng());
            preStm.setString(7, park.getParkID());
            isUpdate = (preStm.executeUpdate() > 0);

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return isUpdate;
    }

    public boolean changeActive(String parkID, boolean active) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "UPDATE park SET Active = ? WHERE ParkID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, active);
            preStm.setString(2, parkID);
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public ParkDTO getParkByID(String parkID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM park WHERE ParkID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, parkID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                ParkDTO park = new ParkDTO();
                park.setParkID(rs.getString("ParkID"));
                park.setParkName(rs.getString("ParkName"));
                park.setParkAddress(rs.getString("ParkAddress"));
                park.setSlot(rs.getInt("Slot"));
                park.setPrice(rs.getInt("Price"));
                park.setEmptySlot(rs.getInt("EmptySlot"));
                park.setLat(rs.getFloat("Lat"));
                park.setLng(rs.getFloat("Lng"));
                park.setActive(rs.getBoolean("Active"));
                return park;
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public boolean checkEmptySlot(String parkID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT EmptySlot FROM park WHERE ParkID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, parkID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                int emptySlot = rs.getInt("EmptySlot");
                return (emptySlot > 0);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return false;
    }

    public boolean checkFullSlot(String parkID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT EmptySlot, Slot FROM park WHERE ParkID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, parkID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                return rs.getInt("EmptySlot") == rs.getInt("Slot");
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return false;
    }

    public boolean updateEmptySlot(String parkID, int emptySlot) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "UPDATE park SET EmptySlot = EmptySlot + ? WHERE ParkID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, emptySlot);
            preStm.setString(2, parkID);
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }
}
