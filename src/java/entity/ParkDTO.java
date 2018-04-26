/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Yuu
 */
public class ParkDTO implements Serializable {

    private String parkID;
    private String parkName;
    private String parkAddress;
    private int slot;
    private int emptySlot;
    private int price;
    private float lat;
    private float lng;
    private boolean active;

    public ParkDTO(String parkID, String parkName, String parkAddress, int slot, int price, float lat, float lng) {
        this.parkID = parkID;
        this.parkName = parkName;
        this.parkAddress = parkAddress;
        this.slot = slot;
        this.price = price;
        this.lat = lat;
        this.lng = lng;
    }

    public ParkDTO(String parkName, String parkAddress, int slot, int price, float lat, float lng) {
        this.parkName = parkName;
        this.parkAddress = parkAddress;
        this.slot = slot;
        this.price = price;
        this.lat = lat;
        this.lng = lng;
    }

    public ParkDTO() {
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
     * @return the slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * @param slot the slot to set
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * @return the emptySlot
     */
    public int getEmptySlot() {
        return emptySlot;
    }

    /**
     * @param emptySlot the emptySlot to set
     */
    public void setEmptySlot(int emptySlot) {
        this.emptySlot = emptySlot;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the lat
     */
    public float getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(float lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public float getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(float lng) {
        this.lng = lng;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the parkAddress
     */
    public String getParkAddress() {
        return parkAddress;
    }

    /**
     * @param parkAddress the parkAddress to set
     */
    public void setParkAddress(String parkAddress) {
        this.parkAddress = parkAddress;
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

}
