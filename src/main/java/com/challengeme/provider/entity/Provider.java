package com.challengeme.provider.entity;

import com.challengeme.provider.enums.PersonType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class Provider {

    @Id
    private Long id;

    private String name;
    private String nameContact;
    private String emailContact;
    private PersonType personType;
    private String cpfOrCnpj;
    private String activityDescription;
    private Address address;
    private List<Phone> phoneList = new ArrayList<>();

    public Provider(){

    }

    public Provider(Long id, String name, String nameContact, String emailContact, PersonType personType, String cpfOrCnpj, String activityDescription, Address address, List<Phone> phoneList) {
        this.id = id;
        this.name = name;
        this.nameContact = nameContact;
        this.emailContact = emailContact;
        this.personType = personType;
        this.cpfOrCnpj = cpfOrCnpj;
        this.activityDescription = activityDescription;
        this.address = address;
        this.phoneList = phoneList;
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

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(id, provider.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
