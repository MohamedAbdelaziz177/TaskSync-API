package com.SpringProj.todo.Services.Task;

import com.SpringProj.todo.DTOs.TaskDTOs.TaskCreateDto;
import com.SpringProj.todo.DTOs.TaskDTOs.TaskUpdateDto;
import com.SpringProj.todo.Enums.TaskStatus;
import com.SpringProj.todo.Model.Task;
import com.SpringProj.todo.Model.User;

import java.util.List;

public interface TaskService {

    Task addTask(TaskCreateDto taskCreateDto, User user);
    void deleteTask(Long id, User user);
    Task changeStatus(Long id, TaskStatus newStatus, User user);
    Task updateTask(Long id, TaskUpdateDto taskUpdateDto, User user);
    Task getTask(Long id, User user);
    List<Task> getByStatus(TaskStatus status, User user);
    List<Task> getByCategoryId(Long categoryId, User user);

   //public Task updateTask(TaskUpdateDto taskUpdateDto);
   //public Task deleteTask(int id);
   //public Task getTask(int id);
}
