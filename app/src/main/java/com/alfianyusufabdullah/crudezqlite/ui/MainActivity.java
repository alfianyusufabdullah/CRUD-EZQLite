package com.alfianyusufabdullah.crudezqlite.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.alfianyusufabdullah.crudezqlite.R;
import com.alfianyusufabdullah.crudezqlite.common.DatabaseConfig;

import alfianyusufabdullah.ezqlite.EZQLite;
import alfianyusufabdullah.ezqlite.listener.OnDatabaseCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout inputNama, inputNim, inputSemester;
    TextInputEditText etNama, etNim, etSemester;
    Button btnTambahData, btnLiatData;

    String textNama, textNim, textSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("EZQLite");
        }

        initView();
    }

    private void initView() {
        inputNama = findViewById(R.id.inputNama);
        inputNim = findViewById(R.id.inputNim);
        inputSemester = findViewById(R.id.inputSemester);

        etNama = findViewById(R.id.etNama);
        etNim = findViewById(R.id.etNim);
        etSemester = findViewById(R.id.etSemester);

        btnLiatData = findViewById(R.id.btnLihatData);
        btnTambahData = findViewById(R.id.btnTambahData);

        btnLiatData.setOnClickListener(this);
        btnTambahData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLihatData:
                startActivity(new Intent(MainActivity.this , ListActivity.class));
                break;
            case R.id.btnTambahData:

                textNama = etNama.getText().toString().trim();
                textNim = etNim.getText().toString();
                textSemester = etSemester.getText().toString().trim();

                if (textNama.isEmpty()) {
                    inputNama.setError("Masukkan Nama Mahasiswa");
                    etNama.requestFocus();
                    return;
                }

                if (textNim.isEmpty()) {
                    inputNim.setError("Masukkan NIM");
                    etNim.requestFocus();
                    return;
                }

                if (textSemester.isEmpty()) {
                    inputSemester.setError("Masukkan Semester");
                    etSemester.requestFocus();
                    return;
                }

                EZQLite.getInstance(MainActivity.this)
                        .doInsert(DatabaseConfig.TABEL_NAME)
                        .putValue(DatabaseConfig.COL_NAMA_MAHASISWA, textNama)
                        .putValue(DatabaseConfig.COL_NIM_MAHASISWA, textNim)
                        .putValue(DatabaseConfig.COL_SEMESTER_MAHASISWA, textSemester)
                        .insert(new OnDatabaseCallback() {
                            @Override
                            public void Success() {
                                hideSoftkey(etNama);

                                etNama.setText("");
                                etSemester.setText("");
                                etNim.setText("");

                                inputNama.setErrorEnabled(false);
                                inputNim.setErrorEnabled(false);
                                inputSemester.setErrorEnabled(false);

                                etNama.clearFocus();
                                etSemester.clearFocus();
                                etNim.clearFocus();

                                Snackbar.make(findViewById(R.id.rootView), "Berhasil Menambah Data", Snackbar.LENGTH_SHORT).show();
                            }

                            @Override
                            public void Failed(String s) {
                                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }

    private void hideSoftkey(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
