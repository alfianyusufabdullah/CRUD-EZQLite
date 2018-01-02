package com.alfianyusufabdullah.crudezqlite.adapter;

import android.view.View;

import com.alfianyusufabdullah.crudezqlite.model.ModelMahasiswa;

/**
 * Created by jonesrandom on 1/2/18.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public interface OnItemClickListener {
    void Click(View v, ModelMahasiswa mahasiswa, int pos);
}
