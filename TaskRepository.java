
package com.rakshithacodes.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakshithacodes.todo.model.Task;

public interface TaskRepository extends JpaRepository < Task,Long> {
    
}
