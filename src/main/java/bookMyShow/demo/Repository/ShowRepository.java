package bookMyShow.demo.Repository;

import bookMyShow.demo.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Integer> {
}
