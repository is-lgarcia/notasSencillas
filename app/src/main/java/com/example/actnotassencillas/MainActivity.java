package com.example.actnotassencillas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.actnotassencillas.Adapter.NotaAdapter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView notasrv;
    private EditText editNota;
    private NotaAdapter notaAdapter;
    private ServicioArchivo servicio = new ServicioArchivo(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notasrv = findViewById(R.id.rv_notas);
        editNota = findViewById(R.id.edit_nota);
        try {
            notaAdapter = new NotaAdapter(this, Arrays.asList(servicio.leerNotas()));
        }catch (IOException e){
            Toast.makeText(this, "No existe ningún archivo aún", Toast.LENGTH_SHORT).show();
            notaAdapter = new NotaAdapter(this, new ArrayList<String>());
        }

        notasrv.setHasFixedSize(true);
        notasrv.setAdapter(notaAdapter);
        notasrv.setLayoutManager(new GridLayoutManager(this,2));
    }

    public void onClickAgregarNota (View view){
        if (!editNota.getText().toString().equals("")) {
            try {
                servicio.agregarNotas(editNota.getText().toString());
                notaAdapter.setNotas(Arrays.asList(servicio.leerNotas()));
                notaAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Nota agregada con éxito", Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                Toast.makeText(this, "Error al agregar la nota", Toast.LENGTH_SHORT).show();
            }
        }else Toast.makeText(this, "Recuerda que debes escribir algo primero", Toast.LENGTH_SHORT).show();
    }


    public void onClickEliminar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("¿Desea eliminar las notas?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        servicio.eliminar();
                        notaAdapter.setNotas(new ArrayList<String>());
                        notaAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Notas eliminadas", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
