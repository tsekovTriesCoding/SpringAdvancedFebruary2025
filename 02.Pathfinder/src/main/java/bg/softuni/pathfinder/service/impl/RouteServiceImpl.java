package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.dto.AddRouteDTO;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.CurrentUser;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RouteServiceImpl implements RouteService {
    private final UserService userService;
    private final CurrentUser currentUser;
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final Random random = new Random();

    public RouteServiceImpl(UserService userService,
                            CurrentUser currentUser,
                            RouteRepository routeRepository) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.routeRepository = routeRepository;
        this.modelMapper = new ModelMapper();
    }

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

        try(InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }
//originalFileName,fileLocation -> /uploads/{userId}{fileId}.gpx - this is example for unique file names
//        may use imgur api to upload img to their site,not local.... but local is OK for now
    }
}