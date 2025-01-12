package bg.softuni.mobileleleoffers.repository;

import bg.softuni.mobileleleoffers.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
