package seg.crud.Auditoria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import seg.crud.Auditoria.model.Requisicao;

public interface IRequisicaoRepository  extends JpaRepository<Requisicao, Integer> {

	@Query(value = "SELECT r FROM Requisicao r WHERE r.tempo < ?1" )
	public List<Requisicao> findTempoReq(int tempo);
	
	@Query(value = "SELECT*FROM requisicao r, sessao s WHERE r.sessaoIp = s.ip AND s.usuario = ?1", nativeQuery = true)
	public List<Requisicao> findSessaoUserRequisicao(String usuario);
}
