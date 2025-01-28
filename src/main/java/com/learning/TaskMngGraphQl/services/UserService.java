package com.learning.TaskMngGraphQl.services;

import com.learning.TaskMngGraphQl.entity.User;
import com.learning.TaskMngGraphQl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private UserRepository userRepository;

    // Constructor DI
    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Implement service

    public Flux<User> getAllUser(){
        return userRepository.findAll();
    }

    public Mono<User> getUserById(Long userId){
        return userRepository.findById(userId);
    }

    public Mono<User> createUser(User user){
        return userRepository.save(user);
    }

    public  Mono<User> updateUser(Long userId, User user){
        return userRepository.findById(userId)
                .flatMap(existingUser->{
                   existingUser.setName(user.getName());
                   existingUser.setEmail(user.getEmail());
                   return userRepository.save(existingUser);
                });
    }



}
