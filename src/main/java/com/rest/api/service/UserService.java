package com.rest.api.service;

import com.rest.api.utils.request.UserDTO;
import com.rest.api.utils.response.BaseResponse;

public interface UserService {

    BaseResponse saveUser(UserDTO userDTO);


}
