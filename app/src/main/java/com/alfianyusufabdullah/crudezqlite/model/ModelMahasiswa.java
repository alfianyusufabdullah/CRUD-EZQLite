package com.alfianyusufabdullah.crudezqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jonesrandom on 1/2/18.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class ModelMahasiswa implements Parcelable {

    private int id;
    private String Nama;
    private String Nim;
    private String Semester;

    public ModelMahasiswa() {
    }

    public ModelMahasiswa(Parcel in) {
        id = in.readInt();
        Nama = in.readString();
        Nim = in.readString();
        Semester = in.readString();
    }

    public static final Creator<ModelMahasiswa> CREATOR = new Creator<ModelMahasiswa>() {
        @Override
        public ModelMahasiswa createFromParcel(Parcel in) {
            return new ModelMahasiswa(in);
        }

        @Override
        public ModelMahasiswa[] newArray(int size) {
            return new ModelMahasiswa[size];
        }
    };

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getNim() {
        return Nim;
    }

    public void setNim(String nim) {
        Nim = nim;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(Nama);
        parcel.writeString(Nim);
        parcel.writeString(Semester);
    }
}
