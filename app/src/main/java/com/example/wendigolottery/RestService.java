package com.example.wendigolottery;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestService {

    @GET("/sorteio/{tipo}")
    Call<ResponseBody> buscarSorteio(@Path("tipo") String tipo);
}
