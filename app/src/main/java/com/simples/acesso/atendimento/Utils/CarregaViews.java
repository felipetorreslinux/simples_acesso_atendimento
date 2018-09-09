package com.simples.acesso.atendimento.Utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.simples.acesso.atendimento.R;

public class CarregaViews {

    static AlertDialog.Builder builder;
    static AlertDialog alertDialog;

    public static void abre (Activity activity, String message) {
        builder = new AlertDialog.Builder(activity, R.style.CustomDialog);
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_carregar, null);
        TextView mensagem_load = view.findViewById(R.id.mensagem_load);
        mensagem_load.setText(message);
        builder.setView(view);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public static void fecha (){
        alertDialog.dismiss();
    }


}
