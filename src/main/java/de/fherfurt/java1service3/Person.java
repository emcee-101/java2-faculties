package de.fherfurt.java1service3;

public class Person {

    public Faculty(String name, String mail, Int phNumber, String role) {
        this.name = name;
        this.mail = mail;
        this.phNumber = phNumber;
        this.role = role;
    }

    private String name;
    private String mail;
    private Int phNumber;
    private String role;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Int getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(Int phNumber) {
        this.phNumber = phNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}