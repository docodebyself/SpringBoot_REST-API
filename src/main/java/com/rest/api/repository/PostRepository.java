package com.rest.api.repository;

import com.rest.api.entity.Post;
import com.rest.api.repository.impl.PostRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    Page<Post> findAll(Pageable pageable);

    @Query("select p from Post p where p.title like %?1%")
    Page<Post> findByTitle(String title, Pageable pageable);

}
