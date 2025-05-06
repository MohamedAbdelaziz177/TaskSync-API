package com.SpringProj.todo.Services.Subtask;

import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskCreateDto;
import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskUpdateDto;
import com.SpringProj.todo.Enums.TaskStatus;
import com.SpringProj.todo.Model.SubTask;
import com.SpringProj.todo.Model.Task;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Repository.SubTaskRepository;
import com.SpringProj.todo.Repository.TaskRepository;
import com.SpringProj.todo.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
@Transactional
public class SubtaskServiceImpl implements SubtaskService {

    private final SubTaskRepository subTaskRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public SubTask getById(Long id, User user)
    {
        if(checkAuthorized(id, user))
        return subTaskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Subtask not found"));

        else throw new AccessDeniedException("Access denied");
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

    public SubTask updateSubTask(Long id, SubTaskUpdateDto subTaskUpdateDto, User user)
    {
        if(!checkAuthorized(id, user))
            throw new AccessDeniedException("Access denied");


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

    @Transactional
    public void deleteSubTask(Long id, User user)
    {
        if(!checkAuthorized(id, user))
            throw new AccessDeniedException("Access denied");


        SubTask subTask = subTaskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Subtask is not found"));

        subTaskRepository.delete(subTask);
    }

    public SubTask ChangeStatus(Long id, String status, User user)
    {
        SubTask subTask = subTaskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Subtask is not found"));

        Task task = taskRepository.findById(subTask.getTask().getId()).orElseThrow(() ->
                new EntityNotFoundException("Task is not found"));

        if(task.getUser() != user)
            throw new AccessDeniedException("Access denied");


        subTask.setStatus(TaskStatus.valueOf(status));

        return subTaskRepository.save(subTask);
    }

    private boolean checkAuthorized(Long id, User user)
    {
        SubTask subTask = subTaskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Subtask is not found"));

        User managedUser = getManagedUser(user).orElseThrow(() ->
                new AccessDeniedException("User not found"));

        return Objects.equals(subTask.getTask().getUser().getId(), managedUser.getId());

    }

    private Optional<User> getManagedUser(User user)
    {
        return userRepository.findById(user.getId());
    }


}
