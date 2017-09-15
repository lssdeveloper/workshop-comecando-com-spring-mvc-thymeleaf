# Mantendo os valores no formulário

*Neste resumo corrigimos um problema da aula anterior onde os valores são validados mas somem as descrições.*

Isto acontece porque o thymeleafe não reconhece no momento a permanencia desses dados devido a tag `name`.

###### Solução:

Nesse caso estou dizendo para o thymeleafe que `th:field` é um campo do `objeto cobranca`

#### 1º Substituir todos os `name` por `th:field` 

```html
CadastroCobranca.html
	<input type="text" class="form-control" id="descricao" th:field="*{descricao}"/>
```
*O mesmo deve ser feito para os demais campos do objeto respectivo.*

#### 2º Limpando o formulário quando salva o registro.

###### Solução:

*Usando redirect*

```java
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Estado estado, Errors errors, RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return "CadastroEstado";
		}
		estados.save(estado);	
		attributes.addFlashAttribute("mensagem", "Estado salvo com sucesso!");
		return "redirect:/estados/novo";
	}
```

