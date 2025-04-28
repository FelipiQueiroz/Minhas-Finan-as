package com.example.minhasfinancas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText edtDescricao, edtValor;
    private Spinner spinnerCategoria;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtValor = findViewById(R.id.edtValor);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        datePicker = findViewById(R.id.datePicker);

        String[] categorias = {"Alimentação", "Transporte", "Lazer", "Outros"};
        spinnerCategoria.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categorias));

        Button btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(v -> salvarGasto());
    }

    private void salvarGasto() {
        String descricao = edtDescricao.getText().toString();
        String valor = edtValor.getText().toString();
        String categoria = spinnerCategoria.getSelectedItem().toString();
        String data = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();

        String gasto = descricao + " - R$" + valor + " - " + categoria + " - " + data;

        Intent resultIntent = new Intent();
        resultIntent.putExtra("gasto", gasto);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}