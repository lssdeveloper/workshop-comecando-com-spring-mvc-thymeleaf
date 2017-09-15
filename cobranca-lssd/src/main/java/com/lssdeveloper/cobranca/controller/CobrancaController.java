package com.lssdeveloper.cobranca.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lssdeveloper.cobranca.model.Cobranca;
import com.lssdeveloper.cobranca.model.StatusCobranca;
import com.lssdeveloper.cobranca.repository.Cobrancas;

@Controller
@RequestMapping("/cobrancas")
public class CobrancaController {
	
	@Autowired
	private Cobrancas cobrancas;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroCobranca");
		mv.addObject(new Cobranca());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Cobranca> todasCobrancas = cobrancas.findAll(); 
		ModelAndView mv = new ModelAndView("PesquisaCobrancas");
		mv.addObject("cobrancas", todasCobrancas);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Cobranca cobranca, Errors errors) {
			
		ModelAndView mv = new ModelAndView("CadastroCobranca");
		if (errors.hasErrors()) {
			return mv;
		}
		cobrancas.save(cobranca);
		mv.addObject("mensagem", "Cobrança salva com sucesso!");
		return mv;
	}
	
	@ModelAttribute("todosStatusCobranca")
	public List<StatusCobranca> todosStatusCobranca(){
		
		return Arrays.asList(StatusCobranca.values());	
	}

	
}
