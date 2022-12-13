package com.example.tareaactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;

    TextInputEditText tvNombreCompleto;
    AppCompatButton btnFechaNacimiento;
    TextInputEditText tvTelefono;
    TextInputEditText tvEmail;
    TextInputEditText tvDescripcionContacto;
    AppCompatButton btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatePicker();
        tvNombreCompleto = findViewById(R.id.tvNombreCompleto);
        btnFechaNacimiento = findViewById(R.id.btnFechaNacimiento);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescripcionContacto = findViewById(R.id.tvDescripcionContacto);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(view -> {
            if(!tvNombreCompleto.getText().toString().isEmpty() && !btnFechaNacimiento.getText().toString().isEmpty() &&
                    !tvTelefono.getText().toString().isEmpty() && !tvEmail.getText().toString().isEmpty() && !tvDescripcionContacto.getText().toString().isEmpty()) {

                Contacto contacto = new Contacto(tvNombreCompleto.getText().toString(), btnFechaNacimiento.getText().toString(),
                tvTelefono.getText().toString(), tvEmail.getText().toString(), tvDescripcionContacto.getText().toString());

                Intent intent = new Intent(this, ConfirmarContacto.class);
                intent.putExtra(getResources().getString(R.string.pnombreCompleto), contacto.getNombreCompleto());
                intent.putExtra(getResources().getString(R.string.pFechaNacimiento), contacto.getFechaNacimiento());
                intent.putExtra(getResources().getString(R.string.pTelefono), contacto.getTelefono());
                intent.putExtra(getResources().getString(R.string.pEmail), contacto.getEmail());
                intent.putExtra(getResources().getString(R.string.pDescripcionContacto), contacto.getDescripcionContacto());
                startActivity(intent);
                finish(); //Se finaliza la actividad actual donde se encuentra el usuario);
            }else{
                Snackbar.make(view, getResources().getString(R.string.datos_incompletos),Snackbar.LENGTH_LONG)
                        .show();
            }
        });
        setearInformacionContacto();
        //dateButton.setText(getTodayDate());
    }
    private String getTodayDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month++;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    String date = makeDateString(day,month,year);
                    btnFechaNacimiento.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private String makeDateString(int day, int month, int year){
        return day + "/" + month + "/" + year;
    }

    private String getMonthFormat(int month){
        if(month == 1)
                return "JAN";
        else if(month == 2)
                return "FEB";
        else if(month == 3)
                return "MAR";
        else if(month == 4)
                return "APR";
        else if(month == 5)
                return "MAY";
        else if(month == 6)
                return "JUN";
        else if(month == 7)
                return "JUL";
        else if(month == 8)
                return "AUG";
        else if(month == 9)
                return "SEP";
        else if(month == 10)
                return "OCT";
        else if(month == 11)
                return "NOV";
        else if(month == 12)
                return "DEC";

        //never arrive here
        else
            return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void setearInformacionContacto(){
        Bundle parametros = getIntent().getExtras();
        if(parametros != null) {

                String nombreCompleto = parametros.getString(getResources().getString(R.string.pnombreCompleto));
                String fechaNacimiento = parametros.getString(getResources().getString(R.string.pFechaNacimiento));
                String telefono = parametros.getString(getResources().getString(R.string.pTelefono));
                String email = parametros.getString(getResources().getString(R.string.pEmail));
                String descripcionContacto = parametros.getString(getResources().getString(R.string.pDescripcionContacto));

                tvNombreCompleto.setText(nombreCompleto);
                btnFechaNacimiento.setText(fechaNacimiento);
                tvTelefono.setText(telefono);
                tvEmail.setText(email);
                tvDescripcionContacto.setText(descripcionContacto);

        }
    }
}