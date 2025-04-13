package com.SpringProj.todo.Services.Subtask;

import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskCreateDto;
import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskUpdateDto;
import com.SpringProj.todo.Enums.TaskStatus;
import com.SpringProj.todo.Model.SubTask;
import com.SpringProj.todo.Model.Task;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Repository.SubTaskRepository;
import com.SpringProj.todo.Repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor

public class SubtaskServiceImpl {

    private final SubTaskRepository subTaskRepository;
    private final TaskRepository taskRepository;

    public SubTask getById(Long id)
    {
        return subTaskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Subtask not found"));
    }


    public List<SubTask> getByTaskId(Long id)
    {
        return subTaskRepository.findByTask_Id(id).orElseThrow(() ->
                new EntityNotFoundException("Subtask not found"));


    }

    public SubTask addSubTask(SubTaskCreateDto subTaskCreateDto)
    {
        Task task = taskRepository.findById(subTaskCreateDto.getTaskId()).orElseThrow(() ->
                new EntityNotFoundException("TaskId is not valid"));

        SubTask subTask = new SubTask();

        subTask.setTask(task);
        subTask.setDescription(subTaskCreateDto.getDescription());
        subTask.setStatus(TaskStatus.valueOf(subTaskCreateDto.getStatus()));
        subTask.setTitle(subTaskCreateDto.getTitle());

        return subTaskRepository.save(subTask);
    }

    public SubTask updateSubTask(Long id, SubTaskUpdateDto subTaskUpdateDto)
    {
        Task task = taskRepository.findById(subTaskUpdateDto.getTaskId()).orElseThrow(() ->
                new EntityNotFoundException("TaskId is not valid"));

        SubTask subTask = subTaskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Subtask is not found"));

        subTask.setTask(task);
        subTask.setDescription(subTaskUpdateDto.getDescription());
        subTask.setStatus(TaskStatus.valueOf(subTaskUpdateDto.getStatus()));
        subTask.setTitle(subTaskUpdateDto.getTitle());

        return subTaskRepository.save(subTask);
    }

    public void deleteSubTask(Long id)
    {
        SubTask subTask = subTaskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Subtask is not found"));

        subTaskRepository.delete(subTask);
    }
}
