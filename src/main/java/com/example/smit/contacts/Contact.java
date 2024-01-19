package com.example.smit.contacts;

import jakarta.persistence.*;

@Entity
@Table
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String secretName;
    private String phoneNumber;

    public Contact() {}

    public Contact(Long id, String name, String secretName, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.secretName = secretName;
        this.phoneNumber = phoneNumber;
    }

    public Contact(String name, String secretName, String phoneNumber) {
        this.name = name;
        this.secretName = secretName;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretName() {
        return secretName;
    }

    public void setSecretName(String secretName) {
        this.secretName = secretName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secretCode='" + secretName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}
