package com.aryanonline.Model;


public class My_Locatio_model {

    String id;
    String pg_title;
    String pg_slug;
    String pg_descri;
    String pg_status;
    String pg_foot;
    String crated_date;

    public My_Locatio_model(String pg_descri,String pg_title) {
        this.pg_descri = pg_descri;
        this.pg_title = pg_title;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPg_title() {
        return pg_title;
    }

    public void setPg_title(String pg_title) {
        this.pg_title = pg_title;
    }

    public String getPg_slug() {
        return pg_slug;
    }

    public void setPg_slug(String pg_slug) {
        this.pg_slug = pg_slug;
    }

    public String getPg_descri() {
        return pg_descri;
    }

    public void setPg_descri(String pg_descri) {
        this.pg_descri = pg_descri;
    }

    public String getPg_status() {
        return pg_status;
    }

    public void setPg_status(String pg_status) {
        this.pg_status = pg_status;
    }

    public String getPg_foot() {
        return pg_foot;
    }

    public void setPg_foot(String pg_foot) {
        this.pg_foot = pg_foot;
    }

    public String getCrated_date() {
        return crated_date;
    }

    public void setCrated_date(String crated_date) {
        this.crated_date = crated_date;
    }
}
