package com.muzeffereli.springbootphonebookapi.controller;

import com.muzeffereli.springbootphonebookapi.dto.ResponseDto;
import com.muzeffereli.springbootphonebookapi.dto.UserDto;
import com.muzeffereli.springbootphonebookapi.entity.UserEntity;
import com.muzeffereli.springbootphonebookapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public List<UserEntity> getAll(){
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public ResponseDto add(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @PutMapping("/edit")
    public ResponseDto edit(@RequestBody UserEntity entity){
        return userService.editUser(entity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable(name = "id") UUID id){
        return userService.deleteUser(id);
    }

}
