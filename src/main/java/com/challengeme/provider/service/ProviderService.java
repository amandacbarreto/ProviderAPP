package com.challenge.challenge.service;

import com.challenge.challenge.entity.Provider;
import com.challenge.challenge.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public List<Provider> findAll(){
        return providerRepository.findAll();
    }

    public Provider findById(Long id){
        return providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
    }

    public void deleteById(Long id){
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
        providerRepository.delete(provider);
    }

    public Provider insert (Provider provider) {
        Provider newProvider = new Provider(this.findAll().size()+1L, provider.getName(), provider.getAddress(), provider.getPhoneList());
        return providerRepository.insert(newProvider);
    }

    public Provider update (Long id, Provider provider){
        Provider providerUpdate = this.findById(id);
        providerUpdate.setName(provider.getName());
        providerUpdate.setAddress(provider.getAddress());
        providerUpdate.setPhoneList(provider.getPhoneList());
        return providerRepository.save(providerUpdate);
    }

}
