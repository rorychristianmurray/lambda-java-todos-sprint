package com.lambdaschool.todoer.controller;

import com.lambdaschool.todoer.model.Todo;
import com.lambdaschool.todoer.service.TodoService;
import com.lambdaschool.todoer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController
{
    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;

    @PutMapping(value = "/todoid/{todoid}")
    public ResponseEntity<?> updateTodo(@RequestBody Todo updateTodo, @PathVariable long todoid)
{
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    updateTodo.setUser(userService.findUserByName(authentication.getName()));
    todoService.update(updateTodo, todoid);
    return new ResponseEntity<>(updateTodo, HttpStatus.OK);
}


//    @PutMapping(value = "/todoid/{todoid}")
//    public ResponseEntity<?> updateTodo(
//            @RequestBody
//                    Todo updateTodo,
//            @PathVariable
//                    long todoid)
//    {
//        todoService.update(updateTodo, todoid);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}