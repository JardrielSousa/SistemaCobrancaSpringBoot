package br.com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo,Errors erros,RedirectAttributes attributes ) {
		if(erros.hasErrors()) {
			return CADASTRO_TITULO;
		}
		titulos.save(titulo);
		attributes.addFlashAttribute("mensagem","Titulo Cadastrado com sucesso !" );
		return "redirect:/titulos/novo";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView(PESQUISA_TITULO);
		mv.addObject("titulo", todosTitulos);
		return mv;
	}
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		ModelAndView mv = new ModelAndView(CADASTRO_TITULO);
		mv.addObject(titulo);
		return mv;
	}
	@ModelAttribute("todosOsTitulos")
	public List<StatusTitulo> todosStatusList(){
		return Arrays.asList(StatusTitulo.values());
		
	}
}
