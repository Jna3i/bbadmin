package com.pifss.bbadmin;

import java.io.Serializable;

/**
 * Created by ios on 3/28/17.
 */

public class Campaign implements Serializable {


    private Integer CFDId;
    private String LLat;
    private String LLong;
    private String bloodTypes;
    private String enddate;
    private String locationName;
    private String name;
    private String startdate;
    private String status;

    public Integer getCFDId() {
        return CFDId;
    }

    public void setCFDId(Integer cFDId) {
        this.CFDId = cFDId;
    }

    public String getLLat() {
        return LLat;
    }

    public void setLLat(String lLat) {
        this.LLat = lLat;
    }

    public String getLLong() {
        return LLong;
    }

    public void setLLong(String lLong) {
        this.LLong = lLong;
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
