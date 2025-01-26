package bg.softuni.pathfinder.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentApiDTO {
    private Long id;
    private String content;
    private String authorName;
}
