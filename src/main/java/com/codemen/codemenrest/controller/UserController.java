package com.codemen.codemenrest.controller;

import com.codemen.codemenrest.entity.User;
import com.codemen.codemenrest.entity.UserModelAssembler;
import com.codemen.codemenrest.entity.dto.UserDto;
import com.codemen.codemenrest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    @Autowired
    private UserService userService;

    private final PagedResourcesAssembler pagedResourcesAssembler;

    private final UserModelAssembler userModelAssembler;

//    @GetMapping
//    public List<User> findAll() {
//
//        return userService.findAll();
//    }

    @GetMapping
    public ResponseEntity findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            List<User> users = new ArrayList<User>();
            Pageable paging = PageRequest.of(page, size);

            Page<User> pageTuts = userService.findAll(paging);

            return ResponseEntity
                    .ok()
                    .contentType(MediaTypes.HAL_JSON)
                    .body(pagedResourcesAssembler.toModel(pageTuts, userModelAssembler));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.CREATED);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@Valid @RequestBody UserDto user) {
       return new ResponseEntity<>(userService.save(user.toUser()), HttpStatus.CREATED);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.save(user);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/find/username/{username}")
    public List<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
}
