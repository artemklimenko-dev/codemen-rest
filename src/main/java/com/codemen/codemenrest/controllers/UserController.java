package com.codemen.codemenrest.controllers;

import com.codemen.codemenrest.entities.User;
import com.codemen.codemenrest.entities.UserModelAssembler;
import com.codemen.codemenrest.entities.dto.UserDto;
import com.codemen.codemenrest.exceptions.UserNotFoundException;
import com.codemen.codemenrest.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {
    @Autowired
    private UserService userService;

    private final PagedResourcesAssembler pagedResourcesAssembler;

    private final UserModelAssembler userModelAssembler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<PagedModel<User>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required=false, defaultValue = "") String name) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<User> pagedData;
            if(!name.isEmpty()) {
                pagedData = userService.findByNameContaining(name, paging);
            } else {
                pagedData = userService.findAll(paging);
            }
            return ResponseEntity
                    .ok()
                    .contentType(MediaTypes.HAL_JSON)
                    .body(pagedResourcesAssembler.toModel(pagedData, userModelAssembler));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@Valid @RequestBody UserDto user) {
       return new ResponseEntity<>(userService.save(user.toUser()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid UserDto user) {
        return new ResponseEntity<>(userService.update(id, user.toUser()), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/find/username/{username}")
    public ResponseEntity<List<User>> findByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }
}
