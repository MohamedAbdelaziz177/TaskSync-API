package com.SpringProj.todo.Repository;

import com.SpringProj.todo.Model.SubtaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubtaskAttachmentRepository extends JpaRepository<SubtaskAttachment, Long> {

    Optional<List<SubtaskAttachment>> findBySubtaskId(Long subtaskId);
}
