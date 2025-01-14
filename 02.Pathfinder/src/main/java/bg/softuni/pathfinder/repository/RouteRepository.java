package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByCategories_Name(CategoryType category);
}