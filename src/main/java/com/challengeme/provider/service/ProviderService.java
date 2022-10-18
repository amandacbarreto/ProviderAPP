package com.challengeme.provider.service;

import com.challengeme.provider.dto.ProviderDTO;
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

    public List<ProviderDTO> findAll(){
        List<ProviderDTO> providerList = new ArrayList<>();
        ProviderDTO providerDTO = new ProviderDTO();
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

    public Provider insert (ProviderDTO providerDTO) {
        Provider newProvider = new Provider(
                providerDTO.getName(),
                providerDTO.getNameContact(),
                providerDTO.getEmailContact(),
                providerDTO.getPersonType(),
                providerDTO.getCpfOrCnpj(),
                providerDTO.getActivityDescription(),
                providerDTO.getAddress(),
                providerDTO.getPhoneList()
        );
        if (providerDTO.getId().isEmpty()){
            return providerRepository.save(newProvider);
        }
        newProvider.setId(providerDTO.getId());
        return providerRepository.save(newProvider);
    }

    public Provider update (String id, Provider provider){
        Provider providerUpdate = this.findById(id);
        providerUpdate.setName(provider.getName());
        providerUpdate.setAddress(provider.getAddress());
        providerUpdate.setPhoneList(provider.getPhoneList());
        return providerRepository.save(providerUpdate);
    }

}
