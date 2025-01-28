package com.learning.TaskMngGraphQl.repository;

import com.learning.TaskMngGraphQl.entity.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {
    Flux<Task> findByUserId(Long userId);
}
