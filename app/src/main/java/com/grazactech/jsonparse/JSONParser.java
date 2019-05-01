package com.grazactech.jsonparse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {

    public static ArrayList<Mobile> mMobile = new ArrayList<>();

    public static Mobile parseFeed(JSONObject obj) {
        try {

            Mobile mobile = new Mobile();
            mobile.setmName(obj.getString("name"));
            mobile.setmCompanyName(obj.getString("companyName"));
            mobile.setmOperatingSystem(obj.getString("operatingSystem"));
            mobile.setmProcessor(obj.getString("processor"));
            mobile.setmBackCamera(obj.getString("backCamera"));
            mobile.setmFrontCamera(obj.getString("frontCamera"));
            mobile.setmRam(obj.getString("ram"));
            mobile.setmRom(obj.getString("rom"));
            mobile.setmScreenSize(obj.getString("screenSize"));
            mobile.setmUrl(obj.getString("url"));
            mobile.setmBattery(obj.getString("battery"));
            return mobile;

        } catch (JSONException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Mobile> parseArrayFeed(JSONArray array) {
        JSONObject obj = null;
        Mobile mobile = null;
        mMobile.clear();
        try {
            for (int i = 0; i < array.length(); i++) {
                obj = array.getJSONObject(i);
                mobile = new Mobile();
                mobile.setmName(obj.getString("name"));
                mobile.setmCompanyName(obj.getString("companyName"));
                mobile.setmOperatingSystem(obj.getString("operatingSystem"));
                mobile.setmProcessor(obj.getString("processor"));
                mobile.setmBackCamera(obj.getString("backCamera"));
                mobile.setmFrontCamera(obj.getString("frontCamera"));
                mobile.setmRam(obj.getString("ram"));
                mobile.setmRom(obj.getString("rom"));
                mobile.setmScreenSize(obj.getString("screenSize"));
                mobile.setmUrl(obj.getString("url"));
                mobile.setmBattery(obj.getString("battery"));
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
            return null;
        }

        return mMobile;
    }
}