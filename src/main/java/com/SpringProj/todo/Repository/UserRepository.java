package com.SpringProj.todo.Repository;

import com.SpringProj.todo.Model.Category;
import com.SpringProj.todo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   // @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u.categories FROM User u WHERE u.id = :userId")

    Optional<List<Category>> findUserCategories(@Param("userId") Long userId);


}
