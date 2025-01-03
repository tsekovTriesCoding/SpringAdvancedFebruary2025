package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}