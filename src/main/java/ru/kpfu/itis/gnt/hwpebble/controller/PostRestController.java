package ru.kpfu.itis.gnt.hwpebble.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kpfu.itis.gnt.hwpebble.constants.ViewPaths;
import ru.kpfu.itis.gnt.hwpebble.exception.ResourceNotFoundException;
import ru.kpfu.itis.gnt.hwpebble.model.PostDto;
import ru.kpfu.itis.gnt.hwpebble.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Tags(value = {
        @Tag(name = "Посты")
})
public class PostRestController {
    private final PostService service;

    @Operation(summary = "Посты")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Получение статей с пагинацией",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostDto.class)
                            )
                    }
            )
    })
    @ResponseBody
    @GetMapping(path = "posts",params = { "page", "size" }, produces = "application/json")
    public List<PostDto> findPaginated(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "size", defaultValue = "5") int size, UriComponentsBuilder uriBuilder,
                                   HttpServletResponse response) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<PostDto> resultPage = service.findAll(pageable);
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException("Resource not found");
        }
        return resultPage.getContent();
    }

}
