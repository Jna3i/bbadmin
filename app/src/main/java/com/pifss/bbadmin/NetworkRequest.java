package com.pifss.bbadmin;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ahmed on 28/03/17.
 */

public class NetworkRequest {

    private RequestQueue requestQueue;
    private static final NetworkRequest ourInstance = new NetworkRequest();

    public static NetworkRequest getInstance() {
        return ourInstance;
    }

    private NetworkRequest() {
    }

    public  RequestQueue getRequestQueue(Context context) {

        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(context);


        return requestQueue;
    }
}
