package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.ExRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExRateRepository extends JpaRepository<ExRate, Long> {

    Optional<ExRate> findByCurrency(String currency);
}
