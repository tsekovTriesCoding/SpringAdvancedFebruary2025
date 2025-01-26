package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.Comment;
import bg.softuni.pathfinder.model.dto.CreateCommentApiDTO;
import bg.softuni.pathfinder.model.dto.CreateCommentDTO;
import bg.softuni.pathfinder.repository.CommentRepository;
import bg.softuni.pathfinder.service.helper.RouteHelperService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final RouteHelperService routeHelperService;
    private final UserHelperService userHelperService;

    public void create(CreateCommentDTO createCommentDTO) {
        createInternal(createCommentDTO);
    }

    public CreateCommentApiDTO createApi(CreateCommentDTO createCommentDTO) {
        return modelMapper.map(createInternal(createCommentDTO), CreateCommentApiDTO.class);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    private Comment createInternal(CreateCommentDTO createCommentDTO) {
        Comment comment = new Comment();

        comment.setContent(createCommentDTO.getMessage());
        comment.setRoute(routeHelperService.getByIdOrThrow(createCommentDTO.getRouteId()));
        comment.setAuthor(userHelperService.getUser());
        comment.setCreated(Instant.now());

        return commentRepository.save(comment);
    }
}
