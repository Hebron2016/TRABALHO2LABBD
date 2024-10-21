package seg.crud.Auditoria;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import seg.crud.Auditoria.repository.ILinkRepository;
import seg.crud.Auditoria.repository.ILogRepository;
import seg.crud.Auditoria.repository.IPaginaLinkRepository;
import seg.crud.Auditoria.repository.IRequisicaoRepository;
import seg.crud.Auditoria.repository.ISessaoRepository;
import seg.crud.Auditoria.model.*;
@SpringBootTest
class AuditoriaApplicationTests {
	@Autowired
	private ILinkRepository lRep;
	@Autowired
	private ISessaoRepository sRep;
	@Autowired
	private ILogRepository loRep;
	@Autowired
	private IRequisicaoRepository rRep;
	@Autowired
	private IPaginaLinkRepository plRep;
	
	@Test
	void selectPL() {
		int num = plRep.findCountQtdLink("A");
		System.out.println(num);
	}
	@Test
	void selectsRequisicao() {
		List<Requisicao> requisicoes = rRep.findSessaoUserRequisicao("a");
		for (Requisicao r: requisicoes) {
			System.out.println(r.getCodigoRequisicao());
		}
	/*	List<Requisicao> requisicoes = rRep.findTempoReq(2);
		for (Requisicao r: requisicoes) {
			System.out.println(r.getCodigoRequisicao());
		}*/
	}
	@Test
	void selectsLink() {
		List<Link> links = lRep.findAll();
		for (Link l: links) {
			System.out.println(l.toString());
		}
	}
	@Test
	void selectLog() {
		Sessao sessao = sRep.findBySessaoIp("1");
		System.out.println(sessao.toString());

	}
	@Test
	void selectLogIp() {
		List<Log> logas = loRep.findSessaoIp("1");
		System.out.println("Roda está merda");
		if(logas.isEmpty()) {
			System.out.println("Num tem nada");
		}
		for (Log l: logas) {
			System.out.println(l.getCodigoLog());
		}
	}
}


