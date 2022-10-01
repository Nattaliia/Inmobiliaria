package com.example.inmobiliaria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.inmobiliaria.R;
import com.example.inmobiliaria.modelo.Pago;


import java.util.ArrayList;

public class PagoAdapter extends ArrayAdapter<Pago> {
    private ArrayList<Pago> pagos;
    private Context context;
    private LayoutInflater inflater;
    public PagoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Pago> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.pagos = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.detalle_pagos, parent, false);
        }
        TextView tvCodigo = v.findViewById(R.id.tvCodigo);
        TextView tvNumero = v.findViewById(R.id.tvNumero);
        TextView tvContrato = v.findViewById(R.id.tvIdContrato);
        TextView tvImporte = v.findViewById(R.id.tvImporte);
        TextView tvFecha = v.findViewById(R.id.tvFecha);

        tvCodigo.setText(pagos.get(position).getIdPago() + "");
        tvNumero.setText(pagos.get(position).getNumero() + "");
        tvContrato.setText(pagos.get(position).getContrato().getIdContrato() + "");
        tvImporte.setText("$" + pagos.get(position).getImporte());
        tvFecha.setText(pagos.get(position).getFechaDePago());
        return v;
    }
}
