package com.PKHS.airbnb.service;

import com.PKHS.airbnb.model.Comment;

public interface CommentService {

    Iterable<Comment> findAll();

    Comment findById(Integer id);

    Comment save(Comment comment);
}
