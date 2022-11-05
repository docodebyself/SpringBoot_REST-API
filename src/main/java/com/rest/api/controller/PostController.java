package com.rest.api.controller;

import com.rest.api.entity.Post;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.PostService;
import com.rest.api.utils.PageDTO;
import com.rest.api.utils.ValidateObject;
import com.rest.api.utils.request.PostDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final PostRepository postRepository;

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody PostDTO dto) {
        Map<String, String> errorValidator = ValidateObject.validatePostDTO(dto);
        if (!ObjectUtils.isEmpty(errorValidator)) {
            return new ResponseEntity<>(errorValidator, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postService.save(dto), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("idToUpdate") Long id, @RequestBody PostDTO dto) {
        return new ResponseEntity<>(postService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idToDelete}")
    public ResponseEntity<String> delete(@PathVariable("idToDelete") Long id) {
        return new ResponseEntity<>(postService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getAllWithPage")
    public ResponseEntity<Page<Post>> getAllWithPage(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(postRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllWithTitlePage")
    public ResponseEntity<Page<Post>> getAllWithTitlePage(@RequestParam(defaultValue = "", required = false) String title,
                                                          @PageableDefault Pageable pageable){
        return new ResponseEntity<>(postRepository.findByTitle(title, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllWithPageCustom")
    public ResponseEntity<PageDTO> getAllWithPageCustom(
                                    @RequestParam(defaultValue = "10", required = false) int size,
                                    @RequestParam(defaultValue = "1", required = false) int page,
                                    @RequestParam(defaultValue = "asc", required = false) String direction,
                                    @RequestParam(defaultValue =  "", required = false) String properties,
                                    @RequestParam(defaultValue = "", required = false) String content,
                                    @RequestParam(defaultValue = "", required = false) String title){
        return new ResponseEntity<>(
                postRepository.findAllWithCustomPage(size, page, direction, properties, content, title),
                HttpStatus.OK);
    }


}
