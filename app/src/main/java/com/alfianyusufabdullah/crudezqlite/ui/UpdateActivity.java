package com.alfianyusufabdullah.crudezqlite.ui;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.alfianyusufabdullah.crudezqlite.R;
import com.alfianyusufabdullah.crudezqlite.common.DatabaseConfig;
import com.alfianyusufabdullah.crudezqlite.model.ModelMahasiswa;

import alfianyusufabdullah.ezqlite.EZQLite;
import alfianyusufabdullah.ezqlite.listener.OnDatabaseCallback;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout inputNama, inputNim, inputSemester;
    TextInputEditText etNama, etNim, etSemester;
    Button btnUpdateData;

    String textNama, textNim, textSemester;
    ModelMahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Update Data");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initView();
    }


    private void initView() {

        mahasiswa = getIntent().getParcelableExtra("DATA");

        textNama = mahasiswa.getNama();
        textNim = mahasiswa.getNim();
        textSemester = mahasiswa.getSemester();

        inputNama = findViewById(R.id.inputNama);
        inputNim = findViewById(R.id.inputNim);
        inputSemester = findViewById(R.id.inputSemester);

        etNama = findViewById(R.id.etNama);
        etNim = findViewById(R.id.etNim);
        etSemester = findViewById(R.id.etSemester);

        etNama.setText(textNama);
        etNim.setText(textNim);
        etSemester.setText(textSemester);

        btnUpdateData = findViewById(R.id.btnUpdateData);
        btnUpdateData.setOnClickListener(this);

    }

    private void hideSoftkey(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private void showSnackbar(String text) {
        Snackbar.make(findViewById(R.id.rootView), text, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

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

        String whereClause = DatabaseConfig.COL_ID + "=" + mahasiswa.getId();

        EZQLite.getInstance(UpdateActivity.this)
                .doUpdate(DatabaseConfig.TABEL_NAME)
                .whereClause(whereClause)
                .putValue(DatabaseConfig.COL_NAMA_MAHASISWA , textNama)
                .putValue(DatabaseConfig.COL_NIM_MAHASISWA , textNim)
                .putValue(DatabaseConfig.COL_SEMESTER_MAHASISWA , textSemester)
                .update(new OnDatabaseCallback() {
                    @Override
                    public void Success() {

                        hideSoftkey(etNama);
                        showSnackbar("Berhasil Mengupdate Data");

                    }

                    @Override
                    public void Failed(String s) {
                        showSnackbar(s);
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
