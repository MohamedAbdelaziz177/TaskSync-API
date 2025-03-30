package com.SpringProj.todo.Controllers;


import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskCreateDto;
import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskReadDto;
import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskUpdateDto;
import com.SpringProj.todo.DTOs.TaskDTOs.TaskReadDto;
import com.SpringProj.todo.DTOs.TaskDTOs.TaskUpdateDto;
import com.SpringProj.todo.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/subtask")
public class SubTaskController {

    @GetMapping("/getById/{id}")
    public ResponseEntity<SubTaskReadDto> getById(@AuthenticationPrincipal User user, @PathVariable Long id)
    {
        return null;
    }

    @GetMapping("/getByTaskId")
    public ResponseEntity<SubTaskReadDto> getByTaskId(@AuthenticationPrincipal User user, @RequestParam Long id)
    {
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<SubTaskReadDto> addSubTask(@AuthenticationPrincipal User user,
                                                     @RequestBody SubTaskCreateDto subTaskCreateDto)
    {
        return null;
    }

    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<SubTaskReadDto> changeSubTaskStatus(@PathVariable Long id, @RequestParam String status
            ,@AuthenticationPrincipal User user)
    {
        return null;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubTaskReadDto> updateTask(@RequestParam Long id,
                                                  @RequestBody SubTaskUpdateDto subTaskUpdateDto,
                                                  @AuthenticationPrincipal User user)
    {
        return null;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @AuthenticationPrincipal User user)
    {
        return null;
    }


}
