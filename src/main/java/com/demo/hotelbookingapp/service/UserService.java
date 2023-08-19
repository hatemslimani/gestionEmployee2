package com.demo.hotelbookingapp.service;

import com.demo.hotelbookingapp.model.User;
import com.demo.hotelbookingapp.model.dto.ResetPasswordDTO;
import com.demo.hotelbookingapp.model.dto.UserDTO;
import com.demo.hotelbookingapp.model.dto.UserRegistrationDTO;

import java.util.List;

public interface UserService {

    User saveUser(UserRegistrationDTO registrationDTO);

    // For registration
    User findUserByUsername(String username);

    UserDTO findUserDTOByUsername(String username);

    UserDTO findUserById(Long id);

    List<UserDTO> findAllUsers();

    void updateUser(UserDTO userDTO);

    void updateLoggedInUser(UserDTO userDTO);

    void deleteUserById(Long id);

    User resetPassword(ResetPasswordDTO resetPasswordDTO);

}
