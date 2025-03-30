package com.SpringProj.todo.Controllers;


import com.SpringProj.todo.DTOs.TaskDTOs.TaskCreateDto;
import com.SpringProj.todo.DTOs.TaskDTOs.TaskReadDto;
import com.SpringProj.todo.DTOs.TaskDTOs.TaskUpdateDto;
import com.SpringProj.todo.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {


    @GetMapping("/get-all")
    public ResponseEntity<List<TaskReadDto>> getAllTasks(@AuthenticationPrincipal User user,
                                                         @RequestParam(required = false) String category,
                                                         @RequestParam(required = false) String search,
                                                         @RequestParam(required = false) String sort,
                                                         @RequestParam(defaultValue = "1") int page
                                                         )
    {
        return null;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TaskReadDto> getById(@AuthenticationPrincipal User user, @PathVariable Long id)
    {
        return null;
    }


    @GetMapping("/getByCategoryId")
    public ResponseEntity<List<TaskReadDto>> getByCategoryId(@AuthenticationPrincipal User user,
                                                             @RequestParam Long categoryId)
    {
        return null;
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<List<TaskReadDto>> getByStatus(@AuthenticationPrincipal User user, String status)
    {
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<TaskReadDto> addTask(@RequestBody TaskCreateDto taskCreateDto,
                                           @AuthenticationPrincipal User user)
    {
        return null;
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<TaskReadDto> updateTask(@RequestParam Long id,
                                                  @RequestBody TaskUpdateDto taskUpdateDto,
                                                  @AuthenticationPrincipal User user)
    {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id)
    {
        return null;
    }



    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<TaskReadDto> changeTaskStatus(@PathVariable Long id, @RequestParam String status
            ,@AuthenticationPrincipal User user)
    {
        return null;
    }

}
