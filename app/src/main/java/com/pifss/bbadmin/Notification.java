package com.pifss.bbadmin;

import java.io.Serializable;

/**
 * Created by Macbook on 3/29/17.
 */

public class Notification implements Serializable {

    String title;
    String description;
    String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
