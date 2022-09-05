package com.rest.api.utils.request;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class PostDTO {

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Field is not empty")
    @Length(min = 1, max = 30, message = "Invalid length field title")
    private String title;


    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is not empty")
    @Length(min = 1, max = 30, message = "Invalid length field description")
    private String description;

    @Min(1)
    @Max(6)
    @NotNull(message = "Maximum of comments is required")
    private Integer maximumOfComments;


    @NotNull(message = "Field content is required")
    @NotEmpty(message = "Field content is not empty")
    @Length(min = 1, max = 30, message = "Invalid length field content")
    private String content;

}
