package com.rest.api.controller;

import com.rest.api.entity.Comment;
import com.rest.api.repository.CommentRepository;
import com.rest.api.utils.request.CommentDTO;
import com.rest.api.service.CommentService;
import com.rest.api.utils.response.CommentProjection;
import com.rest.api.utils.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    private final CommentRepository commentRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<CommentResponseDTO>> getAllComment(){
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentDTO dto){
        return new ResponseEntity<>(commentService.save(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CommentResponseDTO>> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<CommentResponseDTO> update(@RequestParam("id") Long id, @RequestBody CommentDTO dto){
        return new ResponseEntity<>(commentService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("idToDelete")Long id){
        return new ResponseEntity<>(commentService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Comment>> findAll(){
        return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findAllComments")
    public ResponseEntity<List<Comment>> findAllComments(){
        return new ResponseEntity<>(commentRepository.findAllComments(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findByCommentId")
    public ResponseEntity<Comment> findByCommentId(@RequestParam(required = false) Long id){
        return new ResponseEntity<>(commentRepository.findByCommentId(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findWithLike")
    public ResponseEntity<List<Comment>> findByCommentId(@RequestParam(required = false) String name){
        return new ResponseEntity<>(commentRepository.findWithLike(name), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findWithLikeJPA")
    public ResponseEntity<List<Comment>> findWithLikeJPA(@RequestParam(required = false) String name){
        return new ResponseEntity<>(commentRepository.findWithLikeJPA(name), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findAllWithNativeQuery")
    public ResponseEntity<List<Comment>> findAllWithNativeQuery(@RequestParam(required = false) String name){
        return new ResponseEntity<>(commentRepository.findAllWithNativeQuery(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findByIdWithNativeQuery")
    public ResponseEntity<Comment> findByIdWithNativeQuery(@RequestParam(required = false) Long id){
        return new ResponseEntity<>(commentRepository.findByIdWithNativeQuery(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findByIdWithJPA")
    public ResponseEntity<Comment> findByIdWithJPA(@RequestParam(required = false) Long id){
        return new ResponseEntity<>(commentRepository.findByIdWithJPA(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findAllWithProjection")
    public ResponseEntity<List<CommentProjection>> findAllWithProjection(@RequestParam(required = false) Long id){
        return new ResponseEntity<>(commentRepository.findAllWithProjection(), HttpStatus.ACCEPTED);
    }
}
