package bg.softuni.pathfinder.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RouteCategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
}