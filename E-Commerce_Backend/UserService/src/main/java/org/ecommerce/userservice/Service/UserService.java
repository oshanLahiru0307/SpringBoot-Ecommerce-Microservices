package org.ecommerce.userservice.Service;

import org.ecommerce.userservice.DTO.UserRequestDTO;
import org.ecommerce.userservice.DTO.UserResponseDTO;
import org.ecommerce.userservice.Model.UserEntity;
import org.ecommerce.userservice.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserService(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    //get all users service method
    public List<UserResponseDTO> getAllUsers(){
        List<UserEntity> users =  userRepo.findAll();
        return modelMapper.map(users, new TypeToken<List<UserResponseDTO>>(){}.getType());
    }

    //get single user by id
    public UserResponseDTO getUserById(Integer userId){
        UserEntity user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return null; // or throw an exception
        }
        return modelMapper.map(user, UserResponseDTO.class);
    }

    //create new user service method
    public UserResponseDTO createUser(UserRequestDTO user){
        try{
            UserEntity newUser = modelMapper.map(user, UserEntity.class);
            UserEntity savedUser = userRepo.save(newUser);
            return modelMapper.map(savedUser, UserResponseDTO.class);
        }catch(Exception e){
            return null;
        }
    }

    //delete user by id
    public String deleteUserById(Integer userId){
        if(userRepo.existsById(userId)){
            userRepo.deleteById(userId);
            return "user deleted successfully";
        }
        return "user not found with id: " + userId;
    }

    //update user details by id
    public UserResponseDTO updateUserById(Integer userId, UserRequestDTO user){
        UserEntity userResult = userRepo.findById(userId).orElse(null);
        if(userResult != null){
            userResult.setName(user.getName());
            userResult.setEmail(user.getEmail());
            userResult.setPassword(user.getPassword());
            userResult.setPhone(user.getPhone());
            UserEntity savedUser = userRepo.save(userResult);
            return modelMapper.map(userResult, UserResponseDTO.class);
        }
        return null; // or throw an exception
    }
}
