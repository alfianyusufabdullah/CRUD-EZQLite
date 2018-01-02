package com.alfianyusufabdullah.crudezqlite;

import android.app.Application;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.alfianyusufabdullah.crudezqlite.common.DatabaseConfig;

import alfianyusufabdullah.ezqlite.EZQLiteDatabaseConfiguration;
import alfianyusufabdullah.ezqlite.configuration.TableConfiguration;

/**
 * Created by jonesrandom on 12/31/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TableConfiguration tabelMahasiswa = new TableConfiguration(DatabaseConfig.TABEL_NAME);
        tabelMahasiswa.addColumn(DatabaseConfig.COL_ID, TableConfiguration.TYPE_INTEGER, "PRIMARY KEY AUTOINCREMENT");
        tabelMahasiswa.addColumn(DatabaseConfig.COL_NAMA_MAHASISWA, TableConfiguration.TYPE_VARCHAR);
        tabelMahasiswa.addColumn(DatabaseConfig.COL_NIM_MAHASISWA, TableConfiguration.TYPE_VARCHAR);
        tabelMahasiswa.addColumn(DatabaseConfig.COL_SEMESTER_MAHASISWA, TableConfiguration.TYPE_VARCHAR);

        EZQLiteDatabaseConfiguration.getInstance()
                .setDatabaseName(DatabaseConfig.DATABASE_NAME)
                .setDatabaseVersion(DatabaseConfig.DATABASE_VERSION)
                .addTableConfiguration(tabelMahasiswa)
                .build();

    }
}
