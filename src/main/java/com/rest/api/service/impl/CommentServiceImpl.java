package com.rest.api.service.impl;

import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;
import com.rest.api.errors.ResourceNotFoundException;
import com.rest.api.repository.PostRepository;
import com.rest.api.utils.request.CommentDTO;
import com.rest.api.repository.CommentRepository;
import com.rest.api.service.CommentService;
import com.rest.api.utils.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @Override
    public List<CommentResponseDTO> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {
        commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        Comment comment = commentRepository.findById(id).get();
        return Optional.of(mapperToCommentDTO(comment));
    }

    @Override
    public CommentResponseDTO save(CommentDTO dto) {
        Post p = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id : " + dto.getPostId()));

        Comment cmt = new Comment();
        cmt.setName(dto.getName());
        cmt.setEmail(dto.getEmail());
        cmt.setBody(dto.getBody());
        cmt.setPost(p);
        Comment savedComment = commentRepository.save(cmt);
        return mapperToCommentDTO(savedComment);
    }

    @Override
    public CommentResponseDTO update(CommentDTO dto, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found to update"));

        Post p = postRepository.findById(dto.getPostId())
                        .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        comment.setName(dto.getName());
        comment.setPost(p);
        return mapperToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public String delete(Long id) {
        commentRepository.deleteById(id);
        return "Success";
    }

    public static CommentResponseDTO mapperToCommentDTO(Comment comment){
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setEmail(comment.getEmail());
        dto.setName(comment.getName());
        dto.setPost(comment.getPost());
        return dto;
    }
}
