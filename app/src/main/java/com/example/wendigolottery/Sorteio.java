package com.example.wendigolottery;

import java.util.Arrays;
import java.util.Date;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sorteio {

    @SerializedName("tema")
    @Expose
    private String tipoSorteio;

    @SerializedName("numero")
    @Expose
    private Integer codigo;

    @SerializedName("data")
    @Expose
    private Date dataSorteio;

    @SerializedName("numerosSorteados")
    @Expose
    private int[] numerosSorteados;

    public Sorteio() {}

    public Sorteio(String tipoSorteio, Integer codigo, Date dataSorteio, int[] numerosSorteados) {
        this.tipoSorteio = tipoSorteio;
        this.codigo = codigo;
        this.dataSorteio = dataSorteio;
        this.numerosSorteados = numerosSorteados;
    }

    public String getTipoSorteio() {
        return tipoSorteio;
    }

    public void setTipoSorteio(String tipoSorteio) {
        this.tipoSorteio = tipoSorteio;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataSorteio() {
        return dataSorteio;
    }

    public void setDataSorteio(Date dataSorteio) {
        this.dataSorteio = dataSorteio;
    }

    public int[] getNumerosSorteados() {
        return numerosSorteados;
    }

    public void setNumerosSorteados(int[] numerosSorteados) {
        this.numerosSorteados = numerosSorteados;
    }

    @Override
    public String toString() {
        return "Sorteio{ " +
                "tipoSorteio=' " + tipoSorteio + '\'' +
                ", codigo= " + codigo +
                ", dataSorteio= " + dataSorteio +
                ", numerosSorteados= " + Arrays.toString(numerosSorteados) +
                '}';
    }
}
