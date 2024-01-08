package com.example.wendigolottery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class NumerosSorteadosAdapter extends RecyclerView.Adapter<NumerosSorteadosAdapter.ViewHolder> {

    private int[] numerosSorteados;

    public NumerosSorteadosAdapter(int[] numerosSorteados) {
        this.numerosSorteados = numerosSorteados;
    }public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtNumeroSorteado);
        }
    }

    @Override
    public NumerosSorteadosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_numeros, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int item = numerosSorteados[position];
        holder.textView.setText(String.valueOf(item));
        // Adicione a formatação desejada aqui, como cores, tamanho de texto, etc.
    }

    @Override
    public int getItemCount() {
        return numerosSorteados.length;
    }

}
