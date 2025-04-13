package com.SpringProj.todo.Services.Subtask;

import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskCreateDto;
import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskUpdateDto;
import com.SpringProj.todo.Model.SubTask;

import java.util.List;

public interface SubtaskService {

    SubTask getById(Long id);
    List<SubTask> getByTaskId(Long id);
    SubTask addSubTask(SubTaskCreateDto subTaskCreateDto);
    SubTask updateSubTask(SubTaskUpdateDto subTaskUpdateDto);
    void deleteSubTask(Long Id);

}
