package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.dto.AddRouteDTO;
import bg.softuni.pathfinder.model.dto.RouteCategoryDTO;
import bg.softuni.pathfinder.model.enums.CategoryType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface RouteService {
    void add(AddRouteDTO addRouteDTO, MultipartFile file) throws IOException;


    List<RouteCategoryDTO> getRouteByCategory(CategoryType category);

    RouteCategoryDTO getMostCommentedRoute();
}

