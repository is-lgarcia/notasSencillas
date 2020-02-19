package com.example.actnotassencillas.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actnotassencillas.R;

import java.util.List;

public class NotaAdapter extends RecyclerView.Adapter<NotaViewHolder> {

    private List<String> notas;
    private Activity activity;

    public NotaAdapter(Activity activity,List<String> notas){
        this.activity = activity;
        this.notas = notas;
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.nota_item,parent,false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        holder.asignarNota(notas.get(position));
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public List<String> getNotas() {
        return notas;
    }

    public void setNotas(List<String> notas) {
        this.notas = notas;
    }

    public Activity getActivity() {
        return activity;
    }
}
