package com.dh.clinica.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> searchedUser = userRepository.findByEmail(email);
        if(searchedUser.isPresent()){
            return searchedUser.get();
        } else{
            throw new UsernameNotFoundException("Email de usuario no encontrado");
        }
        //return userRepository.findByEmail(email).orElseThrow((() -> new UsernameNotFoundException("Username not found")));
    }

    public AppUser saveUser(AppUser user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public Optional<AppUser> findUserById(Long id){
        return userRepository.findById(id);
    }

    public AppUser updateUser(AppUser user) {
        return userRepository.save(user);
    }
}
