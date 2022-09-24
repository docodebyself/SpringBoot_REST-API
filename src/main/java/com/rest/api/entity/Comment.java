package com.rest.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Comment.findAllComments", query = "select c from Comment c "),
        @NamedQuery(name = "Comment.findByCommentId", query = "select c from Comment c where c.id = ?1"),
        @NamedQuery(name = "Comment.findWithLike", query = "select c from Comment c where c.name like ?1")
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "findAllWithNativeQuery",
                entities = {
                        @EntityResult(entityClass =  Comment.class)
                }
        ),
        @SqlResultSetMapping(
                name = "findByIdWithNativeQuery",
                entities = {
                        @EntityResult(entityClass = Comment.class)
                }
        )
})


@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Comment.findAllWithNativeQuery",
                query = "select c.* from comment c ",
                resultSetMapping = "findAllWithNativeQuery"
        ),
        @NamedNativeQuery(
                name = "Comment.findByIdWithNativeQuery",
                query = "select c.* from comment c where c.comment_id = ?1",
                resultSetMapping = "findByIdWithNativeQuery"
        )
})

@Setter
@Getter
@Entity
@Table(name = "comment", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "body")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "post_id")
    private Post post;
}
