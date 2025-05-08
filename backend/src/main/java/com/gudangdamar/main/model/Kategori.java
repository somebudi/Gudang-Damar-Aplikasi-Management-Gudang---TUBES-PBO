package com.gudangdamar.main.model;

public class Kategori{
    private float ukuran;
    private String bentuk;
    private float ketebalan;
    private String bahan;
    private String merek;

    public Kategori(float ukuran, String bentuk, float ketebalan, String bahan, String merek){
        this.ukuran=ukuran;
        this.bentuk=bentuk;
        this.ketebalan=ketebalan;
        this.bahan=bahan;
        this.merek=merek;
    }

    public void setUkuran(float ukuran){
        this.ukuran=ukuran;
    }

    public float getUkuran(){
        return ukuran;
    }

    public void setBentuk(String bentuk){
        this.bentuk=bentuk;
    }

    public String getBentuk(){
        return bentuk;
    }

    public void setKetebalan(float ketebalan){
        this.ketebalan=ketebalan;
    }

    public float getketebalan(){
        return ketebalan;
    }

    public void setBahan(String bahan){
        this.bahan=bahan;
    }

    public String getBahan(){
        return bahan;
    }

    public void setMerek(String merek){
        this.merek=merek;
    }

    public String getMerek(){
        return merek;
    }
}