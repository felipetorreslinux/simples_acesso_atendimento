package com.simples.acesso.atendimento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.simples.acesso.atendimento.Permissoes.Permissoes;
import com.simples.acesso.atendimento.Views.Login;
import com.simples.acesso.atendimento.Views.Principal;

public class Splash extends AppCompatActivity {

    SharedPreferences profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Permissoes(this).checar();
        openAtenimento();
    }

    private void openAtenimento(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                profile = getSharedPreferences("profile", MODE_PRIVATE);
                if(profile != null){
                    if(profile.getInt("id", 0) != 0){
                        startActivity(new Intent(Splash.this, Principal.class));
                        finish();
                    }else{
                        startActivity(new Intent(Splash.this, Login.class));
                        finish();
                    }
                }
            }
        }, 2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                new Permissoes(this).checar();
                return;
            }
        }
    }
}
