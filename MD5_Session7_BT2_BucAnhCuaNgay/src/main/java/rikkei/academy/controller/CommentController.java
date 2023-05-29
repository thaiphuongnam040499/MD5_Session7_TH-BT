package rikkei.academy.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Comment;
import rikkei.academy.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @GetMapping("/")
    public String show(Model model, Pageable pageable){
        Page<Comment> comments = commentService.findAll(pageable);
        model.addAttribute("comments", comments);
        model.addAttribute("comment",new Comment());
        return "/index";
    }
    @GetMapping("/like/{id}")
    public String like(@PathVariable("id") Long id){
        Optional<Comment> comment = commentService.findById(id);
        Comment comment1 = comment.get();
        comment1.setAmountLike(comment1.getAmountLike()+1);
        commentService.save(comment1);
        return "redirect:/";
    }
    @PostMapping("/comment")
    public String comment(@ModelAttribute("comment") Comment comment) {
        commentService.save(comment);
        return "redirect:/";
    }
}
