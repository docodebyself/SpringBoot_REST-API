package com.rest.api.utils;

import com.rest.api.utils.request.PostDTO;

import java.util.HashMap;
import java.util.Map;

public class ValidateObject {

    public static Map<String, String> validatePostDTO(PostDTO dto){
        Map<String, String> errors = new HashMap<>();

        errors.putAll(ValidateUtils.builder()
                .fieldName("title")
                .value(dto.getTitle())
                .required(true)
                .maxLength(20)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("description")
                .value(dto.getDescription())
                .required(false)
                .maxLength(50)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("maximumOfComments")
                .value(dto.getMaximumOfComments())
                .required(true)
                .isInteger(true)
                .min(Long.valueOf(1))
                .max(Long.valueOf(10))
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("content")
                .value(dto.getContent())
                .required(true)
                .maxLength(30)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("phoneNumber")
                .value(dto.getPhoneNumber())
                .required(true)
                .maxLength(10)
                .onlyNumber(true)
                .build().validate());

        return errors;

    }
}
