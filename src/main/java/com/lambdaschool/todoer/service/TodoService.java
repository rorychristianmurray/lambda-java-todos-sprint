package com.lambdaschool.todoer.service;

import com.lambdaschool.todoer.model.Todo;
import com.lambdaschool.todoer.view.CountTodos;

import java.util.ArrayList;
import java.util.List;

public interface TodoService
{
    List<Todo> findAll();

    Todo findTodoById(long id);

    List<Todo> findByUserName (String username);

    void delete(long id);

    Todo save(Todo todo);

    Todo update(Todo quote, long id);

    ArrayList<CountTodos> getCountTodos();
}
