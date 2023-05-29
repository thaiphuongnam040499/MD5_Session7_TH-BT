package rikkei.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rikkei.academy.model.Comment;

import java.util.List;
@Repository
public interface ICommentRepository extends PagingAndSortingRepository<Comment,Long> {
    Page<Comment> findAll(Pageable pageable);
}
