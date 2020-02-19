package com.example.actnotassencillas.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actnotassencillas.R;

public class NotaViewHolder extends RecyclerView.ViewHolder {

    private TextView txtNota;

    public NotaViewHolder(@NonNull View itemView) {
        super(itemView);

        txtNota = itemView.findViewById(R.id.txt_nota);
    }

    public void asignarNota(String nota){
        txtNota.setText(nota);
    }
}
