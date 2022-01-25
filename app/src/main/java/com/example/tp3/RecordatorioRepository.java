package com.example.tp3;

public final class RecordatorioRepository {
    private final RecordatorioDataSource datasource;
    public RecordatorioRepository(final RecordatorioDataSource datasource) {
        this.datasource = datasource;
    }
    // Metodos que recuperan los recordatorios usando el data source

    public void obtenerRecord(RecordatorioDataSource.RecuperarRecordatorioCallback callback){
        datasource.recuperarRecordatorios(callback);
    }

    public void guardarRecord(RecordatorioModel recordatorio, RecordatorioDataSource.GuardarRecordatorioCallback callback){
        datasource.guardarRecordatorio(recordatorio,callback);
    }
}
