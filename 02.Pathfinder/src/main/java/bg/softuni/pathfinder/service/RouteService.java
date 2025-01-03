package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.AddRouteDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RouteService {
    void add(AddRouteDTO addRouteDTO, MultipartFile file) throws IOException;
}

