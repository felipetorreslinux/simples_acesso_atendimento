package com.simples.acesso.atendimento.Views;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.simples.acesso.atendimento.R;
import com.simples.acesso.atendimento.Services.Services;
import com.simples.acesso.atendimento.Utils.CarregaViews;
import com.simples.acesso.atendimento.Utils.Keyboard;
import com.simples.acesso.atendimento.Utils.MascaraTelefone;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences profile;

    TextInputLayout layoutTelefoneLogin;
    TextInputLayout layoutSenhaLogin;

    EditText telefoneLogin;
    EditText senhaLogin;

    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_login);
        createToolbar();

    }

    private void createToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        layoutTelefoneLogin = findViewById(R.id.layout_telefone_login);
        layoutSenhaLogin = findViewById(R.id.layout_senha_login);
        telefoneLogin = findViewById(R.id.telefone_login);
        telefoneLogin.addTextChangedListener(MascaraTelefone.insert(telefoneLogin));
        senhaLogin = findViewById(R.id.senha_login);
        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                checarLogin();
                break;
        }
    }

    private void checarLogin() {
        String telefone = telefoneLogin.getText().toString().trim();
        String senha = senhaLogin.getText().toString().trim();
        if(telefone.isEmpty()){
            layoutTelefoneLogin.setErrorEnabled(true);
            layoutSenhaLogin.setErrorEnabled(false);
            layoutTelefoneLogin.setError("Informe seu telefone");
            telefoneLogin.requestFocus();
        }else if(telefone.length() < 14){
            layoutTelefoneLogin.setErrorEnabled(true);
            layoutSenhaLogin.setErrorEnabled(false);
            layoutTelefoneLogin.setError("Telefone inválido");
            telefoneLogin.requestFocus();
        }else if(senha.isEmpty()){
            layoutSenhaLogin.setErrorEnabled(false);
            layoutSenhaLogin.setErrorEnabled(true);
            layoutSenhaLogin.setError("Informe sua senha");
            senhaLogin.requestFocus();
        }else if(senha.length() < 6){
            layoutSenhaLogin.setErrorEnabled(false);
            layoutSenhaLogin.setErrorEnabled(true);
            layoutSenhaLogin.setError("Senha inválida ou incorreta");
            senhaLogin.requestFocus();
        }else{
            layoutSenhaLogin.setErrorEnabled(false);
            layoutSenhaLogin.setErrorEnabled(false);
            Keyboard.close(this, getWindow().getDecorView());
            CarregaViews.abre(this, "Autorizando");
            new Services(this).login(MascaraTelefone.unmask(telefone), senha);
        }
    }


}
