package com.SpringProj.todo.Controllers;

import com.SpringProj.todo.DTOs.AttachmentDTOs.AttachmentCreateDto;
import com.SpringProj.todo.DTOs.AttachmentDTOs.AttachmentReadDto;
import com.SpringProj.todo.Model.SubtaskAttachment;
import com.SpringProj.todo.Model.TaskAttachment;
import com.SpringProj.todo.Responses.ApiResponse;
import com.SpringProj.todo.Services.Attachment.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachment")
public class AttachmentController {

    // get by TaskId - get by subtaskId - assign to a task - assign to sub task - delete
    // ربنا يسهل

    private final AttachmentService attachmentService;

    @GetMapping("/get-by-task-id")
    public ResponseEntity<ApiResponse<List<AttachmentReadDto>>> getByTaskId(@RequestParam Long taskId)
    {
        ApiResponse<List<AttachmentReadDto>> res = new ApiResponse<>();

        List<TaskAttachment> attachments = attachmentService.getTaskAttachmentsByTaskId(taskId);

        List<AttachmentReadDto> attachmentReadDtos = new ArrayList<>();

        for (TaskAttachment attachment : attachments)
            attachmentReadDtos.add(new AttachmentReadDto(attachment));

        res.setData(attachmentReadDtos);
        res.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(res);

    }

    @GetMapping("/get-by-subtask-id")
    public ResponseEntity<ApiResponse<List<AttachmentReadDto>>> getBySubtaskId(@RequestParam Long subtaskId)
    {
        ApiResponse<List<AttachmentReadDto>> res = new ApiResponse<>();

        List<SubtaskAttachment> attachments = attachmentService.getSubtaskAttachmentsBySubtaskId(subtaskId);

        List<AttachmentReadDto> attachmentReadDtos = new ArrayList<>();

        for (SubtaskAttachment attachment : attachments)
            attachmentReadDtos.add(new AttachmentReadDto(attachment));

        res.setData(attachmentReadDtos);
        res.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(res);

    }

    @PostMapping("/add-to-task")
    public ResponseEntity<ApiResponse<AttachmentReadDto>> addToTask(@RequestParam Long taskId,
                                                                    @RequestBody AttachmentCreateDto attachmentCreateDto)
            throws IOException
    {
        ApiResponse<AttachmentReadDto> res = new ApiResponse<>();

        attachmentService.saveTaskAttachment(taskId, attachmentCreateDto);
        res.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(res);

    }

    @PostMapping("/add-to-subtask")
    public ResponseEntity<ApiResponse<AttachmentReadDto>> addToSubtask(@RequestParam Long subtaskId,
                                                                    @RequestBody AttachmentCreateDto attachmentCreateDto)
            throws IOException

    {
        ApiResponse<AttachmentReadDto> res = new ApiResponse<>();

        attachmentService.saveSubTaskAttachment(subtaskId, attachmentCreateDto);
        res.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(res);

    }

    @DeleteMapping("/delete-taskAttachment-by-id/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTaskAttachmentById(@PathVariable Long id)
    {
        ApiResponse<Void> res = new ApiResponse<>();

        attachmentService.deleteTaskAttachment(id);
        res.setSuccess(Boolean.TRUE);
        return ResponseEntity.ok(res);

    }

    @DeleteMapping("/delete-subtaskAttachment-by-id/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSubtaskAttachmentById(@PathVariable Long id)
    {
        ApiResponse<Void> res = new ApiResponse<>();

        attachmentService.deleteSubtaskAttachment(id);

        res.setSuccess(Boolean.TRUE);
        return ResponseEntity.ok(res);

    }

}
