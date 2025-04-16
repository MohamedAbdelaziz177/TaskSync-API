package com.SpringProj.todo.Controllers;

import com.SpringProj.todo.Model.Category;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Responses.ApiResponse;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addCategory(String category)
    {return null;}

    @PostMapping("/assign-to-user")
    public ResponseEntity<ApiResponse<String>> addUserCategory(@AuthenticationPrincipal User user, String category)
    {return null;}

    @GetMapping("/get-by-user")
    public ResponseEntity<ApiResponse<List<String>>> getUserCategories(@AuthenticationPrincipal User user)
    {return null;}

    @GetMapping("/get-all") // only for admins / analysis purposes for eg
    public ResponseEntity<ApiResponse<List<String>>> getAllCategories()
    {return null;}

    @GetMapping("/get-tasks")
    public ResponseEntity<ApiResponse<List<String>>> getAllTasksByCategory(Long id)
    {return null;}

    @DeleteMapping("delete-user-category")
    public ResponseEntity<ApiResponse<String>> deleteUserCategory(@AuthenticationPrincipal User user, Long id)
    {return null;}

}
