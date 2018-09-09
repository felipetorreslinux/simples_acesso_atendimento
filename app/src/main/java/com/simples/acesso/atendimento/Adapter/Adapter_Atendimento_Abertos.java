package com.simples.acesso.atendimento.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class Adapter_Atendimento_Abertos extends RecyclerView.Adapter<Adapter_Atendimento_Abertos.AtendimentoAbertos> {

    Activity activity;

    public Adapter_Atendimento_Abertos(Activity activity){
        this.activity = activity;
    }

    @NonNull
    @Override
    public AtendimentoAbertos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AtendimentoAbertos atendimentoAbertos, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AtendimentoAbertos extends RecyclerView.ViewHolder {
        public AtendimentoAbertos(@NonNull View itemView) {
            super(itemView);
        }
    }
}
