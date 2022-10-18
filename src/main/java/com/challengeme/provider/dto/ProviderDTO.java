package com.challengeme.provider.dto;

import com.challengeme.provider.entity.Address;
import com.challengeme.provider.entity.Phone;
import com.challengeme.provider.entity.Provider;
import com.challengeme.provider.enums.PersonType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class ProviderDTO {

    private String id;
    private String name;
    private String nameContact;
    private String emailContact;
    private PersonType personType;
    private String cpfOrCnpj;
    private String activityDescription;
    private Address address;
    private List<Phone> phoneList = new ArrayList<>();

    private String shortAddress;

    private String shortPhoneList;

    public ProviderDTO(){
    }

    public ProviderDTO(String id, String name, String nameContact, String emailContact, PersonType personType, String cpfOrCnpj, String activityDescription, Address address, List<Phone> phoneList) {
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

    public String getShortAddress() {
        return shortAddress;
    }

    public void setShortAddress() {
        StringBuilder newAddress = new StringBuilder();
        Address addressProvider = this.getAddress();
        newAddress.append(addressProvider.getStreet());
        newAddress.append(", ");
        newAddress.append(addressProvider.getNumber());
        if (!addressProvider.getReference().isEmpty()){
            newAddress.append(", ");
            newAddress.append(addressProvider.getReference());
        }
        if (!addressProvider.getComplement().isEmpty()){
            newAddress.append(", ");
            newAddress.append(addressProvider.getComplement());
        }
        newAddress.append(", ");
        newAddress.append(addressProvider.getNeighborhood());
        newAddress.append(". CEP: ");
        newAddress.append(addressProvider.getCep());
        newAddress.append(". ");
        newAddress.append(addressProvider.getCity());
        newAddress.append("-");
        newAddress.append(addressProvider.getState());
        newAddress.append(". ");
        this.shortAddress = newAddress.toString();
    }

    public String getShortPhoneList() {
        return shortPhoneList;
    }

    public void setShortPhoneList() {
        StringBuilder phones = new StringBuilder();
        for (Phone phone: this.getPhoneList()) {
            phones.append(phone.getContact());
            phones.append(";\n");
        }
        this.shortPhoneList = phones.toString();
    }

    public ProviderDTO convertProviderToDTO (Provider provider){
        ProviderDTO providerDto = new ProviderDTO();
        providerDto.id = provider.getId();
        providerDto.name = provider.getName();
        providerDto.nameContact = provider.getNameContact();
        providerDto.emailContact = provider.getEmailContact();
        providerDto.personType = provider.getPersonType();
        providerDto.cpfOrCnpj = provider.getCpfOrCnpj();
        providerDto.activityDescription = provider.getActivityDescription();
        providerDto.address = provider.getAddress();
        providerDto.phoneList = provider.getPhoneList();
        providerDto.setShortPhoneList();
        providerDto.setShortAddress();
        return providerDto;
    }

}
