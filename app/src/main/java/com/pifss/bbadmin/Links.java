package com.pifss.bbadmin;

/**
 * Created by ahmed on 05/04/17.
 */

public class Links {

    //bbadmin
    public static String BASE = "http://34.196.107.188:8081/MhealthWeb/webresources";
    public static String BBADMIN = BASE + "/bbadmin";
    public static String BBADMIN_LOGIN = BBADMIN + "/login";
    public static String BBADMIN_RESET = BBADMIN + "/reset";

    //Campaign (callfordonation)
    public static String CAMPAIGN = BASE + "/callfordonation";

    //Schedule (Peroid Adapter)
    public static String SCHEDULE = BASE + "/schedule";//+"/branch/"+id+"/day/"+date

    //bbnotification
    public static String BBNOTIFICATION = BASE + "/bbnotification";

    //onsitedonor
    public static String ONSITEDONOR = BASE + "/onsitedonor";

    //OLD
    public static String ONSITEDONOR_OLD = "http://34.196.107.188:8080/mHealthWS/ws/onsitedonor";

    public static String BLOODREQUEST = BASE + "/bloodrequests/pending/";

    public static String BLOODREQUEST_SETSTATUS = BASE + "/bloodrequests/setstatus/";

    public static String DOCTOR = BASE + "/doctor";

}
