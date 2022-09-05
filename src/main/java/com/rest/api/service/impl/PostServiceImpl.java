package com.rest.api.service.impl;

import com.rest.api.entity.Post;
import com.rest.api.errors.ResourceNotFoundException;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.PostService;
import com.rest.api.utils.request.PostDTO;
import com.rest.api.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rest.api.service.impl.CommentServiceImpl.mapperToCommentDTO;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;


    @Override
    public List<PostResponseDTO> getAll() {
        return postRepository.findAll().stream().map(p -> mapperToPostDTO(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<PostResponseDTO> findById(Long id) {
        postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post not found with id :" + id));

        return Optional.of(mapperToPostDTO(postRepository.findById(id).get()));
    }

    @Override
    public PostResponseDTO save(PostDTO dto) {
        Post p = new Post();
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setMaximumOfComments(dto.getMaximumOfComments());
        p.setContent(dto.getContent());
        Post saved = postRepository.save(p);
        return mapperToPostDTO(saved);
    }

    @Override
    public PostResponseDTO update(PostDTO dto, Long id) {
        Post p = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id : " + id));
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setContent(dto.getContent());
        return mapperToPostDTO(p);
    }

    @Override
    public String delete(Long id) {
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found to delete"));
        postRepository.deleteById(id);
        return "Delete successfully";
    }

    private PostResponseDTO mapperToPostDTO(Post entity){
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMaximumOfComments(entity.getMaximumOfComments());
        dto.setContent(entity.getContent());

        if(entity.getComments() != null && entity.getComments().size() > 0){
            dto.setComments(entity.getComments().stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toSet()));
        }
        return dto;
    }


}
