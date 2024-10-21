package seg.crud.Auditoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import seg.crud.Auditoria.model.Link;



public interface ILinkRepository extends JpaRepository<Link, Integer> {
	@Query(value = "SELECT l FROM Link l WHERE l.URLdestino = ?1")
	public Link findLinkUrl(String url);
}
