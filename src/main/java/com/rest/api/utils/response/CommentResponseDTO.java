package com.rest.api.utils.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.api.entity.Post;
import lombok.Data;

@Data
public class CommentResponseDTO {

    private Long id;

    private String name;

    private String email;

    private String body;

    @JsonIgnore
    private Post post;
}
