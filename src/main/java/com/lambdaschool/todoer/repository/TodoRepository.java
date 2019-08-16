package com.lambdaschool.todoer.repository;


import com.lambdaschool.todoer.model.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
    @Query(value = "SELECT u.username, count(t.todoid) as counttodos FROM todos t JOIN users u on t.userid = u.userid GROUP BY u.username", nativeQuery = true)
    ArrayList<CountTodos> getCountTodos();
}