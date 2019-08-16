package com.lambdaschool.todoer.repository;


import com.lambdaschool.todoer.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
