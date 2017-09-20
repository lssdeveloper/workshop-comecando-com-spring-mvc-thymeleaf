package com.lssdeveloper.cobranca.controller;

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

import com.lssdeveloper.cobranca.model.Cobranca;
import com.lssdeveloper.cobranca.model.Estado;
import com.lssdeveloper.cobranca.model.Regiao;
import com.lssdeveloper.cobranca.repository.Estados;


@Controller
@RequestMapping("/estados")
public class EstadoController {
	
	private static final String CADASTRO_VIEW = "CadastroEstado";
	private static final String PESQUISA_VIEW = "PesquisaEstados";
	
	@Autowired
	private Estados estados;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Estado());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Estado> todosEstados = estados.findAll();
		ModelAndView mv = new ModelAndView(PESQUISA_VIEW);
		mv.addObject("estados", todosEstados);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Estado estado) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(estado);
		return mv;
	}
	
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		
		estados.delete(codigo);	
		attributes.addFlashAttribute("mensagem", "Estado excluído com sucesso!");
		return "redirect:/estados";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Estado estado, Errors errors, RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		estados.save(estado);	
		attributes.addFlashAttribute("mensagem", "Estado salvo com sucesso!");
		return "redirect:/estados/novo";
	}
	
	@ModelAttribute("todasRegioes")
	public List<Regiao> todasRegioes(){
		return Arrays.asList(Regiao.values());
	}	
	

}
