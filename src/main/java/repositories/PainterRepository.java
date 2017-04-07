package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;
import domain.Painter;

@Repository
public interface PainterRepository extends JpaRepository<Painter, Integer> {

	@Query("select p from Painter p where p.userAccount.username=?1")
	Painter getPainterByUsername(String username);

}
