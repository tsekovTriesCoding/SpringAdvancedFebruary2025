package bg.softuni.pathfinder.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDTO {
    private Long routeId;
    private String message;
}
