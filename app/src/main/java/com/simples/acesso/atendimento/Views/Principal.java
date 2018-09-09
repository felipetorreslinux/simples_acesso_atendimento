package com.simples.acesso.atendimento.Views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.simples.acesso.atendimento.R;
import com.simples.acesso.atendimento.Services.Services;

public class Principal extends AppCompatActivity {

    SharedPreferences profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_principal);
        createToolbar();
    }

    private void createToolbar() {
        profile = getSharedPreferences("profile", MODE_PRIVATE);
        String[] name = profile.getString("name", null).split(" ");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_principal);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Olá, " + name[0]);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sair_app:
                int id = profile.getInt("id", 0);
                if(id != 0){
                    new Services(this).sair(id);
                }
                break;
        }
        return true;
    }
}
