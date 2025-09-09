package bookMyShow.demo.Repository;

import bookMyShow.demo.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Integer> {
    Optional<ShowSeatType> findByShowAndSeatType(int showId, String seatTypeName);
}
