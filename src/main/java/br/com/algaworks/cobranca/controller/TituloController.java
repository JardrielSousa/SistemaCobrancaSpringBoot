package br.com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import br.com.algaworks.cobranca.model.StatusTitulo;
import br.com.algaworks.cobranca.model.Titulo;
import br.com.algaworks.cobranca.repository.Titulos;

@Controller
@RequestMapping(value = "/titulos")
public class TituloController {
	@Autowired
	private Titulos titulos;
	
	public static String CADASTRO_TITULO = "CadastroTitulo";
	public static String PESQUISA_TITULO = "home";
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_TITULO);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulos.save(titulo);
		ModelAndView mv = new ModelAndView(CADASTRO_TITULO);
		mv.addObject("mensagem","Titulo Cadastrado com sucesso !");
		return mv;
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		return new ModelAndView(PESQUISA_TITULO);
	}
	@ModelAttribute("todosOsTitulos")
	public List<StatusTitulo> todosStatusList(){
		return Arrays.asList(StatusTitulo.values());
		
	}
}
