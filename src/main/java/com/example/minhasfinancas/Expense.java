package com.example.minhasfinancas;

import androidx.annotation.NonNull;

public class Expense {
    private final int id;
    private final String descricao;
    private final double valor;
    private final String categoria;
    private final String data;

    public Expense(int id, String descricao, double valor, String categoria, String data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public String getCategoria() { return categoria; }
    public String getData() { return data; }

    @NonNull
    @Override
    public String toString() {
        return descricao + " - R$" + valor + " - " + categoria + " - " + data;
    }
}