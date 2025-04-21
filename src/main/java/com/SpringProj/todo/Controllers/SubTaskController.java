package com.SpringProj.todo.Controllers;


import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskCreateDto;
import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskReadDto;
import com.SpringProj.todo.DTOs.SubTaskDTOs.SubTaskUpdateDto;
import com.SpringProj.todo.Model.SubTask;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Responses.ApiResponse;
import com.SpringProj.todo.Services.Subtask.SubtaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/subtask")
@RequiredArgsConstructor
public class SubTaskController {

    private final SubtaskService subtaskService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<SubTaskReadDto>> getById(@AuthenticationPrincipal User user, @PathVariable Long id)
    {
        ApiResponse<SubTaskReadDto> res = new ApiResponse<>();

        try {

            SubTask subTask = subtaskService.getById(id, user);

            res.setData(new SubTaskReadDto(subTask));
            res.setSuccess(true);

            return ResponseEntity.ok(res);

        }
        catch (AccessDeniedException e) {

            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);

        }

        catch (EntityNotFoundException e) {

            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @GetMapping("/getByTaskId")
    public ResponseEntity<ApiResponse<SubTaskReadDto>> getByTaskId(@AuthenticationPrincipal User user, @RequestParam Long taskId)
    {
        ApiResponse<SubTaskReadDto> res = new ApiResponse<>();

        try {

            SubTask subTask = subtaskService.getById(taskId, user);
            res.setData(new SubTaskReadDto(subTask));
            res.setSuccess(true);

            return ResponseEntity.ok(res);

        }
        catch(EntityNotFoundException e) {

            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch (AccessDeniedException e) {

            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        }


    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<SubTaskReadDto>> addSubTask(@RequestBody SubTaskCreateDto subTaskCreateDto)
    {
        ApiResponse<SubTaskReadDto> res = new ApiResponse<>();

        try {

            subtaskService.addSubTask(subTaskCreateDto);

            res.setSuccess(true);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);

        }
        catch(EntityNotFoundException e) {

            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch (AccessDeniedException e) {

            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        }
    }

    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<ApiResponse<SubTaskReadDto>> changeSubTaskStatus(@PathVariable Long id, @RequestParam String status
            ,@AuthenticationPrincipal User user)
    {
        ApiResponse<SubTaskReadDto> res = new ApiResponse<>();

        try {

            SubTask subTask = subtaskService.ChangeStatus(id, status, user);
            res.setData(new SubTaskReadDto(subTask));
            res.setSuccess(true);

            return ResponseEntity.ok(res);
        }
        catch (EntityNotFoundException e) {

            res.setSuccess(false);
            res.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch (AccessDeniedException e) {
            res.setSuccess(false);
            res.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<SubTaskReadDto>> updateTask(@RequestParam Long id,
                                                  @RequestBody SubTaskUpdateDto subTaskUpdateDto,
                                                  @AuthenticationPrincipal User user)
    {
        ApiResponse<SubTaskReadDto> res = new ApiResponse<>();

        try{

            SubTask subtask = subtaskService.updateSubTask(id, subTaskUpdateDto, user);

            res.setData(new SubTaskReadDto(subtask));
            res.setSuccess(true);

            return ResponseEntity.ok(res);
        }
        catch(EntityNotFoundException e) {
            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch (AccessDeniedException e) {
            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        }
    }


    @DeleteMapping("/delete/{id}")
      public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id, @AuthenticationPrincipal User user)
    {
        ApiResponse<Void> res = new ApiResponse<>();

        try {
            subtaskService.deleteSubTask(id, user);
            res.setSuccess(true);
            return ResponseEntity.ok(res);
        }
        catch (EntityNotFoundException e) {

            res.setSuccess(false);
            res.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch (AccessDeniedException e) {
            res.setSuccess(false);
            res.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);

        }
    }

}
