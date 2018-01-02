package com.alfianyusufabdullah.crudezqlite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alfianyusufabdullah.crudezqlite.model.ModelMahasiswa;
import com.alfianyusufabdullah.crudezqlite.R;

/**
 * Created by jonesrandom on 1/2/18.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class HolderDaftarMahasiswa extends RecyclerView.ViewHolder {

    private TextView rowNamaMahasiswa, rowNimMahasiswa, rowSemesterMahasiswa;

    public HolderDaftarMahasiswa(View itemView) {
        super(itemView);

        rowNamaMahasiswa = itemView.findViewById(R.id.rowNamaMahasiswa);
        rowNimMahasiswa = itemView.findViewById(R.id.rowNimMahasiswa);
        rowSemesterMahasiswa = itemView.findViewById(R.id.rowSemesterMahasiswa);

    }

    public void setContent(final ModelMahasiswa mahasiswa, final int position, final OnItemClickListener clickListener) {

        rowNamaMahasiswa.setText(mahasiswa.getNama());
        rowNimMahasiswa.setText(mahasiswa.getNim());
        rowSemesterMahasiswa.setText("Semester " + mahasiswa.getSemester());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.Click(view, mahasiswa, position);
            }
        });
    }
}
