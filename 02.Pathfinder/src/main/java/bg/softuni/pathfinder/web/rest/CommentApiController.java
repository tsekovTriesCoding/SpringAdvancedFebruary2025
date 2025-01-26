package bg.softuni.pathfinder.web.rest;

import bg.softuni.pathfinder.model.dto.CreateCommentApiDTO;
import bg.softuni.pathfinder.model.dto.CreateCommentDTO;
import bg.softuni.pathfinder.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comments")
    public CreateCommentApiDTO create(@RequestBody CreateCommentDTO createCommentApiDTO){
        return commentService.createApi(createCommentApiDTO);
    }
}
