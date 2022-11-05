package com.rest.api.service.impl;

import com.rest.api.entity.User;
import com.rest.api.repository.UserRepository;
import com.rest.api.service.UserService;
import com.rest.api.utils.request.UserDTO;
import com.rest.api.utils.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public BaseResponse saveUser(UserDTO userDTO) {
        BaseResponse baseResponse = new BaseResponse();

        BaseResponse validateResult = validateUser(userDTO);
        if(validateResult.isSuccess()){
            User user = toEntity(userDTO);
            User savedUser = userRepository.save(user);
            baseResponse.setData(savedUser);
            baseResponse.setSuccess(true);
            baseResponse.setCode(String.valueOf(HttpStatus.OK));
        }else{
            baseResponse.setCode(validateResult.getCode());
            baseResponse.setSuccess(validateResult.isSuccess());
        }
        return baseResponse;
    }

    private BaseResponse validateUser(UserDTO userDTO){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSuccess(true);
        List<User> user = userRepository.findByEmail(userDTO.getEmail());
        if(!ObjectUtils.isEmpty(user)){
            baseResponse.setSuccess(false);
            baseResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
            return baseResponse;
        }
        return baseResponse;
    }

    private User toEntity(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEndTime(userDTO.getEndTime());
        user.setStartTime(userDTO.getStartTime());
        user.setCreatedDate(new Date());
        user.setLastModifiedDate(new Date());
        return user;
    }
}
