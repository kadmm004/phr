package com.phr.entity;

public class Drug {
    public Drug(String drugname) {
        this.drugname = drugname;
    }

    public Drug(String drugid, String drugname, String vertinum, String ingredient, String mainfunc, String dosage, String adverseaction, String taboo, String attention, String standards, String productor, String category, double price) {
        this.drugid = drugid;
        this.drugname = drugname;
        this.vertinum = vertinum;
        this.ingredient = ingredient;
        this.mainfunc = mainfunc;
        this.dosage = dosage;
        this.adverseaction = adverseaction;
        this.taboo = taboo;
        this.attention = attention;
        this.standards = standards;
        this.productor = productor;
        this.category = category;
        this.price = price;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    private  String drugid;
    private  String drugname;
    private  String vertinum;
    private  String ingredient;
    private  String mainfunc;
    private  String dosage;
    private  String adverseaction;
    private  String taboo;
    private  String attention;
    private  String standards;
    private  String productor;
    private  String category;
    private  double price;

    public String getDrugid() {
        return drugid;
    }

    public void setDrugid(String drugid) {
        this.drugid = drugid;
    }

    public String getVertinum() {
        return vertinum;
    }

    public void setVertinum(String vertinum) {
        this.vertinum = vertinum;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMainfunc() {
        return mainfunc;
    }

    public void setMainfunc(String mainfunc) {
        this.mainfunc = mainfunc;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getAdverseaction() {
        return adverseaction;
    }

    public void setAdverseaction(String adverseaction) {
        this.adverseaction = adverseaction;
    }

    public String getTaboo() {
        return taboo;
    }

    public void setTaboo(String taboo) {
        this.taboo = taboo;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
