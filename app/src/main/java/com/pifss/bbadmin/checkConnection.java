package com.pifss.bbadmin;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by ahmed on 12/04/17.
 */

public class checkConnection {

    ConnectivityManager cm;


    //(ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
    public checkConnection(ConnectivityManager cm) {
        this.cm = cm;
    }

    //show snackbar massage if there is no connection
    public void showSnackBar(View v){
        if (!haveNetworkConnection()) {
            Snackbar snackConnectiong = Snackbar.make(v,"No Internet connection",Snackbar.LENGTH_LONG);
            snackConnectiong.show();
        }

    }

    //check if their is internet connection
    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
