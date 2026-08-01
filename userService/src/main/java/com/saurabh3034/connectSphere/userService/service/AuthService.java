package com.saurabh3034.connectSphere.userService.service;

import com.saurabh3034.connectSphere.userService.dto.LoginRequestDto;
import com.saurabh3034.connectSphere.userService.dto.SignupRequestDto;
import com.saurabh3034.connectSphere.userService.dto.UserDto;
import com.saurabh3034.connectSphere.userService.entity.User;
import com.saurabh3034.connectSphere.userService.exception.BadRequestException;
import com.saurabh3034.connectSphere.userService.exception.ResourceNotFoundException;
import com.saurabh3034.connectSphere.userService.repository.UserRepository;
import com.saurabh3034.connectSphere.userService.utils.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signup(SignupRequestDto signupRequestDto) {
        log.info("Signup a user with email: {}", signupRequestDto.getEmail());

        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());
        if (exists) {
            throw new BadRequestException("User with email already exists");
        }

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(BCrypt.hashPassword(signupRequestDto.getPassword()));
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) {
        log.info("Login a user with email: {}", loginRequestDto.getEmail());

        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new BadRequestException("Incorrect email or password"));

        boolean isPasswordMatch = BCrypt.match(loginRequestDto.getPassword(), user.getPassword());

        if (!isPasswordMatch) {
            throw new BadRequestException("Incorrect email or password");
        }

        return jwtService.generateAccessToken(user);
    }
}
