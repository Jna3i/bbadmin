
package com.pifss.bbadmin;

import java.io.Serializable;
import java.util.List;

public class Period implements Serializable {

    private List<Donor> donors = null;
    private List<OnSiteDonor> onSiteDonors = null;
    private String period;
    private Integer total;

    public List<Donor> getDonors() {
        return donors;
    }

    public void setDonors(List<Donor> donors) {
        this.donors = donors;
    }

    public List<OnSiteDonor> getOnSiteDonors() {
        return onSiteDonors;
    }

    public void setOnSiteDonors(List<OnSiteDonor> onSiteDonors) {
        this.onSiteDonors = onSiteDonors;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
