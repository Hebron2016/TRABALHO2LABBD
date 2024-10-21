package seg.crud.Auditoria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import seg.crud.Auditoria.model.Log;

public interface ILogRepository  extends JpaRepository<Log, Integer>{

	@Query(value = "SELECT * FROM log lo WHERE lo.sessaoIp = ?1", nativeQuery = true)
	public List<Log> findSessaoIp(String ip);

}
