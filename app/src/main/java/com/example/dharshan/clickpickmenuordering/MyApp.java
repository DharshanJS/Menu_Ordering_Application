package com.example.dharshan.clickpickmenuordering;

import android.app.Application;

/**
 * Created by Dharshan on 8/15/2017.
 */

public class MyApp extends Application{

    private String mGlobalVarValue="";

    public String getGlobalVarValue() {
        return mGlobalVarValue;
    }

    public void setGlobalVarValue(String str) {
        mGlobalVarValue += str+"\n";
    }
    public void setGlobalClear() {
        mGlobalVarValue ="";
    }
}
