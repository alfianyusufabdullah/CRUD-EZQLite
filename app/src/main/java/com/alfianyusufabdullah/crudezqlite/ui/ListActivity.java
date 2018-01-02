package com.alfianyusufabdullah.crudezqlite.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.alfianyusufabdullah.crudezqlite.model.ModelMahasiswa;
import com.alfianyusufabdullah.crudezqlite.R;
import com.alfianyusufabdullah.crudezqlite.adapter.AdapterDaftarMahasiswa;
import com.alfianyusufabdullah.crudezqlite.adapter.OnItemClickListener;
import com.alfianyusufabdullah.crudezqlite.common.DatabaseConfig;

import java.util.ArrayList;
import java.util.List;

import alfianyusufabdullah.ezqlite.EZQLite;
import alfianyusufabdullah.ezqlite.listener.OnDatabaseCallback;
import alfianyusufabdullah.ezqlite.listener.OnDatabaseCursorCallback;

public class ListActivity extends AppCompatActivity implements OnItemClickListener {

    RecyclerView listMahasiswa;

    List<ModelMahasiswa> dataMahasiswa = new ArrayList<>();
    AdapterDaftarMahasiswa adapterDaftarMahasiswa;

    EZQLite ezqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ezqLite = EZQLite.getInstance(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Daftar Mahasiswa");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        adapterDaftarMahasiswa = new AdapterDaftarMahasiswa(dataMahasiswa, this);

        initView();
    }

    @Override
    public void Click(View v, final ModelMahasiswa mahasiswa, final int pos) {

        PopupMenu popupMenu = new PopupMenu(ListActivity.this, v, Gravity.END);
        popupMenu.inflate(R.menu.row_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_delete:

                        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                        builder.setTitle("Hapus Data");
                        builder.setMessage("Apakah Kamu Ingin Menghapus Data " + mahasiswa.getNama() + "?");
                        builder.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String whereClause = DatabaseConfig.COL_ID + "=" + mahasiswa.getId();

                                ezqLite.doRemove(DatabaseConfig.TABEL_NAME)
                                        .whereClause(whereClause)
                                        .remove(new OnDatabaseCallback() {
                                            @Override
                                            public void Success() {

                                                dataMahasiswa.remove(pos);
                                                adapterDaftarMahasiswa.notifyDataSetChanged();

                                                showSnackbar("Berhasil Menghapus Data");

                                            }

                                            @Override
                                            public void Failed(String s) {
                                                showSnackbar(s);
                                            }
                                        });

                            }
                        });
                        builder.setNegativeButton("BATAL", null);
                        builder.create().show();

                        break;
                    case R.id.menu_update:

                        Intent update = new Intent(ListActivity.this , UpdateActivity.class);
                        update.putExtra("DATA" , mahasiswa);

                        startActivity(update);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        dataMahasiswa.clear();

        ezqLite.doLoad(DatabaseConfig.TABEL_NAME)
                .load(new OnDatabaseCursorCallback() {
                    @Override
                    public void Success(Cursor cursor) {

                        if (cursor.moveToFirst()) {
                            do {
                                ModelMahasiswa mahasiswa = new ModelMahasiswa();
                                mahasiswa.setId(cursor.getInt(DatabaseConfig.INDEX_COL_ID));
                                mahasiswa.setNama(cursor.getString(DatabaseConfig.INDEX_COL_NAMA));
                                mahasiswa.setNim(cursor.getString(DatabaseConfig.INDEX_COL_NIM));
                                mahasiswa.setSemester(cursor.getString(DatabaseConfig.INDEX_COL_SEMESTER));

                                dataMahasiswa.add(mahasiswa);
                            } while (cursor.moveToNext());

                            adapterDaftarMahasiswa.notifyDataSetChanged();
                        }
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

    private void initView() {
        listMahasiswa = findViewById(R.id.listMahasiswa);
        listMahasiswa.setHasFixedSize(true);
        listMahasiswa.setLayoutManager(new LinearLayoutManager(this));
        listMahasiswa.setAdapter(adapterDaftarMahasiswa);
    }

    private void showSnackbar(String text) {
        Snackbar.make(listMahasiswa, text, Snackbar.LENGTH_SHORT).show();
    }
}
