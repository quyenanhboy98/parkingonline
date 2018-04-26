/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.Util;

/**
 *
 * @author Yuu
 */
public class ForgotPasswordDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public ForgotPasswordDAO() {
    }

    public boolean addToken(String token) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "INSERT INTO forgotpassword(RecoverToken) VALUES(?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, token);
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public boolean removeToken(String token) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "DELETE FROM forgotpassword WHERE RecoverToken = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, token);
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public String checkToken(String token) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();
            String sql = "SELECT RecoverToken FROM forgotpassword "
                    + "WHERE RecoverToken = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, token);
            rs = preStm.executeQuery();
            if (rs.next()) {
                return token;
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }
}
