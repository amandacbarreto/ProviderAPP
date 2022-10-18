package com.challengeme.provider.service;

import com.challengeme.provider.dto.UserDTO;
import com.challengeme.provider.entity.User;
import com.challengeme.provider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public void deleteById(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
    }

    public User insert (UserDTO user) {
        User newUser = new User(user.getName(), user.getEmail(), passwordEncoder().encode(user.getPassword()));
        return userRepository.insert(newUser);
    }

    public Boolean isNewUser(UserDTO user){
        if (userRepository.findByEmail(user.getEmail())==null){
            return true;
        }
        return false;
    }

    public User update (String id, UserDTO userDTO){
        User user = this.findById(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

}
