package com.PKHS.airbnb.repository;

import com.PKHS.airbnb.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
