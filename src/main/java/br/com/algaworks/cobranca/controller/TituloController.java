package br.com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.algaworks.cobranca.model.StatusTitulo;
import br.com.algaworks.cobranca.model.Titulo;
import br.com.algaworks.cobranca.repository.Titulos;

@Controller
@RequestMapping(value = "/titulos")
public class TituloController {
	private static final String EDITADO_COM_SUCESSO = "Titulo editado com sucesso !";

	private static final String CADASTRO_COM_SUCESSO = "Titulo Cadastrado com sucesso !";

	private static final String MENSAGEM = "mensagem";

	private static final String TODOS_OS_TITULOS = "todosOsTitulos";

	private static final String REDIRECT_TITULOS = "redirect:/titulos/";

	private static final String EDICAO_TITULO = "EdicaoTitulo";

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
		attributes.addFlashAttribute(MENSAGEM,CADASTRO_COM_SUCESSO );
		return REDIRECT_TITULOS;
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
		ModelAndView mv = new ModelAndView(EDICAO_TITULO);
		mv.addObject(titulo);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.PUT,path = "/{codigo}")
	public String editar (@ModelAttribute("codigo")  Titulo titulo,Errors erros,RedirectAttributes attributes ) {
		titulos.saveAndFlush(titulo);
		attributes.addFlashAttribute(MENSAGEM,EDITADO_COM_SUCESSO );
		return REDIRECT_TITULOS;
	}
	@ModelAttribute(TODOS_OS_TITULOS)
	public List<StatusTitulo> todosStatusList(){
		return Arrays.asList(StatusTitulo.values());
		
	}
}
