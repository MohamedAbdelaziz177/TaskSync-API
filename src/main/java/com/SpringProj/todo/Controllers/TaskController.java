package com.SpringProj.todo.Controllers;


import com.SpringProj.todo.DTOs.TaskDTOs.TaskCreateDto;
import com.SpringProj.todo.DTOs.TaskDTOs.TaskReadDto;
import com.SpringProj.todo.DTOs.TaskDTOs.TaskUpdateDto;
import com.SpringProj.todo.Enums.TaskStatus;
import com.SpringProj.todo.Model.Task;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Repository.TaskRepository;
import com.SpringProj.todo.Responses.ApiResponse;
import com.SpringProj.todo.Services.Task.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<TaskReadDto>>> getAllTasks(@AuthenticationPrincipal User user,
                                                                      @RequestParam(required = false) Long category,
                                                                      @RequestParam(required = false) String search,
                                                                      @RequestParam(required = false) String sort,
                                                                      @RequestParam(defaultValue = "1") int page
                                                         )
    {
        return null;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<TaskReadDto>> getById(@AuthenticationPrincipal User user, @PathVariable Long id)
    {


        ApiResponse<TaskReadDto> res = new ApiResponse<TaskReadDto>();

        try {

            Task task = taskService.getTask(id, user);

            res.setData(new TaskReadDto(task));
            res.setSuccess(Boolean.TRUE);

            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        catch (EntityNotFoundException e) {

            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch (AccessDeniedException e) {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        }


    }


    @GetMapping("/getByCategoryId")
    public ResponseEntity<ApiResponse<List<TaskReadDto>>> getByCategoryId(@AuthenticationPrincipal User user,
                                                             @RequestParam Long categoryId)
    {
        ApiResponse<List<TaskReadDto>> res = new ApiResponse<>();

        try {
            List<Task> tasks = taskService.getByCategoryId(categoryId, user);

            List<TaskReadDto> taskReadDtos = new ArrayList<>();

            for(Task task : tasks)
                taskReadDtos.add(new TaskReadDto(task));

            res.setSuccess(Boolean.TRUE);
            res.setData(taskReadDtos);

            return ResponseEntity.status(HttpStatus.OK).body(res);
        }

        catch (EntityNotFoundException e) {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

    }

    @GetMapping("/getByStatus")
    public ResponseEntity<ApiResponse<List<TaskReadDto>>> getByStatus(@AuthenticationPrincipal User user, String status)
    {
        ApiResponse<List<TaskReadDto>> res = new ApiResponse<>();

        try{

            List<Task> tasks = taskService.getByStatus(TaskStatus.valueOf(status), user);

            List<TaskReadDto> taskReadDtos = new ArrayList<>();

            for(Task task : tasks)
                taskReadDtos.add(new TaskReadDto(task));


            res.setData(taskReadDtos);
            res.setSuccess(Boolean.TRUE);

            return ResponseEntity.ok(res);

        }
        catch (EntityNotFoundException e) {

            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<TaskReadDto>> addTask(@RequestBody TaskCreateDto taskCreateDto,
                                           @AuthenticationPrincipal User user)
    {
        ApiResponse<TaskReadDto> res = new ApiResponse<>();

        try{

            Task task = taskService.addTask(taskCreateDto, user);

            TaskReadDto taskReadDto = new TaskReadDto(task);

            res.setData(taskReadDto);
            res.setSuccess(Boolean.TRUE);

            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }
        catch (IllegalArgumentException e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TaskReadDto>> updateTask(@RequestParam Long id,
                                                  @RequestBody TaskUpdateDto taskUpdateDto,
                                                  @AuthenticationPrincipal User user)
    {
        ApiResponse<TaskReadDto> res = new ApiResponse<>();

        try {

            Task task = taskService.updateTask(id, taskUpdateDto, user);
            TaskReadDto taskReadDto = new TaskReadDto(task);

            res.setData(taskReadDto);
            res.setSuccess(Boolean.TRUE);

            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        catch (AccessDeniedException e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        }
        catch (EntityNotFoundException e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id, @AuthenticationPrincipal User user)
    {
        try {

            taskService.deleteTask(id, user);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<ApiResponse<TaskReadDto>> changeTaskStatus(@PathVariable Long id, @RequestParam String status
            ,@AuthenticationPrincipal User user)
    {
        ApiResponse<TaskReadDto> res = new ApiResponse<>();
        try {
            TaskStatus ts;

            try {
                ts = TaskStatus.valueOf(status);
            }
           catch (IllegalArgumentException  e) {

                throw new BadRequestException(e.getMessage());
           }

            Task task = taskService.changeStatus(id, ts, user);

            TaskReadDto taskReadDto = new TaskReadDto(task);

            res.setData(taskReadDto);
            res.setSuccess(Boolean.TRUE);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
        }
        catch (EntityNotFoundException e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch (AccessDeniedException e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        }

        catch (BadRequestException e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

        catch (Exception e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }

    }

}
