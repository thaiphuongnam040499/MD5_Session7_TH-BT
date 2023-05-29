package rikkei.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rikkei.academy.model.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService extends IGenericService<Comment,Long> {
    void save(Comment comment);
    Optional<Comment> findById(Long id);
    Page<Comment> findAll(Pageable pageable);
}
