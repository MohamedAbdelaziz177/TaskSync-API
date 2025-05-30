package com.SpringProj.todo.Repository;

import com.SpringProj.todo.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategory(String category);
}
