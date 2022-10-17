package com.challengeme.provider.dto;

import com.challengeme.provider.entity.Address;
import com.challengeme.provider.entity.Phone;
import com.challengeme.provider.entity.Provider;
import com.challengeme.provider.enums.PersonType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class ProviderResponseListDTO {

    private String id;
    private String name;
    private String nameContact;
    private String emailContact;
    private PersonType personType;
    private String cpfOrCnpj;
    private String activityDescription;
    private String address;
    private String phoneList;

    public ProviderResponseListDTO(){

    }

    public ProviderResponseListDTO(String id, String name, String nameContact, String emailContact, PersonType personType, String cpfOrCnpj, String activityDescription, String address, String phoneList) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(String phoneList) {
        this.phoneList = phoneList;
    }

    public ProviderResponseListDTO convertProviderToDTO (Provider provider){
        ProviderResponseListDTO providerDto = new ProviderResponseListDTO();
        providerDto.id = provider.getId();
        providerDto.name = provider.getName();
        providerDto.nameContact = provider.getNameContact();
        providerDto.emailContact = provider.getEmailContact();
        providerDto.personType = provider.getPersonType();
        providerDto.cpfOrCnpj = provider.getCpfOrCnpj();
        providerDto.activityDescription = provider.getActivityDescription();
        StringBuilder address = new StringBuilder();
        Address addressProvider = provider.getAddress();
        address.append(addressProvider.getStreet());
        address.append(", ");
        address.append(addressProvider.getNumber());
        if (!addressProvider.getReference().isEmpty()){
            address.append(", ");
            address.append(addressProvider.getReference());
        }
        if (!addressProvider.getComplement().isEmpty()){
            address.append(", ");
            address.append(addressProvider.getComplement());
        }
        address.append(", ");
        address.append(addressProvider.getNeighborhood());
        address.append(". CEP: ");
        address.append(addressProvider.getCep());
        address.append(". ");
        address.append(addressProvider.getCity());
        address.append("-");
        address.append(addressProvider.getState());
        address.append(". ");
        providerDto.address = address.toString();
        StringBuilder phones = new StringBuilder();
        for (Phone phone: provider.getPhoneList()) {
            phones.append(phone.getContact());
            phones.append(";\n");
        }
        providerDto.phoneList = phones.toString();
        return providerDto;
    }
}
