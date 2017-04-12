package com.pifss.bbadmin;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by PIFSS on 4/12/2017.
 */

public class Slots implements Serializable{
    private String day;
    private ArrayList slots;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList getSlots() {
        return slots;
    }

    public void setSlots(ArrayList slots) {
        this.slots = slots;
    }
}
