package com.simples.acesso.atendimento.Services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.simples.acesso.atendimento.API.API;
import com.simples.acesso.atendimento.R;
import com.simples.acesso.atendimento.Utils.CarregaViews;
import com.simples.acesso.atendimento.Views.Principal;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.design.widget.Snackbar.*;

public class Services {

    Activity activity;
    SharedPreferences.Editor editorProfile;
    Snackbar snackbar;
    View view;

    public Services(Activity activity){
        this.activity = activity;
        this.view = activity.getWindow().getDecorView();
        this.editorProfile = activity.getSharedPreferences("profile", Context.MODE_PRIVATE).edit();
    }

    public void entrar (final String telefone, final String senha){
        AndroidNetworking.post(API.SERVER+"/Modules/Login.php")
            .addBodyParameter("cellphone", telefone)
            .addBodyParameter("password", senha)
            .build()
            .getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                public void onResponse(final JSONObject response){
                    try {
                        int code = response.getInt("code");
                        switch (code){
                            case 0:
                                int id = response.getJSONObject("profile").getInt("id");
                                String document = response.getJSONObject("profile").getString("document");
                                String name = response.getJSONObject("profile").getString("name");
                                String email = response.getJSONObject("profile").getString("email");
                                String token = response.getJSONObject("profile").getString("token");
                                editorProfile.putInt("id", id);
                                editorProfile.putString("document", document);
                                editorProfile.putString("name", name);
                                editorProfile.putString("email", email);
                                editorProfile.putString("cellphone", telefone);
                                editorProfile.putString("token", token);
                                editorProfile.commit();
                                activity.startActivity(new Intent(activity, Principal.class));
                                activity.finish();
                                break;
                            case 100:
                                String usuario = response.getString("name");
                                snackbar = Snackbar.make(view,
                                        "Usuário "+usuario+" logado", Snackbar.LENGTH_INDEFINITE);
                                snackbar.setActionTextColor(activity.getResources().getColor(R.color.colorWhite));
                                snackbar.setAction("Sair", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            sair(response.getInt("id"));
                                        } catch (JSONException e) {}
                                    }
                                });
                                snackbar.show();
                                break;
                            case 500:
                                snackbar = Snackbar.make(view,
                                        "Telefone e senha inválidos", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                break;
                        }
                        CarregaViews.fecha();
                    }catch (JSONException e){}

                }
                @Override
                public void onError(ANError anError) {
                    CarregaViews.fecha();
                }
            });
    }

    public void sair (int id){
        CarregaViews.abre(activity,"Saindo");
        AndroidNetworking.post(API.SERVER+"/Modules/Logout.php")
                .addBodyParameter("id", String.valueOf(id))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            int code = response.getInt("code");
                            switch (code){
                                case 0:
                                    CarregaViews.fecha();
                                    editorProfile.putInt("id", 0);
                                    editorProfile.commit();
                                    activity.finishAffinity();
                                    break;
                            }
                        }catch (JSONException e){}
                    }

                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }

}
