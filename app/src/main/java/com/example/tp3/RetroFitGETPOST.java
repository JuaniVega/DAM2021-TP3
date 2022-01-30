package com.example.tp3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetroFitGETPOST {
    @GET("recordatorio")
    Call<List<String>> getRecord();

    @POST("recordatorio")
    Call<RecordatorioModel> setRecord(@Body RecordatorioModel rm);
}
