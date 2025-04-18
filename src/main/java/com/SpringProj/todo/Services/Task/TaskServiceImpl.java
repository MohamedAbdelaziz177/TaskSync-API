package com.SpringProj.todo.Services.Task;

import com.SpringProj.todo.DTOs.TaskDTOs.TaskCreateDto;
import com.SpringProj.todo.DTOs.TaskDTOs.TaskReadDto;
import com.SpringProj.todo.DTOs.TaskDTOs.TaskUpdateDto;
import com.SpringProj.todo.Enums.SortType;
import com.SpringProj.todo.Enums.TaskPriority;
import com.SpringProj.todo.Enums.TaskStatus;
import com.SpringProj.todo.Model.Category;
import com.SpringProj.todo.Model.Task;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Repository.CategoryRepository;
import com.SpringProj.todo.Repository.TaskRepository;
import com.SpringProj.todo.Responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public Task addTask(TaskCreateDto taskCreateDto, User user)
    {

        Task task = new Task();

        Optional<Category> category = categoryRepository.findById(taskCreateDto.getCategoryId());

        if(category.isEmpty())
            throw new IllegalArgumentException("Category not found");


        task.setPriority(TaskPriority.valueOf(taskCreateDto.getPriority()));
        task.setStatus(TaskStatus.valueOf(taskCreateDto.getStatus()));
        task.setDescription(taskCreateDto.getDescription());
        task.setUser(user);
        task.setDeadline(taskCreateDto.getDeadline());
        task.setTitle(taskCreateDto.getTitle());
        task.setCategory(category.get());

        return taskRepository.save(task);
    }


    public void deleteTask(Long id, User user) {

        Task task = taskRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Task not found"));

        if(Objects.equals(task.getUser().getId(), user.getId()))
            taskRepository.delete(task);

        else throw new AccessDeniedException("You cannot delete this task");
    }

    public Task changeStatus(Long id, TaskStatus newStatus, User user) {

        Task task = taskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Task not found"));

        if(task.getUser() != user)
            throw new AccessDeniedException("You cannot Edit this task");

        task.setStatus(newStatus);

        return taskRepository.save(task);

    }


    public Task updateTask(Long id, TaskUpdateDto taskUpdateDto, User user) {

        Task task = taskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Task not found"));

        if(Objects.equals(task.getUser().getId(), user.getId()))
            throw new AccessDeniedException("You cannot Edit this task");

        task.setPriority(TaskPriority.valueOf(taskUpdateDto.getPriority()));
        task.setStatus(TaskStatus.valueOf(taskUpdateDto.getStatus()));
        task.setDescription(taskUpdateDto.getDescription());
        task.setTitle(taskUpdateDto.getTitle());
        task.setStatus(TaskStatus.valueOf(taskUpdateDto.getStatus()));


        taskRepository.save(task);

        return task;

    }

    public Task getTask(Long id, User user) {

        Task task = taskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Task not found"));

        if(Objects.equals(task.getUser().getId(), user.getId()))
            throw new AccessDeniedException("You cannot Get this task");

        return task;
    }

    public List<Task> getByStatus(TaskStatus status, User user) {

        List<Task> tasks = taskRepository.findByUser_IdAndStatus(user.getId(), status).orElseThrow(
                () -> new EntityNotFoundException("Task not found"));

        return tasks;
    }

    public List<Task> getByCategoryId(Long categoryId, User user) {

        List<Task> tasks = taskRepository.findByUser_IdAndCategory_Id(user.getId(), categoryId).orElseThrow(
                () -> new EntityNotFoundException("Task not found"));

        return tasks;
    }
}



