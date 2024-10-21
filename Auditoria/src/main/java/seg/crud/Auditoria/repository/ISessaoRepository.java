package seg.crud.Auditoria.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import seg.crud.Auditoria.model.Sessao;

public interface ISessaoRepository  extends JpaRepository<Sessao, String> {
	
	@Query(value = "SELECT s FROM Sessao s WHERE s.ip = ?1 ")
	public Sessao findBySessaoIp(String ip);
	
}
