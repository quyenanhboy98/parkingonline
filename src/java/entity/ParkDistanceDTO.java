/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Yuu
 */
public class ParkDistanceDTO extends ParkDTO implements Serializable {

    private int emptySlot;
    private int distanceVal;
    private String distance;
    private String duration;

    public ParkDistanceDTO() {
    }

    /**
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
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
     * @return the distanceVal
     */
    public int getDistanceVal() {
        return distanceVal;
    }

    /**
     * @param distanceVal the distanceVal to set
     */
    public void setDistanceVal(int distanceVal) {
        this.distanceVal = distanceVal;
    }

    public static class SortByPrice implements Comparator<ParkDistanceDTO> {

        @Override
        public int compare(ParkDistanceDTO o1, ParkDistanceDTO o2) {
            return o1.getPrice() > o2.getPrice() ? 1 : o1.getPrice() < o2.getPrice() ? -1 : 0;
        }
    }

    public static class SortByDistance implements Comparator<ParkDistanceDTO> {

        @Override
        public int compare(ParkDistanceDTO o1, ParkDistanceDTO o2) {
            return o1.distanceVal > o2.distanceVal ? 1 : o1.distanceVal < o2.distanceVal ? -1 : 0;
        }
    }
}
