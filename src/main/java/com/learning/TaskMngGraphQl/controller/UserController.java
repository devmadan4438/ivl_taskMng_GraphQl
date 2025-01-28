package com.learning.TaskMngGraphQl.controller;

import com.learning.TaskMngGraphQl.entity.Task;
import com.learning.TaskMngGraphQl.entity.User;
import com.learning.TaskMngGraphQl.repository.TaskRepository;
import com.learning.TaskMngGraphQl.repository.UserRepository;
import com.learning.TaskMngGraphQl.services.TaskService;
import com.learning.TaskMngGraphQl.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class UserController {
    private UserService userService;
    private TaskService taskService;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService,TaskService taskService,TaskRepository taskRepository,UserRepository userRepository){
        this.userService  = userService;
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @QueryMapping
    public Flux<User> getAllUsers(){
        return userService.getAllUser();
    }

    @QueryMapping
    public Mono<User> getUserById(@Argument Long id) {
        return userService.getUserById(id);
    }

    @QueryMapping
    public Flux<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @QueryMapping
    public Mono<Task> getTaskById(@Argument Long id) {
        return taskService.getTaskById(id);
    }

    @MutationMapping
    public Mono<User> createUser(@Argument String name,@Argument String email) {
        try {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            return userService.createUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @MutationMapping
    public Mono<Task> createTask(@Argument String description,@Argument Long userId) {
        Task task = new Task();
        task.setDescription(description);
        task.setUserId(userId);
        return taskService.createTask(task);
    }

    // This can be used for the User -> Tasks relationship
    @SchemaMapping
    public Flux<Task> tasks(User user) {
        return taskRepository.findByUserId(user.getId());
    }

    @SchemaMapping
    public Mono<User> user(Task task) {
        return userRepository.findById(task.getUserId());  // Fetch the User associated with the task
    }
}
