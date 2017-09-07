package com.lssdeveloper.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lssdeveloper.cobranca.model.Estado;
import com.lssdeveloper.cobranca.model.Regiao;
import com.lssdeveloper.cobranca.repository.Estados;


@Controller
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private Estados estados;
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroEstado";
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Estado estado) {
		estados.save(estado);
		ModelAndView mv = new ModelAndView("CadastroEstado");
		mv.addObject("mensagem", "Estado salvo com sucesso!");
		return mv;
	}
	@ModelAttribute("todasRegioes")
	public List<Regiao> todasRegioes(){
		return Arrays.asList(Regiao.values());
	}	
	

}
