package com.SpringProj.todo.Controllers;

import com.SpringProj.todo.DTOs.TaskDTOs.TaskReadDto;
import com.SpringProj.todo.Model.Category;
import com.SpringProj.todo.Model.Task;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Responses.ApiResponse;
import com.SpringProj.todo.Services.Category.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add") // only for admins / analysis purposes for EX
    public ResponseEntity<ApiResponse<String>> addCategory(String category)
    {
        ApiResponse<String> res = new ApiResponse<>();

        try {

            categoryService.addCategory(category);

            res.setSuccess(Boolean.TRUE);
            res.setData(category + " added successfully");

            return ResponseEntity.ok(res);
        }
        catch (Exception e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PostMapping("/assign-to-user")
    public ResponseEntity<ApiResponse<String>> addUserCategory(@AuthenticationPrincipal User user, String category)
    {
        ApiResponse<String> res = new ApiResponse<>();

        try {
            categoryService.addUserCategory(user, category);

            res.setSuccess(Boolean.TRUE);
            res.setData(category + " added successfully");

            return ResponseEntity.ok(res);
        }
        catch (Exception e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/get-by-user")
    public ResponseEntity<ApiResponse<List<String>>> getUserCategories(@AuthenticationPrincipal User user)
    {
        ApiResponse<List<String>> res = new ApiResponse<>();

        try {

            List<String> categories = categoryService.getUserCategories(user);
            res.setData(categories);
            res.setSuccess(Boolean.TRUE);

            return ResponseEntity.ok(res);
        }
        catch (Exception e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/get-all") // only for admins / analysis purposes for EX
    public ResponseEntity<ApiResponse<List<String>>> getAllCategories()
    {
        ApiResponse<List<String>> res = new ApiResponse<>();

        try {

            List<String> categories = categoryService.getAllCategories();

            res.setData(categories);
            res.setSuccess(Boolean.TRUE);

            return ResponseEntity.ok(res);
        }
        catch (Exception e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/get-tasks") // only for admins / analysis purposes for EX
    public ResponseEntity<ApiResponse<List<TaskReadDto>>> getAllTasksByCategory(Long id)
    {
        ApiResponse<List<TaskReadDto>> res = new ApiResponse<>();

        try {

            List<Task> tasks = categoryService.getAllTasksByCategory(id);
            List<TaskReadDto> tasksDto = new ArrayList<>();

            for (Task task : tasks)
                tasksDto.add(new TaskReadDto(task));

            res.setData(tasksDto);
            res.setSuccess(Boolean.TRUE);

            return ResponseEntity.ok(res);

        }
        catch (EntityNotFoundException e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch (Exception e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("delete-user-category")
    public ResponseEntity<ApiResponse<String>> deleteUserCategory(@AuthenticationPrincipal User user, Long id)
    {
        ApiResponse<String> res = new ApiResponse<>();

        try {
            categoryService.deleteUserCategory(user, id);
            res.setSuccess(Boolean.TRUE);
            res.setData("Category deleted successfully");
            return ResponseEntity.ok(res);
        }
        catch (EntityNotFoundException e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch (Exception e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("delete-category")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@AuthenticationPrincipal User user, Long id)
    {
        ApiResponse<String> res = new ApiResponse<>();

        try {

            categoryService.deleteCategory(id);
            res.setSuccess(Boolean.TRUE);
            res.setData("Category deleted successfully");

            return ResponseEntity.ok(res);
        }
        catch (EntityNotFoundException e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        catch (Exception e)
        {
            res.setSuccess(Boolean.FALSE);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }

    }

}
