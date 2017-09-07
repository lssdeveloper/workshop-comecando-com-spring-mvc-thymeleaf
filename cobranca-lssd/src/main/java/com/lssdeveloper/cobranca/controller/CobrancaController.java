package com.lssdeveloper.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lssdeveloper.cobranca.model.Cobranca;
import com.lssdeveloper.cobranca.repository.Cobrancas;

@Controller
@RequestMapping("/cobrancas")
public class CobrancaController {
	
	@Autowired
	private Cobrancas cobrancas;

	@RequestMapping("/novo")
	public String novo() {
		return "CadastroCobranca";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Cobranca cobranca) {
		cobrancas.save(cobranca);
		
		ModelAndView mv = new ModelAndView("CadastroCobranca");
		mv.addObject("mensagem", "Cobrança salva com sucesso!");
		return mv;
	}
	
}
