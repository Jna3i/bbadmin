package com.pifss.bbadmin.BloodRequests;

import java.io.Serializable;

/**
 * Created by ahmed on 11/04/17.
 */

public class DoctorsHandler implements Serializable {

    int requestsId;
    String bloodType;
    int drId;
    int quantity;
    String reason;
    int status;
    String timestamp;


    public void setRequestsId(int requestId) {
        this.requestsId = requestId;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int setStatus(int status) {
        this.status = status;
        return status;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getRequestsId() {
        return requestsId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public int getDrId() {
        return drId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getReason() {
        return reason;
    }

    public int getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
