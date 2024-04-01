package com.tmav2.repo;

import com.tmav2.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//
@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

}
