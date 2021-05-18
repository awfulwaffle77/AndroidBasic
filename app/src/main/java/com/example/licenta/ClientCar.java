package com.example.licenta;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientCar {
    private String mIP;
    private String mPassword;
    private boolean isRemembered;
    private String mModel;
    private String mLastLogin;

    public ClientCar(){
        // Default constructor required for calls to DataSnapshot.getValue(ClientCar.class)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ClientCar(String mIP, String mPassword, boolean isRemembered, String mModel) {
        this.mIP = mIP;
        this.isRemembered = isRemembered;
        this.mModel = mModel;

        try {
            this.mPassword = MySHA256.getDigest(mPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        updateLastLogin();
    }

    public String getmIP() {
        return mIP;
    }
    public void setmIP(String mIP) {
        this.mIP = mIP;
    }
    public String getmPassword() {
        return mPassword;
    }
    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
    public boolean isRemembered() {
        return isRemembered;
    }
    public void setRemembered(boolean remembered) {
        isRemembered = remembered;
    }
    public String getmModel() {
        return mModel;
    }
    public void setmModel(String mModel) {
        this.mModel = mModel;
    }
    public String getmLastLogin() {
        return mLastLogin;
    }
    public void setmLastLogin(String mLastLogin) {
        this.mLastLogin = mLastLogin;
    }

    @Override
    public int hashCode() {
        int cod = this.mIP.length();
        for (int i = 0; i < this.mIP.length(); i++)
            cod += this.mIP.charAt(i);
        cod = cod * 3;
        return cod;
    }

    @Override
    public String toString() {
        return "ClientCar{" +
                "mIP='" + mIP + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", isRemembered=" + isRemembered +
                ", mModel='" + mModel + '\'' +
                ", mLastLogin='" + mLastLogin + '\'' +
                '}';
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateLastLogin(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.mLastLogin = myDateObj.format(myFormatObj);
    }
}
