package com.alfianyusufabdullah.crudezqlite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alfianyusufabdullah.crudezqlite.model.ModelMahasiswa;
import com.alfianyusufabdullah.crudezqlite.R;

import java.util.List;

/**
 * Created by jonesrandom on 1/2/18.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class AdapterDaftarMahasiswa extends RecyclerView.Adapter<HolderDaftarMahasiswa> {

    private List<ModelMahasiswa> data;
    private OnItemClickListener clickListener;

    public AdapterDaftarMahasiswa(List<ModelMahasiswa> data, OnItemClickListener clickListener) {
        this.data = data;
        this.clickListener = clickListener;
    }

    @Override
    public HolderDaftarMahasiswa onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mahasiswa, parent, false);
        return new HolderDaftarMahasiswa(v);
    }

    @Override
    public void onBindViewHolder(HolderDaftarMahasiswa holder, int position) {
        holder.setContent(data.get(position), position, clickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
