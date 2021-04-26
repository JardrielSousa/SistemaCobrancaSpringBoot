package br.com.algaworks.cobranca.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.algaworks.cobranca.model.StatusTitulo;
import br.com.algaworks.cobranca.model.Titulo;
import br.com.algaworks.cobranca.repository.TitulosRepository;

@RunWith(EasyMockRunner.class)
public class TituloControllerTest {
	@Mock
	private TitulosRepository titulosRepository;
	
	@Mock
	private TituloController tituloController;
	
	private Titulo titulo;
	
	@Before
	public void setup() {
		titulo = new Titulo();
		titulosRepository = EasyMock.createMock(TitulosRepository.class);
	}
	
	@Test
	public void test01CadastroTitulo() {
	    titulo = setarTitulo();
		EasyMock.expect(titulosRepository.save(titulo)).andReturn(titulo);
	}

	@Test
	public void test02EditarTitulo() {
	    titulo = updateTitulo();
		EasyMock.expect(titulosRepository.saveAndFlush(titulo)).andReturn(titulo);
	}
	
	@Test
	public void test03BuscarTitulo() {
        List<Titulo> listTitulo = Arrays.asList(setarTitulo());
		EasyMock.expect(titulosRepository.findAll()).andReturn(listTitulo);
	}
	

	private Titulo setarTitulo() {
		return new Titulo(1L,"prod",new Date(),new BigDecimal(10),StatusTitulo.PENDENTE);
	}

	private Titulo updateTitulo() {
	    titulo.setDescricao("prod 2");
	    titulo.setStatus(StatusTitulo.RECEBIDO);
	   return titulo;
	}
	
}
