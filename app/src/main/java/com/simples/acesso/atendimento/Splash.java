package com.simples.acesso.atendimento;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.simples.acesso.atendimento.Permissoes.Permissoes;
import com.simples.acesso.atendimento.Views.Login;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Permissoes(this).checar();
        openAtenimento();
    }

    private void openAtenimento(){
        new CountDownTimer(3000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(Splash.this, Login.class));
                finish();
            }
        }.start();
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
