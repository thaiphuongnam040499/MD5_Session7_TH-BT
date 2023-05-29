package rikkei.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import rikkei.academy.model.Comment;
import rikkei.academy.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CommentService implements ICommentService{
    @Autowired
    private ICommentRepository commentRepository;
    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void remove(Long aLong) {
        commentRepository.deleteById(aLong);
    }

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
       return commentRepository.findById(id);
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber(), 3);
        return commentRepository.findAll(pageable);
    }
}
