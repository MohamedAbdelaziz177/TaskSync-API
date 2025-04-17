package com.SpringProj.todo.Services.Category;

import com.SpringProj.todo.Model.Task;
import com.SpringProj.todo.Model.User;

import java.util.List;

public interface CategoryService {

    void addCategory(String category);
    void addUserCategory(User user, String category);
    List<String> getUserCategories(User user);
    List<String> getAllCategories();
    List<Task> getAllTasksByCategory(Long id);
    void deleteUserCategory(User user, Long categoryId);
    void deleteCategory(Long id);


}
