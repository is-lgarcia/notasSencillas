package com.example.actnotassencillas;

import android.app.Activity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServicioArchivo {

    private Activity activity;
    private String notas = "";
    private static final String NOMBRE_ARCHIVO = "notas.txt";

    public ServicioArchivo(Activity activity) {
        this.activity = activity;
    }

    public void agregarNotas(String nota) throws IOException {
        notas = notas.concat("".equals(nota) ? "" : ";"); //
        notas = notas.concat(nota);
        guardar();
    }

    public String[] leerNotas() throws IOException {
        cargar();
        return notas.split(";");
    }

    public String leerNotas(int posicion) throws IOException {
        cargar();
        String[] listaNotas = notas.split(";");
        return listaNotas[posicion];
    }

    private void guardar() throws IOException {
        FileOutputStream fos = activity.openFileOutput(NOMBRE_ARCHIVO, activity.MODE_PRIVATE);
        fos.write(notas.getBytes());
        fos.close();
    }

    private void cargar() throws IOException {
        FileInputStream fis = activity.openFileInput(NOMBRE_ARCHIVO);
        int c;
        notas = "";
        while ((c = fis.read()) != -1)
            notas += String.valueOf((char) c);
        fis.close();
    }

    public void eliminar() {
        activity.deleteFile(NOMBRE_ARCHIVO);
        notas = "";
    }
}
