package com.example.tp3;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface RecordatorioDao {

    @Query("SELECT * FROM recordatorio")
    List<String> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(RecordatorioModel... recordatorioModel );

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(RecordatorioModel recordatorioModel);

    @Delete
    void delete(RecordatorioModel recordatorioModel);
}
