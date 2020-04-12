package com.epam.lab.controller;

import com.epam.lab.dto.UserTo;
import com.epam.lab.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
@Validated
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    private static final String WRONG_ID_MESSAGE = "Id must be greater than 0!";

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserTo getUserById(@PathVariable("id") @Positive(message = WRONG_ID_MESSAGE) long id) {
        UserTo result = userService.findById(id);
        return result;
    }

    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserTo> getAllUsers() {
        List<UserTo> resultList = userService.findAll();
        return resultList;
    }

    @GetMapping(value = "/count")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long getCountUsers() {
        long count = userService.countAll();
        return count;
    }

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserTo postUser(@RequestBody @Validated UserTo userTo) {
        UserTo savedTag = userService.save(userTo);
        return savedTag;
    }

    @PutMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserTo putUser(@RequestBody @Validated UserTo userTo) {
        UserTo savedTag = userService.save(userTo);
        return savedTag;
    }

    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean deleteUser(@PathVariable("id") @Positive(message = WRONG_ID_MESSAGE) long id) {
        boolean isDelete = userService.delete(id);
        return isDelete;
    }
}
