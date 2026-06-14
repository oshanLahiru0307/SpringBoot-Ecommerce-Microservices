package org.ecommerce.userservice.Controller;

import org.ecommerce.userservice.DTO.UserRequestDTO;
import org.ecommerce.userservice.DTO.UserResponseDTO;
import org.ecommerce.userservice.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //get all users
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers(){
        List<UserResponseDTO> users = userService.getAllUsers();
        if(users == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(users);
    }

    //get single user by id
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Integer userId){
        UserResponseDTO response = userService.getUserById(userId);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }

    //save new user with details
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO user){
        UserResponseDTO response = userService.createUser(user);
        if(response == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(response);
    }

    //delete user by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer userId){
        String response = userService.deleteUserById(userId);
        if(response.contains("not found")){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }

    //update user details by id
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Integer id, @RequestBody UserRequestDTO user){
        UserResponseDTO response = userService.updateUserById(id, user);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }
}
