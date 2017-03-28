package com.pifss.bbadmin;

import java.io.Serializable;

/**
 * Created by ios on 3/28/17.
 */

public class Campaign implements Serializable {


    private Integer cFDId;
    private String lLat;
    private String lLong;
    private String bloodTypes;
    private String enddate;
    private String locationName;
    private String name;
    private String startdate;
    private String status;

    public Integer getCFDId() {
        return cFDId;
    }

    public void setCFDId(Integer cFDId) {
        this.cFDId = cFDId;
    }

    public String getLLat() {
        return lLat;
    }

    public void setLLat(String lLat) {
        this.lLat = lLat;
    }

    public String getLLong() {
        return lLong;
    }

    public void setLLong(String lLong) {
        this.lLong = lLong;
    }

    public String getBloodTypes() {
        return bloodTypes;
    }

    public void setBloodTypes(String bloodTypes) {
        this.bloodTypes = bloodTypes;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
