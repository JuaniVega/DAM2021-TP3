package com.example.tp3;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.List;

@Database(entities = {RecordatorioModel.class}, version = 1)

public abstract class RecordatorioRoomDataBase extends RoomDatabase implements RecordatorioDataSource {
    public abstract RecordatorioDao recordatorioDao();
    private static final String DataBaseName= "recordatorio-db";
    private static RecordatorioRoomDataBase INSTANCE;

    public static RecordatorioRoomDataBase getInstance(final Context context){
        if(INSTANCE==null){
            synchronized (RecordatorioRoomDataBase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), RecordatorioRoomDataBase.class, DataBaseName).build();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void guardarRecordatorio(RecordatorioModel recordatorio, GuardarRecordatorioCallback callback){
        long idFila = INSTANCE.recordatorioDao().insert(recordatorio);
        boolean res=false;
        if(idFila != OnConflictStrategy.IGNORE){
            res=true;
        }
        callback.resultado(res);
    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback){
        List<String> listaResult = INSTANCE.recordatorioDao().getAll();
        boolean res= false;
        if(!listaResult.isEmpty() || listaResult!= null){
            res=true;
        }
        callback.resultado(res,listaResult);
    }
}
