package com.rest.api.utils.request;

import lombok.Data;

@Data
public class CommentDTO {

    private String name;

    private String email;

    private String body;

    private Long postId;
}
