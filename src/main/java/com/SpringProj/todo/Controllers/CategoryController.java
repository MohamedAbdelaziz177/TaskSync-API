package com.SpringProj.todo.Controllers;

import com.SpringProj.todo.DTOs.TaskDTOs.TaskReadDto;
import com.SpringProj.todo.Model.Task;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Responses.ApiResponse;
import com.SpringProj.todo.Services.Category.CategoryService;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseEntity<ApiResponse<String>> addCategory(@RequestParam String category)
    {
        ApiResponse<String> res = new ApiResponse<>();

        categoryService.addCategory(category);

        res.setSuccess(Boolean.TRUE);
        res.setData(category + " added successfully");

        return ResponseEntity.ok(res);

    }

    @PostMapping("/assign-to-user")
    public ResponseEntity<ApiResponse<String>> addUserCategory(@AuthenticationPrincipal User user, @RequestParam String category)
    {
        ApiResponse<String> res = new ApiResponse<>();

        categoryService.addUserCategory(user, category);

        res.setSuccess(Boolean.TRUE);
        res.setData(category + " added successfully");

        return ResponseEntity.ok(res);

    }

    @GetMapping("/get-by-user")
    public ResponseEntity<ApiResponse<List<String>>> getUserCategories(@AuthenticationPrincipal User user)
    {
        ApiResponse<List<String>> res = new ApiResponse<>();

        List<String> categories = categoryService.getUserCategories(user);
        res.setData(categories);
        res.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(res);

    }

    @GetMapping("/get-all") // only for admins / analysis purposes for EX
    public ResponseEntity<ApiResponse<List<String>>> getAllCategories()
    {
        ApiResponse<List<String>> res = new ApiResponse<>();

        List<String> categories = categoryService.getAllCategories();

        res.setData(categories);
        res.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(res);

    }

    @GetMapping("/get-tasks-by-category") // only for admins / analysis purposes for EX
    public ResponseEntity<ApiResponse<List<TaskReadDto>>> getAllTasksByCategory(@RequestParam Long id) {
        ApiResponse<List<TaskReadDto>> res = new ApiResponse<>();

        List<Task> tasks = categoryService.getAllTasksByCategory(id);
        List<TaskReadDto> tasksDto = new ArrayList<>();

        for (Task task : tasks)
            tasksDto.add(new TaskReadDto(task));

        res.setData(tasksDto);
        res.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(res);


    }

    @DeleteMapping("delete-user-category/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUserCategory(@AuthenticationPrincipal User user, @PathVariable Long id)
    {
        ApiResponse<String> res = new ApiResponse<>();

        categoryService.deleteUserCategory(user, id);
        res.setSuccess(Boolean.TRUE);
        res.setData("Category deleted successfully");
        return ResponseEntity.ok(res);

    }

    @DeleteMapping("delete-category/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@AuthenticationPrincipal User user, @PathVariable Long id)
    {
        ApiResponse<String> res = new ApiResponse<>();

        categoryService.deleteCategory(id);
        res.setSuccess(Boolean.TRUE);
        res.setData("Category deleted successfully");

        return ResponseEntity.ok(res);

    }

}
