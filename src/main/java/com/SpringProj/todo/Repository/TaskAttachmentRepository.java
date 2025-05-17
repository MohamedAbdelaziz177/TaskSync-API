package com.SpringProj.todo.Repository;

import com.SpringProj.todo.Model.TaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {

    @Query("SELECT S from TaskAttachment S where  S.task.id = :taskId")
    Optional<List<TaskAttachment>> findByTask_Id(@Param("taskId") Long taskId);
}
