package com.example.minhasfinancas;

import static android.os.Build.*;
import static android.os.Build.VERSION.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class SummaryActivity extends AppCompatActivity {

    private TextView txtResumo;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        txtResumo = findViewById(R.id.txtResumo);
        ArrayList<String> gastos = getIntent().getStringArrayListExtra("gastos");

        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulando processamento pesado

                HashMap<String, Double> totais = new HashMap<>();
                double totalMes = 0;

                assert gastos != null;
                for (String gasto : gastos) {
                    String[] partes = gasto.split(" - ");
                    if (partes.length < 4) continue;

                    double valor = Double.parseDouble(partes[1].replace("R$", "").replace(",", "."));
                    String categoria = partes[2];

                    totalMes += valor;
                    if (SDK_INT >= VERSION_CODES.N) {
                        totais.put(categoria, totais.getOrDefault(categoria, 0.0) + valor);
                    }
                }

                StringBuilder resultado = new StringBuilder();
                String maiorCategoria = "";
                double maiorValor = 0;

                for (Map.Entry<String, Double> entry : totais.entrySet()) {
                    resultado.append(entry.getKey()).append(": R$ ").append(String.format("%.2f", entry.getValue())).append("\n");
                    if (entry.getValue() > maiorValor) {
                        maiorValor = entry.getValue();
                        maiorCategoria = entry.getKey();
                    }
                }

                resultado.append("\nTotal do mês: R$ ").append(String.format("%.2f", totalMes));
                resultado.append("\nCategoria com maior gasto: ").append(maiorCategoria);

                String finalResultado = resultado.toString();
                runOnUiThread(() -> txtResumo.setText(finalResultado));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}