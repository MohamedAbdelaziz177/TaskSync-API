package com.SpringProj.todo.Repository;

import com.SpringProj.todo.Model.SubtaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubtaskAttachmentRepository extends JpaRepository<SubtaskAttachment, Long> {

    @Query("SELECT S from SubtaskAttachment S where  S.subtask.id = :subtaskId")
    Optional<List<SubtaskAttachment>> findBySubtaskId(@Param("subtaskId") Long subtaskId);
}
