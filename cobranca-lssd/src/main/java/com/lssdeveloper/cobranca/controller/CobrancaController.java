package com.lssdeveloper.cobranca.controller;

import java.util.Arrays;
import java.util.Calendar;
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
import com.lssdeveloper.cobranca.model.StatusCobranca;
import com.lssdeveloper.cobranca.repository.Cobrancas;

@Controller
@RequestMapping("/cobrancas")
public class CobrancaController {
	
	private static final String CADASTRO_VIEW = "CadastroCobranca";
	private static final String PESQUISA_VIEW = "PesquisaCobrancas";
	
	@Autowired
	private Cobrancas cobrancas;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Cobranca());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Cobranca> todasCobrancas = cobrancas.findAll(); 
		ModelAndView mv = new ModelAndView(PESQUISA_VIEW);
		mv.addObject("cobrancas", todasCobrancas);
		return mv;
	}
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Cobranca cobranca) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(cobranca);
		return mv;
	}
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cobrancas.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Cobrança excluída com sucesso!");
		return "redirect:/cobrancas";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cobranca cobranca, Errors errors, RedirectAttributes attributes) {
			
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		cobrancas.save(cobranca);
		attributes.addFlashAttribute("mensagem", "Cobrança salva com sucesso!");
		return "redirect:/cobrancas/novo";
	}
	
	@ModelAttribute("todosStatusCobranca")
	public List<StatusCobranca> todosStatusCobranca(){
		
		return Arrays.asList(StatusCobranca.values());	
	}

	
}
