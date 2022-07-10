package com.dh.clinica.login;

import com.dh.clinica.persistence.entity.AppUser;
import com.dh.clinica.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = passwordEncoder.encode("password");
        String password2 = passwordEncoder.encode("password");

        //Estos métodos pueden estar en el service y luego en el controller para que se registre el usuario
        userRepository.save(new AppUser("Elena", "effm@mail.com", "effm", password, UserRol.ADMIN));
        userRepository.save(new AppUser("Alan", "alan@mail.com", "alan", password2, UserRol.USER));

        //De esta manera
        userRepository.save(new AppUser("aa", "aa@mail.com", "aa", passwordEncoder.encode("password"), UserRol.ADMIN));
    }
}
