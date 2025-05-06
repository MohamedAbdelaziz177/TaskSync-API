package com.SpringProj.todo.Repository;

import com.SpringProj.todo.Model.TaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {

    Optional<List<TaskAttachment>> findByTask_Id(Long id);
}
