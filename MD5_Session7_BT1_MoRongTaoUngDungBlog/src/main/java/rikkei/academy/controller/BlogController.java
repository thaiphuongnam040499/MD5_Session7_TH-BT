package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Blog;
import rikkei.academy.model.Catalog;
import rikkei.academy.service.blog.IBlogService;
import rikkei.academy.service.catalog.ICatalogService;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    ICatalogService catalogService;

    @GetMapping("/create-blog")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("catalogs",catalogService.findAll());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public String saveBlog(@ModelAttribute("blog") Blog blog,@ModelAttribute("catalogs") Catalog catalogs) {
        blogService.save(blog);
        return "redirect:/create-blog";
    }

    @GetMapping(value = {"/", "/blog"})
    public ModelAndView listCustomers(Pageable pageable, @RequestParam("sort") Optional<String> sort) {
        Sort sort1 = null;
        if (sort.isPresent()){
            switch (sort.get()){
                case "Title-ASC":
                    sort1 = Sort.by("title").ascending();
                    break;
                case "Title-DESC":
                    sort1 = Sort.by("title").descending();
                    break;
                default:
                    break;
            }
        }else {
            sort1 = Sort.by("title").ascending();
        }
        Page<Blog> blogs = blogService.findAll(pageable,sort1);
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog != null) {
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        }else {
            return new ModelAndView("/blog/list");
        }
    }

    @PostMapping("/edit-blog")
    public String updateBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }
    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") Long id){
        blogService.remove(id);
        return "redirect:/blog";
    }
}
