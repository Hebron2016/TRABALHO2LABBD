package seg.crud.Auditoria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import seg.crud.Auditoria.model.Link;
import seg.crud.Auditoria.model.Pagina;
import seg.crud.Auditoria.model.PaginaLink;
import seg.crud.Auditoria.model.Sessao;
import seg.crud.Auditoria.repository.ILinkRepository;
import seg.crud.Auditoria.repository.IPaginaLinkRepository;
import seg.crud.Auditoria.repository.IPaginaRepository;

@Controller
public class PaginaLinkController {

	@Autowired
	private IPaginaLinkRepository plRep;
	@Autowired
	private ILinkRepository lRep;
	@Autowired
	private IPaginaRepository pRep;
	
	@RequestMapping(name="paginaLink", value = "/paginaLink", method = RequestMethod.GET)
	public ModelAndView paginaLinkGet(ModelMap model) {
		return new ModelAndView("paginaLink");
	}
	
	@RequestMapping(name="paginaLink", value = "/paginaLink", method = RequestMethod.POST)
	public ModelAndView paginaLinkPost(
			@RequestParam Map<String, String> params,
			ModelMap model) {
		String link = params.get("link");
		String pagina = params.get("pagina");
		String botao = params.get("botao");


		int num=0;
		PaginaLink paginaLink = new PaginaLink();
		Link linkObj = new Link();
		Pagina paginaObj = new Pagina();
		if (!botao.equals("Listar")) {
			linkObj = lRep.findLinkUrl(link);
			paginaLink.setLink(linkObj);
		}
		if (botao.equals("Inserir")){
			paginaObj = pRep.findPaginaUrl(pagina);
			paginaLink.setPagina(paginaObj);
		}
		
		String saida = "";
		String erro = "";
		List<PaginaLink> paginaLinks = new ArrayList<>();
		
		try {
			if (botao.equals("Inserir")) {
				plRep.save(paginaLink);
				saida = "PaginaLink inserido com sucesso";
			}
			if (botao.equals("Excluir")) {
				plRep.delete(paginaLink);
				saida = "PaginaLink excluido com sucesso";
			}
			if (botao.equals("Listar")) {
				paginaLinks = plRep.findpl();
			}
			if (botao.equals("QtdLink")) {
				 num = plRep.findCountQtdLink(link);
			}
		}catch(Exception e) {
			erro = e.getMessage();
		} finally{
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("paginaLink", paginaLink);
			model.addAttribute("paginaLinks", paginaLinks);
			model.addAttribute("num", num);
		}
		return new ModelAndView("paginaLink");
	}
}
