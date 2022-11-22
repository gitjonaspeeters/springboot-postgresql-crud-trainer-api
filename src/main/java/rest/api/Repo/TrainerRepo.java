package rest.api.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.api.Model.Trainer;

public interface TrainerRepo extends JpaRepository<Trainer, Long> {
}
