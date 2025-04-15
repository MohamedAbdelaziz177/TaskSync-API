package com.SpringProj.todo.Services.Subtask;

import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskCreateDto;
import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskUpdateDto;
import com.SpringProj.todo.Model.SubTask;
import com.SpringProj.todo.Model.User;

import java.util.List;

public interface SubtaskService {

    SubTask getById(Long id, User user);
    List<SubTask> getByTaskId(Long id);
    SubTask addSubTask(SubTaskCreateDto subTaskCreateDto);
    SubTask updateSubTask(Long id, SubTaskUpdateDto subTaskUpdateDto, User user);
    void deleteSubTask(Long Id, User user);
    SubTask ChangeStatus(Long id, String status, User user);

}
