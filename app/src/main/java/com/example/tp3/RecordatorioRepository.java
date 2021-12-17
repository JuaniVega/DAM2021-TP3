package com.example.tp3;

public class RecordatorioRepository {
    private final RecordatorioDataSource datasource;
    public RecordatorioRepository(final RecordatorioDataSource datasource) {
        this.datasource = datasource;
    }
    // Metodos que recuperan los recordatorios usando el data source
}
