package login.register.society.repository;

import login.register.society.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
}
