package com.lambdaschool.todoer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.lambdaschool.todoer.model.Todo;
import com.lambdaschool.todoer.model.User;
import com.lambdaschool.todoer.service.TodoService;
import com.lambdaschool.todoer.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> getUser(@PathVariable Long userid)
    {
        User user = userService.findUserById(userid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping(value = "/mine", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(Authentication authentication)
    {
        return new ResponseEntity<>(userService.findUserByName(authentication.getName()), HttpStatus.OK);
    }

    @PostMapping(value = "/todo/{userid}")
    public ResponseEntity<?> addNewTodo(@Valid @RequestBody Todo newTodo, @PathVariable int userid) throws URISyntaxException {
        todoService.save(new Todo(newTodo.getDescription(), new Date(), userService.findUserById(userid)));
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @PutMapping(value = "/user/{userid}")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long userid)
    {
        userService.update(updateUser, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/userid/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
