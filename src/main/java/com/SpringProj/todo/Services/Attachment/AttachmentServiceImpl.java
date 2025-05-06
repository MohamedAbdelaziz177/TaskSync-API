package com.SpringProj.todo.Services.Attachment;

import com.SpringProj.todo.DTOs.AttachmentDTOs.AttachmentCreateDto;
import com.SpringProj.todo.Model.*;
import com.SpringProj.todo.Repository.SubTaskRepository;
import com.SpringProj.todo.Repository.SubtaskAttachmentRepository;
import com.SpringProj.todo.Repository.TaskAttachmentRepository;
import com.SpringProj.todo.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final TaskRepository taskRepository;
    private final SubTaskRepository subTaskRepository;
    private final TaskAttachmentRepository taskAttachmentRepository;
    private final SubtaskAttachmentRepository subtaskAttachmentRepository;

    private static String StorageFolder = "F:\\SpringSec\\toDoFiles";

    private String getPath(MultipartFile inputFile) throws IOException {

        if(inputFile == null || inputFile.isEmpty())
            throw new NullPointerException("File is null or empty");

        File targetFile = new File(StorageFolder + File.separator + inputFile.getOriginalFilename());

        if(!Objects.equals(targetFile.getParentFile().getAbsolutePath(), StorageFolder))
            throw new SecurityException("Unsupported file name");

        Files.copy(inputFile.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return targetFile.getAbsolutePath();

    }

    public void saveTaskAttachment(Long taskId, AttachmentCreateDto attachment) throws IOException {

        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new NoSuchElementException("Task not found"));

        MultipartFile inputFile = attachment.getFile();

        String path = getPath(inputFile);

        String fileName = attachment.getFileName();


        TaskAttachment taskAttachment = new TaskAttachment();
        taskAttachment.setTask(task);
        taskAttachment.setFilePath(path);
        taskAttachment.setFileName(fileName);

        //--------------------------two approaches which each has its pros & cons-------------------------------//
        taskAttachmentRepository.save(taskAttachment);

        //task.getTaskAttachments().add(taskAttachment);
        //taskRepository.save(task);
    }

    public void saveSubTaskAttachment(Long taskId, AttachmentCreateDto attachment) throws IOException {

        SubTask subTask = subTaskRepository.findById(taskId).orElseThrow(() ->
                new NoSuchElementException("SubTask not found"));

        MultipartFile inputFile = attachment.getFile();
        String path = getPath(inputFile);
        String fileName = attachment.getFileName();



        SubtaskAttachment subTaskAttachment = new SubtaskAttachment();
        subTaskAttachment.setSubtask(subTask);
        subTaskAttachment.setFilePath(path);
        subTaskAttachment.setFileName(fileName);

        subtaskAttachmentRepository.save(subTaskAttachment);

        //subTask.getSubtaskAttachments().add(subTaskAttachment);
        //subTaskRepository.save(subTask);
    }

    public List<TaskAttachment> getTaskAttachmentsByTaskId(Long taskId) {

        return taskAttachmentRepository.findByTask_Id(taskId).orElseThrow(() ->
                new NoSuchElementException("Task not found"));
    }

    public List<SubtaskAttachment> getSubtaskAttachmentsBySubtaskId(Long subTaskId) {

        return subtaskAttachmentRepository.findBySubtaskId(subTaskId).orElseThrow(() ->
                new NoSuchElementException("SubTask not found"));
    }

    public void deleteTaskAttachment(Long attachmentId)  {

        TaskAttachment taskAttachment = taskAttachmentRepository.findById(attachmentId).orElseThrow(
                () -> new NoSuchElementException("TaskAttachment not found"));

        taskAttachmentRepository.delete(taskAttachment);

    }

    public void deleteSubtaskAttachment(Long attachmentId)  {

        SubtaskAttachment subtaskAttachment = subtaskAttachmentRepository.findById(attachmentId).orElseThrow(
                () -> new NoSuchElementException("TaskAttachment not found"));

        subtaskAttachmentRepository.delete(subtaskAttachment);

    }

}
