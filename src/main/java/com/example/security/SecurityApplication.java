package com.example.security;

//import org.springframework.boot.CommandLineRunner;
import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
//    @Bean
//    CommandLineRunner runner(UserService userService){
//        return args -> {
//            userService.saveRole(new Role(null,"USER"));
//            userService.saveRole(new Role(null,"ADMIN"));
//            userService.saveRole(new Role(null,"MANAGER"));
//            userService.saveRole(new Role(null,"SUPPER_ADMIN"));
//
//            userService.saveUser(new User(null,"Truong Cong Toan","truongtoan","truongtoan","123456",new HashSet<>()));
//            userService.saveUser(new User(null,"Truong Cong Tien","truongtien","truongtien","123456",new HashSet<>()));
//
//            userService.addRoleToUser("truongtoan","ADMIN");
//            userService.addRoleToUser("truongtien","USER");
//        };
//    }

}
