package com.simples.acesso.atendimento.API;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class API {

    Activity activity;
    SharedPreferences profile;

    public API(Activity activity){
        this.activity = activity;
        this.profile = activity.getSharedPreferences("profile", Context.MODE_PRIVATE);
    }

    public static String SERVER = "http://simplesacesso.com";

    public String TOKEN (){
        return profile.getString("token", null);
    }
}
