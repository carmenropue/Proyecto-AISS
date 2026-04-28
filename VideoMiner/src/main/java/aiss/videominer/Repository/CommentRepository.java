package aiss.videominer.Repository;

import aiss.videominer.model.Caption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Caption, Long> {
}
