package com.mu.networktest;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mu on 2016-11-09.
 */

public class UserDetailRequest extends NetworkRequest implements NetworkRequest.AsyncResponse {

    private static final String URL = "http://192.168.6.18:9101/user/%s";
    String url;


    public UserDetailRequest(String method, String userNum) {
        setMethod(method);
        url = String.format(URL, userNum);
    }

    @Override
    public URL getURL() throws MalformedURLException {
        return new URL(url);
    }

    @Override
    protected void setRequestProperty(HttpURLConnection conn) {
        super.setRequestProperty(conn);
        conn.setRequestProperty("Accept", "application/json");
    }

    @Override
    public void successResult(String output) {
        Gson gson = new Gson();
        NetworkResultTemp temp = gson.fromJson(output, NetworkResultTemp.class);
        if (temp.getCode() == 0) {
            UserItemData result = gson.fromJson(output, UserItemData.class);
            listener.onSuccess(this, result);
        }
    }
}
