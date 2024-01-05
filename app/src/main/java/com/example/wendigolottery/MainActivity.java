package com.example.wendigolottery;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    View rootView; Spinner spinnerTipos;
    TextView tituloSorteio, codigoSorteio, avisoLegal, dataSorteio;
    String[] arrayTiposSorteio = {"Mega-Sena", "Quina", "Lotofacil", "Lotomania", "Timemania", "Dia da Sorte"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = getWindow().getDecorView().getRootView();

        tituloSorteio = findViewById(R.id.textTema);
        codigoSorteio = findViewById(R.id.textCodigo);
        dataSorteio = findViewById(R.id.textData);

        avisoLegal = findViewById(R.id.txtAvisoLegal);
        avisoLegal.setText("Sorteio meramente ilustrativo e sem nenhuma ligação com canais oficiais.");

        spinnerTipos = findViewById(R.id.spinnerTipoSorteio);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayTiposSorteio);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipos.setAdapter(adapter);

        spinnerTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString().replaceAll("[^a-zA-Z]", "").toLowerCase();
                changeBackgroundBasedOnLottery(selectedItem);
                buscarSorteio(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                changeBackgroundBasedOnLottery("nenhum");
            }
        });

    }

    protected void changeBackgroundBasedOnLottery(String tipoSorteio) {
        String color = " ";

        switch (tipoSorteio) {
            case "lotomania":
                color = "#ffab64";
                break;
            case "megasena":
                color = "#6befa3";
                break;
            case "quina":
                color = "#8666ef";
                break;
            case "lotofacil":
                color = "#dd7ac6";
                break;
            case "timemania":
                color = "#5aad7d";
                break;
            case "diadasorte":
                color = "#bfaf83";
                break;
            default:
                color = "#ffffff";
                break;
        }

        rootView.setBackgroundColor(Color.parseColor(color));
    }

    protected void buscarSorteio(String tipoSorteio) {
        Log.d("PROCESSO:", "Enviando a solicitação para a API!");
        RestService restService = APIConnection.createConnectionToAPI();
        Call<ResponseBody> call = restService.buscarSorteio(tipoSorteio);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("PROCESSO:", "Resposta gerada pela API!");
                if(response.isSuccessful()) {
                    try {
                        String responseData = response.body().string();

                        Log.d("RETORNO:", responseData);

                        Gson gson = new Gson();
                        Sorteio sorteio = gson.fromJson(responseData, Sorteio.class);
                        montarSorteio(sorteio);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                Log.e("ERROR:", "Resposta não recebida!");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("ERROR:", "Falha na chamada à API", t);
            }
        });
    }

    protected void montarSorteio(Sorteio sorteioAtivo) {
        tituloSorteio.setText(sorteioAtivo.getTipoSorteio());
        codigoSorteio.setText("Código Nº" + sorteioAtivo.getCodigo().toString());
        dataSorteio.setText("Realizado em " + sorteioAtivo.getDataFormatada());
    }
}