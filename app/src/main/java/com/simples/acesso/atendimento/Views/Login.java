package com.simples.acesso.atendimento.Views;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.simples.acesso.atendimento.R;

public class Login extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout layoutEmailLogin;
    TextInputLayout layoutSenhaLogin;

    EditText emailLogin;
    EditText senha_login;

    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_login);
        createToolbar();
    }

    private void createToolbar() {
        Drawable backIconActionBar = getResources().getDrawable(R.drawable.ic_back_white);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.titulo_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(backIconActionBar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        layoutEmailLogin = findViewById(R.id.layout_email_login);
        layoutSenhaLogin = findViewById(R.id.layout_senha_login);
        emailLogin = findViewById(R.id.email_login);
        senha_login = findViewById(R.id.senha_login);
        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
