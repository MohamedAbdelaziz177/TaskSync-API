package com.SpringProj.todo.Repository;

import com.SpringProj.todo.Model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    Optional<SubTask> findByTitle(String title);
    Optional<List<SubTask>> findByTask_Id(Long id);


}
