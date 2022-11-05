package com.rest.api.repository.impl;

import com.rest.api.entity.Post;
import com.rest.api.utils.PageDTO;
import com.rest.api.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom{

    private final EntityManager entityManager;

    @Override
    public PageDTO findAllWithCustomPage(int size, int page, String direction, String properties, String content, String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        //write query to get data
        CriteriaQuery<Post> getPostQuery = cb.createQuery(Post.class);

        Root<Post> from = getPostQuery.from(Post.class);

        CriteriaQuery<Post> select = getPostQuery.select(from);

        List<Predicate> predicates = new ArrayList<>();

        //solve searching like operator
        if(!ObjectUtils.isEmpty(content)){
            predicates.add(cb.like(from.get("content"), "%" + content + "%"));
        }

        if(!ObjectUtils.isEmpty(title)){
            predicates.add(cb.like(from.get("title"), "%" + title + "%"));
        }

        select.select(from).where(predicates.toArray(new Predicate[]{}));

        //solve direction and sort
        if(direction.equalsIgnoreCase("desc") && !ObjectUtils.isEmpty(properties)){
            getPostQuery.orderBy(cb.desc(from.get(properties)));
        }else if(direction.equalsIgnoreCase("asc") && !ObjectUtils.isEmpty(properties)){
            getPostQuery.orderBy(cb.asc(from.get(properties)));
        }

        //solve page and size
        TypedQuery<Post> typedQuery = entityManager.createQuery(select);

        //count total elements
        long totalCount = typedQuery.getResultList().size();

        int offSet = (page - 1) * size;
        typedQuery.setFirstResult(offSet);
        typedQuery.setMaxResults(size);

        //set data and return
        PageDTO<Post> pageDTO = PageUtils.calculatePage(size, page, totalCount);
        pageDTO.setData(typedQuery.getResultList());
        return pageDTO;
    }
}
