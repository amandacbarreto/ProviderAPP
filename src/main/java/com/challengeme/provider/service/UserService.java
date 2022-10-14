package com.challengeme.provider.service;

import com.challengeme.provider.dto.UserDTO;
import com.challengeme.provider.entity.User;
import com.challengeme.provider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public void deleteById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
    }

    public User insert (UserDTO user) {
        User newUser = new User(this.findAll().size()+1L, user.getName(), user.getEmail(), user.getPassword());
        return userRepository.insert(newUser);
    }

    public User update (Long id, UserDTO userDTO){
        User user = this.findById(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

}