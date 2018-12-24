package com.PKHS.airbnb.service.impl;

import com.PKHS.airbnb.model.Comment;
import com.PKHS.airbnb.repository.CommentRepository;
import com.PKHS.airbnb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Iterable<Comment> findAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Comment findById(Integer id) {
        return this.commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
