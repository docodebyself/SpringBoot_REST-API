package com.rest.api.utils.response;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

@Data
public class PostResponseDTO {

    private Long id;

    private String title;

    private String description;

    private String content;

    private Integer maximumOfComments;

    private Set<CommentResponseDTO> comments;
}
