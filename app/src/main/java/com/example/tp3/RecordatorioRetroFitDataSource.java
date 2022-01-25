package com.example.tp3;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordatorioRetroFitDataSource implements RecordatorioDataSource{

    private RetroFitGETPOST retroGP;

    public RecordatorioRetroFitDataSource(String user, String psw){
        retroGP=RetroFitService.createService(RetroFitGETPOST.class, user, psw);
    }

    @Override
    public void guardarRecordatorio(RecordatorioModel recordatorio, GuardarRecordatorioCallback callback) {

        Call<RecordatorioModel> call = retroGP.setRecord(recordatorio);
        call.enqueue(new Callback<RecordatorioModel>() {
            @Override
            public void onResponse(Call<RecordatorioModel> call, Response<RecordatorioModel> response) {
                if(response.isSuccessful()){
                    callback.resultado(true);
                    return;
                }else{
                    callback.resultado(false);
                    return;
                }
            }

            @Override
            public void onFailure(Call<RecordatorioModel> call, Throwable throwable) {
                callback.resultado(false);
                return;
            }
        });

    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback) {

        Call<List<String>> call = retroGP.getRecord();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    List<String> rm =response.body();
                    callback.resultado(true, rm);
                    return;
                }else{
                    callback.resultado(false, new ArrayList<String>());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable throwable) {
                callback.resultado(false, new ArrayList<String>());
            }
        });

    }
}
