package com.example.minhasfinancas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<String> gastos = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listaGastos);
        @SuppressLint("WrongViewCast") FloatingActionButton btnAdd = findViewById(R.id.btnAdicionar);
        @SuppressLint("WrongViewCast") FloatingActionButton btnResumo = findViewById(R.id.btnResumo);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gastos);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
            startActivityForResult(intent, 1);
        });

        btnResumo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            intent.putStringArrayListExtra("gastos", gastos);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String novoGasto = data.getStringExtra("gasto");
            gastos.add(novoGasto);
            adapter.notifyDataSetChanged();
        }
    }
}