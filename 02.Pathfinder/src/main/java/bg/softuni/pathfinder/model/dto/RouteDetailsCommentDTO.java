package bg.softuni.pathfinder.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDetailsCommentDTO {
    private Long id;
    private String content;
    private String authorName;
}
