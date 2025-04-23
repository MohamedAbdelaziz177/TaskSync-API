package com.SpringProj.todo.Services.Attachment;

import com.SpringProj.todo.DTOs.AttachmentDTOs.AttachmentCreateDto;
import com.SpringProj.todo.Model.SubtaskAttachment;
import com.SpringProj.todo.Model.TaskAttachment;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {

    void saveTaskAttachment(Long taskId, AttachmentCreateDto attachment) throws IOException;
    void saveSubTaskAttachment(Long taskId, AttachmentCreateDto attachment) throws IOException;
    List<TaskAttachment> getTaskAttachmentsByTaskId(Long taskId);
    List<SubtaskAttachment> getSubtaskAttachmentsBySubtaskId(Long subTaskId);
    void deleteTaskAttachment(Long attachmentId);
    void deleteSubtaskAttachment(Long attachmentId);
}
