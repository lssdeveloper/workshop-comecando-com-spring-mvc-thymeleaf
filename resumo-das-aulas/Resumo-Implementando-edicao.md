# Implementando a edição

Nesta aula foi implementado o botão de editar na tela de pesquisa.

###### Solução:

```html
PesquisaCobrancas.html

	<td class="text-center">
		<a class="btn btn-link btn-xs" th:href="@{/cobrancas/{codigo}(codigo=${cobranca.codigo})}">
			<span class="glyphicon glyphicon-pencil"></span>
		</a>
	</td>
```
```java
CobrancaController.java						
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Cobranca cobranca) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(cobranca);
		return mv;
	}
```	
E para finalizar a implementação da edição, incluir um campo oculto para que o spring entenda que é uma edição.
Pois se o código estiver vazio então para o spring é um método de inserção, senão é uma edição, simples assim.

```html
CadastroCobranca.html
	<div class="panel-body">
			
 		<input type="hidden" th:field="*{codigo}"/>
```
