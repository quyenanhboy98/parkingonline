/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Yuu
 */
public class BookingDTO implements Serializable {

    private int bookingID;
    private String cardID;
    private String parkID;
    private String parkName;
    private String phone;
    private Timestamp bookTime;
    private Timestamp expiredTime;
    private Timestamp checkinTime;
    private Timestamp checkoutTime;
    private int status;

    public BookingDTO() {
    }

    public BookingDTO(String parkID, String phone, Timestamp bookTime, Timestamp expiredTime, int status) {
        this.parkID = parkID;
        this.phone = phone;
        this.bookTime = bookTime;
        this.expiredTime = expiredTime;
        this.status = status;
    }

    /**
     * @return the bookingID
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * @param bookingID the bookingID to set
     */
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * @return the parkID
     */
    public String getParkID() {
        return parkID;
    }

    /**
     * @param parkID the parkID to set
     */
    public void setParkID(String parkID) {
        this.parkID = parkID;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the bookTime
     */
    public Timestamp getBookTime() {
        return bookTime;
    }

    /**
     * @param bookTime the bookTime to set
     */
    public void setBookTime(Timestamp bookTime) {
        this.bookTime = bookTime;
    }

    /**
     * @return the expiredTime
     */
    public Timestamp getExpiredTime() {
        return expiredTime;
    }

    /**
     * @param expiredTime the expiredTime to set
     */
    public void setExpiredTime(Timestamp expiredTime) {
        this.expiredTime = expiredTime;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the parkName
     */
    public String getParkName() {
        return parkName;
    }

    /**
     * @param parkName the parkName to set
     */
    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    /**
     * @return the checkinTime
     */
    public Timestamp getCheckinTime() {
        return checkinTime;
    }

    /**
     * @param checkinTime the checkinTime to set
     */
    public void setCheckinTime(Timestamp checkinTime) {
        this.checkinTime = checkinTime;
    }

    /**
     * @return the checkoutTime
     */
    public Timestamp getCheckoutTime() {
        return checkoutTime;
    }

    /**
     * @param checkoutTime the checkoutTime to set
     */
    public void setCheckoutTime(Timestamp checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    /**
     * @return the cardID
     */
    public String getCardID() {
        return cardID;
    }

    /**
     * @param cardID the cardID to set
     */
    public void setCardID(String cardID) {
        this.cardID = cardID;
    }
}
