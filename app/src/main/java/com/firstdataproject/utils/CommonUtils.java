package com.firstdataproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Avdhesh AKhani on 10/27/2016.
 */

public class CommonUtils {

    public static boolean isOnline(Context mContext) {

        ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public static void showToast(Context mContext,String msg) {
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }

}
