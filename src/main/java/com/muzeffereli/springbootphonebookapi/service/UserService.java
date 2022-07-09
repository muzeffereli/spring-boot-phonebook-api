package com.muzeffereli.springbootphonebookapi.service;

import com.muzeffereli.springbootphonebookapi.dto.ResponseDto;
import com.muzeffereli.springbootphonebookapi.dto.UserDto;
import com.muzeffereli.springbootphonebookapi.entity.UserEntity;
import com.muzeffereli.springbootphonebookapi.repository.UserRepository;
import com.muzeffereli.springbootphonebookapi.utils.OperationStatus;
import com.muzeffereli.springbootphonebookapi.utils.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseDto addUser(UserDto dto) {
        var userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID());
        userEntity.setName(dto.getName());
        userEntity.setPhone(dto.getPhone());
        var savedUser = userRepository.save(userEntity);
        var user = userRepository.findById(savedUser.getUserId());
        return user.map(u -> new ResponseDto(userEntity.getUserId(), OperationType.ADD, OperationStatus.SUCCESS))
                .orElse(new ResponseDto(null, OperationType.ADD, OperationStatus.FAIL));
    }

    @Transactional
    public ResponseDto editUser(UserEntity entity) {
        var user = userRepository.findById(entity.getUserId());
        return user.map(u -> {
            u.setUserId(entity.getUserId());
            u.setName(entity.getName());
            u.setPhone(entity.getPhone());
            return new ResponseDto(entity.getUserId(), OperationType.EDIT, OperationStatus.SUCCESS);
        }).orElse(new ResponseDto(null, OperationType.EDIT, OperationStatus.FAIL));
    }

    @Transactional
    public ResponseDto deleteUser(UUID id) {
        var user = userRepository.findById(id);
        return user.map(u -> {
            userRepository.delete(u);
            return new ResponseDto(u.getUserId(), OperationType.DELETE, OperationStatus.SUCCESS);
        }).orElse(new ResponseDto(null, OperationType.DELETE, OperationStatus.FAIL));
    }
}
