package com.example.tareaactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class ConfirmarContacto extends AppCompatActivity {
    private TextView tvNombreCompleto;
    private TextView tvFechaNacimiento;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcionContacto;
    AppCompatButton btnEditarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_contacto);

        setearInformacionContacto();
    }

    public void setearInformacionContacto(){
        Bundle parametros = getIntent().getExtras();
            String nombreCompleto = parametros.getString(getResources().getString(R.string.pnombreCompleto));
            String fechaNacimiento = parametros.getString(getResources().getString(R.string.pFechaNacimiento));
            String telefono = parametros.getString(getResources().getString(R.string.pTelefono));
            String email = parametros.getString(getResources().getString(R.string.pEmail));
            String descripcionContacto = parametros.getString(getResources().getString(R.string.pDescripcionContacto));
            tvNombreCompleto = findViewById(R.id.tvLblNombreCompleto);
            tvFechaNacimiento = findViewById(R.id.tvLblFechaNacimiento);
            tvTelefono = findViewById(R.id.tvLblTelefono);
            tvEmail = findViewById(R.id.tvLblEmail);
            tvDescripcionContacto = findViewById(R.id.tvLblDescripcionContacto);
            btnEditarDatos = findViewById(R.id.btnEditarDatos);

            tvNombreCompleto.setText(nombreCompleto);
            tvFechaNacimiento.setText("Fecha de Nacimiento: " + fechaNacimiento);
            tvTelefono.setText("Tél." + telefono);
            tvEmail.setText("Email: " + email);
            tvDescripcionContacto.setText("Descripción: " + descripcionContacto);
            btnEditarDatos.setOnClickListener(view -> {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(getResources().getString(R.string.pnombreCompleto), nombreCompleto);
                intent.putExtra(getResources().getString(R.string.pFechaNacimiento), fechaNacimiento);
                intent.putExtra(getResources().getString(R.string.pTelefono), telefono);
                intent.putExtra(getResources().getString(R.string.pEmail), email);
                intent.putExtra(getResources().getString(R.string.pDescripcionContacto), descripcionContacto);
                startActivity(intent);
            });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}