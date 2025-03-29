package com.SpringProj.todo.Repository;

import com.SpringProj.todo.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTitle(String title);

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId")
    Optional<List<Task>> findByUserId(@Param("userId") Long userId);

    Optional<List<Task>> findByCategory_Id(Long id);

    Optional<List<Task>> findByUser_IdAndCategory_Id(Long userId, Long categoryId);

    Optional<List<Task>> findByUser_Id(Long userId);


}
