package com.SpringProj.todo.Services.Category;

import com.SpringProj.todo.Model.Category;
import com.SpringProj.todo.Model.Task;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Repository.CategoryRepository;
import com.SpringProj.todo.Repository.UserRepository;
import com.SpringProj.todo.Responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public void addCategory(String category)
    {
        Category category1 = new Category();
        category1.setCategory(category);

        categoryRepository.save(category1);

    }

    public void addUserCategory(User user, String category)
    {
        Category category1 = new Category();
        category1.setCategory(category);

        user.getCategories().add(category1);

        categoryRepository.save(category1);
        userRepository.save(user);
    }

    public List<String> getUserCategories(User user)
    {
        List<String> categories = new ArrayList<>();

        for (Category category : user.getCategories())
            categories.add(category.getCategory());

        return categories;
    }

    public List<String> getAllCategories()
    {
        List<String> categories = new ArrayList<>();

        for (Category category : categoryRepository.findAll())
            categories.add(category.getCategory());

        return categories;
    }

    public List<Task> getAllTasksByCategory(Long id)
    {
        List<Task> tasks = new ArrayList<>();

        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Category not found"));

        return category.getTasks();
    }

    public void deleteUserCategory(User user, Long categoryId)
    {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->
                new EntityNotFoundException("Category not found"));

        user.getCategories().remove(category);

        userRepository.save(user);
    }

    public void deleteRepository(Long id)
    {
        Category category = categoryRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Category not found"));

        categoryRepository.deleteById(id);
    }
}
