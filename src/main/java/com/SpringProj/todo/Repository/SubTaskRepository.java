package com.SpringProj.todo.Repository;

import com.SpringProj.todo.Model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    Optional<SubTask> findByTitle(String title);

    @Query("SELECT S from SubTask S where  S.task.id = :taskId")
    Optional<List<SubTask>> findByTaskId(@Param("taskId") Long taskId);

    @Query("SELECT S from SubTask S where S.parentSubTask.id = :parentId")
    Optional<List<SubTask>> findByParentId(@Param("parentId") Long parentId);

}
