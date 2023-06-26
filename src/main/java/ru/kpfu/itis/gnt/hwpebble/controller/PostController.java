package ru.kpfu.itis.gnt.hwpebble.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.gnt.hwpebble.constants.ViewPaths;
import ru.kpfu.itis.gnt.hwpebble.model.PostDto;
import ru.kpfu.itis.gnt.hwpebble.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService service;
    private final String CURRENT_PAGE_KEY = "currentPage";
    private final String TOTAL_PAGES_KEY = "totalPages";
    private final String TOTAL_ITEMS_KEY = "totalItems";
    private final String LIST_POSTS_KEY = "posts";
    private final String POST_ITEM_KEY = "post";


    @GetMapping
    public String getAllPosts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model
    ) {
        Page<PostDto> paginated = findPaginated(page, size);
        return addPaginationModel(page, paginated, model);
    }
    @GetMapping("/new")
    public String newPost(ModelMap map) {
        map.put(POST_ITEM_KEY, new PostDto());
        return ViewPaths.POST_NEW;
    }

    @PostMapping("/new")
    public String newPostCreate(
            RedirectAttributes redirectAttributes,
            @ModelAttribute(POST_ITEM_KEY)  PostDto post
    ) {
        service.createPost(post);
        redirectAttributes.addFlashAttribute("message", "Post \"" + post.getTitle() + "\" has been saved successfully");
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#newPost").build();
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        return service.createPost(postDto);
    }

    private String addPaginationModel(int page, Page<PostDto> paginated, Model model) {
        List<PostDto> posts = paginated.getContent();
        model.addAttribute(CURRENT_PAGE_KEY, page);
        model.addAttribute(TOTAL_PAGES_KEY, paginated.getTotalPages());
        model.addAttribute(TOTAL_ITEMS_KEY, paginated.getTotalElements());
        model.addAttribute(LIST_POSTS_KEY, posts);
        return ViewPaths.POSTS_LIST;
    }


    private Page<PostDto> findPaginated(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return service.findAll(pageable);
    }

}
