package com.example.tp3;

import java.util.Date;
import java.util.Objects;

public class RecordatorioModel {
    private String texto;
    private Date fecha;

    public RecordatorioModel(final String texto, final Date fecha) {
        this.texto = texto;
        this.fecha = fecha;
        }

public String getTexto() {
        return texto;
        }
public void setTexto(final String texto) {
        this.texto = texto;
        }
public Date getFecha() {
        return fecha;
        }
public void setFecha(final Date fecha) {
        this.fecha = fecha;
        }

@Override
public boolean equals(final Object other) {
        if (this == other) {
        return true;
        }
        if (other == null || !getClass().equals(other.getClass())) {
        return false;
        }
final RecordatorioModel that = (RecordatorioModel) other;
        return Objects.equals(this.texto, that.texto) && Objects.equals(this.fecha, that.fecha);
        }

@Override
public int hashCode() {
        return Objects.hash(texto) + Objects.hash(fecha);
        }
        }

/*public class RecordatorioModel {
    private String texto;
    private Date fechaD;
    private long fechaMs;

    public RecordatorioModel(final String texto, final long fechaMs) {
        this.fechaD= new Date(fechaMs);
        this.fechaD.setTime(fechaMs);
        this.texto = texto;
        this.fechaMs= fechaMs;
    }

    public RecordatorioModel(final String texto, final Date fechaD) {
        this.fechaD= fechaD;
        this.texto = texto;
        this.fechaMs=0;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(final String texto) {
        this.texto = texto;
    }
    public Date getFechaD() {
        return fechaD;
    }
    public long getFechaMs() {
        return fechaMs;
    }
    public void setFecha(final Date fecha) {
        this.fechaD = fecha;
    }
    public void setFecha(final long fecha) {
        this.fechaD= new Date(fecha);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }
        final RecordatorioModel that = (RecordatorioModel) other;
        return Objects.equals(this.texto, that.texto) && Objects.equals(this.fechaD, that.fechaD);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texto) + Objects.hash(fechaD);
    }
}*/
