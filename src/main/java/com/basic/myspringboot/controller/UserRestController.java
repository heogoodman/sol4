package com.basic.myspringboot.controller;

import com.basic.myspringboot.dto.UserReqDTO;
import com.basic.myspringboot.dto.UserResDTO;
import com.basic.myspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping  // 값집어넣기
    public UserResDTO saveUser(@RequestBody UserReqDTO userReqDTO) {
        return userService.saveUser(userReqDTO);
    }

    @GetMapping("/{id}")  // id로 값조회하기
    public UserResDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResDTO> getUsers() {
        return userService.getUsers();
    }

    @PatchMapping("/{email}")    // 한개수정 PatchMapping 여러개수정 PutMapping
    public UserResDTO updateUser(@PathVariable String email, @RequestBody UserReqDTO userReqDTO) {
        return userService.updateUser(email,userReqDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(id + " User가 삭제처리 되었습니다.");
    }

}