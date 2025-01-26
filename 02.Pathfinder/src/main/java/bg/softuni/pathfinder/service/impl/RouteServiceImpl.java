package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.dto.*;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final Random random = new Random();

    @Transactional
    public List<RouteShortInfoDTO> getAll() {
        return this.routeRepository.findAll().stream().map(this::mapToShortInfo).toList();
    }

    @Transactional
    public RouteShortInfoDTO getRandomRoute() {
        long routeCount = this.routeRepository.count();
        long randomId = this.random.nextLong(routeCount) + 1;
        Optional<Route> route = this.routeRepository.findById(randomId);
        if (route.isEmpty()) {
// throw exception; return empty
        }

        return this.mapToShortInfo(route.get());
    }

    private RouteShortInfoDTO mapToShortInfo(Route route) {
        RouteShortInfoDTO dto = this.modelMapper.map(route, RouteShortInfoDTO.class);
        Optional<Picture> first = route.getPictures().stream().findFirst();
        first.ifPresent((picture) -> {
            dto.setImageUrl(picture.getUrl());
        });
        return dto;
    }

    @Override
    public void add(AddRouteDTO addRouteDTO, MultipartFile file) throws IOException {
        Route toInsert = this.modelMapper.map(addRouteDTO, Route.class);

        Path destinationFile = Paths
                .get("src", "main", "resources", "uploads", "file.gpx")
                .normalize()
                .toAbsolutePath();

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }
//originalFileName,fileLocation -> /uploads/{userId}{fileId}.gpx - this is example for unique file names
//        may use imgur api to upload img to their site,not local.... but local is OK for now
    }

    @Transactional(readOnly = true)
    @Override
    public List<RouteCategoryDTO> getRouteByCategory(CategoryType category) {
        List<Route> allByCategoryName = routeRepository.findAllByCategories_Name(category);

        return allByCategoryName.stream()
                .map(route -> modelMapper.map(route, RouteCategoryDTO.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RouteCategoryDTO getMostCommentedRoute() {
        Route mostCommented = this.routeRepository.findAll()
                .stream()
                .max(Comparator.comparingInt(route -> route.getComments().size()))
                .orElse(null);

        RouteCategoryDTO routeCategoryDTO = this.modelMapper.map(mostCommented, RouteCategoryDTO.class);
        routeCategoryDTO.setImageUrl("http://res.cloudinary.com/ch-cloud/image/upload/v1630581072/d47iy8kxv6qni8euhojk.jpg");
        return routeCategoryDTO;
    }

    @Transactional
    public RouteDetailsDTO getDetails(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Route with id: " + id + " was not found"));

        RouteDetailsDTO dto = modelMapper.map(route, RouteDetailsDTO.class);
        dto.setVideoUrl("https://www.youtube.com/embed/" + dto.getVideoUrl());
        dto.setImageUrls(List.of("/images/pic4.jpg", "/images/pic1.jpg"));
        dto.setComments(route.getComments().stream()
                .map(comment -> modelMapper.map(comment, RouteDetailsCommentDTO.class))
                .toList());

        return dto;
    }
}