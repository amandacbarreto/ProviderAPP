package com.challengeme.provider.dto;

import com.challengeme.provider.entity.Address;
import com.challengeme.provider.entity.Phone;
import com.challengeme.provider.enums.PersonType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class ProviderDTO {

    private String name;
    private String nameContact;
    private String emailContact;
    private PersonType personType;
    private String cpfOrCnpj;
    private String activityDescription;
    private Address address;
    private List<Phone> phoneList = new ArrayList<>();

    public ProviderDTO(){
    }

    public ProviderDTO(String name, String nameContact, String emailContact, PersonType personType, String cpfOrCnpj, String activityDescription, Address address, List<Phone> phoneList) {
        this.name = name;
        this.nameContact = nameContact;
        this.emailContact = emailContact;
        this.personType = personType;
        this.cpfOrCnpj = cpfOrCnpj;
        this.activityDescription = activityDescription;
        this.address = address;
        this.phoneList = phoneList;
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

}
