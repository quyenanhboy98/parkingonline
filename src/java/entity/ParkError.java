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
public class ParkError implements Serializable {

    private String parkIDIsExisted;
    private String parkIDIsEmtpy;
    private String parkNameIsEmpty;
    private String parkAddressIsEmpty;
    private String parkSpaceIsWrongFormat;
    private String parkCostIsWrongFormat;
    private String parkLatitudeIsWrongFormat;
    private String parkLongtitudeIsWrongFormat;
    private String parkIDIsWrongFormat;
    private String parkAddressIsWrongFormat;
    private String parkNameIsWrongFormat;
    private String parkIsNotFull;
    private String createError;

    /**
     * @return the parkIDIsExisted
     */
    public String getParkIDIsExisted() {
        return parkIDIsExisted;
    }

    /**
     * @param parkIDIsExisted the parkIDIsExisted to set
     */
    public void setParkIDIsExisted(String parkIDIsExisted) {
        this.parkIDIsExisted = parkIDIsExisted;
    }

    /**
     * @return the parkIDIsEmtpy
     */
    public String getParkIDIsEmtpy() {
        return parkIDIsEmtpy;
    }

    /**
     * @param parkIDIsEmtpy the parkIDIsEmtpy to set
     */
    public void setParkIDIsEmtpy(String parkIDIsEmtpy) {
        this.parkIDIsEmtpy = parkIDIsEmtpy;
    }

    /**
     * @return the parkNameIsEmpty
     */
    public String getParkNameIsEmpty() {
        return parkNameIsEmpty;
    }

    /**
     * @param parkNameIsEmpty the parkNameIsEmpty to set
     */
    public void setParkNameIsEmpty(String parkNameIsEmpty) {
        this.parkNameIsEmpty = parkNameIsEmpty;
    }

    /**
     * @return the parkAddressIsEmpty
     */
    public String getParkAddressIsEmpty() {
        return parkAddressIsEmpty;
    }

    /**
     * @param parkAddressIsEmpty the parkAddressIsEmpty to set
     */
    public void setParkAddressIsEmpty(String parkAddressIsEmpty) {
        this.parkAddressIsEmpty = parkAddressIsEmpty;
    }

    /**
     * @return the parkSpaceIsWrongFormat
     */
    public String getParkSpaceIsWrongFormat() {
        return parkSpaceIsWrongFormat;
    }

    /**
     * @param parkSpaceIsWrongFormat the parkSpaceIsWrongFormat to set
     */
    public void setParkSpaceIsWrongFormat(String parkSpaceIsWrongFormat) {
        this.parkSpaceIsWrongFormat = parkSpaceIsWrongFormat;
    }

    /**
     * @return the parkLatitudeIsWrongFormat
     */
    public String getParkLatitudeIsWrongFormat() {
        return parkLatitudeIsWrongFormat;
    }

    /**
     * @param parkLatitudeIsWrongFormat the parkLatitudeIsWrongFormat to set
     */
    public void setParkLatitudeIsWrongFormat(String parkLatitudeIsWrongFormat) {
        this.parkLatitudeIsWrongFormat = parkLatitudeIsWrongFormat;
    }

    /**
     * @return the parkLongtitudeIsWrongFormat
     */
    public String getParkLongtitudeIsWrongFormat() {
        return parkLongtitudeIsWrongFormat;
    }

    /**
     * @param parkLongtitudeIsWrongFormat the parkLongtitudeIsWrongFormat to set
     */
    public void setParkLongtitudeIsWrongFormat(String parkLongtitudeIsWrongFormat) {
        this.parkLongtitudeIsWrongFormat = parkLongtitudeIsWrongFormat;
    }

    /**
     * @return the createError
     */
    public String getCreateError() {
        return createError;
    }

    /**
     * @param createError the createError to set
     */
    public void setCreateError(String createError) {
        this.createError = createError;
    }

    /**
     * @return the parkCostIsWrongFormat
     */
    public String getParkCostIsWrongFormat() {
        return parkCostIsWrongFormat;
    }

    /**
     * @param parkCostIsWrongFormat the parkCostIsWrongFormat to set
     */
    public void setParkCostIsWrongFormat(String parkCostIsWrongFormat) {
        this.parkCostIsWrongFormat = parkCostIsWrongFormat;
    }

    public String getParkIDIsWrongFormat() {
        return parkIDIsWrongFormat;
    }

    public void setParkIDIsWrongFormat(String parkIDIsWrongFormat) {
        this.parkIDIsWrongFormat = parkIDIsWrongFormat;
    }

    public String getParkAddressIsWrongFormat() {
        return parkAddressIsWrongFormat;
    }

    public void setParkAddressIsWrongFormat(String parkAddressIsWrongFormat) {
        this.parkAddressIsWrongFormat = parkAddressIsWrongFormat;
    }

    public String getParkNameIsWrongFormat() {
        return parkNameIsWrongFormat;
    }

    public void setParkNameIsWrongFormat(String parkNameIsWrongFormat) {
        this.parkNameIsWrongFormat = parkNameIsWrongFormat;
    }

    /**
     * @return the parkIsNotFull
     */
    public String getParkIsNotFull() {
        return parkIsNotFull;
    }

    /**
     * @param parkIsNotFull the parkIsNotFull to set
     */
    public void setParkIsNotFull(String parkIsNotFull) {
        this.parkIsNotFull = parkIsNotFull;
    }
    
    
}
