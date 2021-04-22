package com.pandragor.tec_iti3.models;

public class Alumno {
    String id;
    String persona;
    String tel;
    String email;
    String password;

    public Alumno() {
    }

    public Alumno(String id, String persona, String tel, String email, String password) {
        this.id = id;
        this.persona = persona;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
