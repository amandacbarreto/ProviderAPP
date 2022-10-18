package com.challengeme.provider.service;

import com.challengeme.provider.dto.ProviderDTO2;
import com.challengeme.provider.dto.ProviderResponseListDTO;
import com.challengeme.provider.entity.Provider;
import com.challengeme.provider.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public List<ProviderDTO2> findAll(){
        List<ProviderDTO2> providerList = new ArrayList<>();
        ProviderDTO2 providerDTO = new ProviderDTO2();
        for (Provider provider: providerRepository.findAll()){
            providerList.add(providerDTO.convertProviderToDTO(provider));
        }
        return providerList;
    }

    public Provider findById(String id){
        return providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
    }

    public void deleteById(String id){
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
        providerRepository.delete(provider);
    }

    public Provider insert (Provider provider) {
        if (provider.getId().isEmpty()){
            Provider newProvider = new Provider(
                    provider.getName(),
                    provider.getNameContact(),
                    provider.getEmailContact(),
                    provider.getPersonType(),
                    provider.getCpfOrCnpj(),
                    provider.getActivityDescription(),
                    provider.getAddress(),
                    provider.getPhoneList()
            );
            return providerRepository.save(newProvider);
        }
        return providerRepository.save(provider);
    }

    public Provider update (String id, Provider provider){
        Provider providerUpdate = this.findById(id);
        providerUpdate.setName(provider.getName());
        providerUpdate.setAddress(provider.getAddress());
        providerUpdate.setPhoneList(provider.getPhoneList());
        return providerRepository.save(providerUpdate);
    }

}
