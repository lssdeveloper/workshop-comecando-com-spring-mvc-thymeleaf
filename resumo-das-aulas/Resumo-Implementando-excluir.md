# Implementando a exclusão

Nesta aula foi implementado o botão de excluir na tela de pesquisa.

###### Solução:

Incluído o arquivo DialogoConfirmacaoExclusaoEstado.html
```html 
DialogoConfirmacaoExclusaoEstado.html

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<div class="modal fade" id="confirmacaoExclusaoModalEstado" tabindex="-1" 
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<form action="/estados" method="POST">
			<input type="hidden" name="_method" value="DELETE"/>
			
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-tittle">Você tem certeza?</h4>
				</div>
				<div class="modal-body">
					<span>Tem certeza que deseja excluir este estado?</span>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-link" 
						data-dismiss="modal">Cancelar</button>
					<button type="submit" class="btn btn-primary">Excluir</button>
				</div>
			</div>
		</form>
	</div>
</div>

</html>
```

## Alterações

*Incluir os arquivos javascript abaixo:*

```html
LayoutPadrao.html
	<script src="/js/cobranca.js"></script>
	<script src="/js/estado.js"></script>
```
*Conteúdo dos arquivos javascript*

```javascript
estado.js
$('#confirmacaoExclusaoModalEstado').on('show.bs.modal', function(event) {
	//pego o botão que disparou o evento
	var button = $(event.relatedTarget);
	//função do jquery (.data) para pegar o código e a descrição th:attr="..."
	var codigoEstado = button.data('codigo');
	var descricaoEstado = button.data('descricao');
	//pega o modal
	var modal = $(this);
	//método find para encontrar o componente form
	var form = modal.find('form');
	//pega o atributo action dentro do form
	
	//truque para se não terminar com a a barra incluí-la com o código
	//ex: /estados/8
	var action = form.attr('action');
	if (!action.endsWith('/')) {
		action += '/';
	}
	//altera o action desse form
	form.attr('action', action + codigoEstado);
	//coloca uma mensagem para confirmar 
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o Estado <strong>' 
		+ descricaoEstado + '</strong>?');
});
```

```html
PesquisaEstados.html
	<div layout:include="DialogoConfirmacaoExclusaoEstado"></div>
```
```html
	<a class="btn btn-link btn-xs" data-toggle="modal"
			data-target="#confirmacaoExclusaoModalEstado"
			th:attr="data-codigo=${estado.codigo}, data-descricao=${estado.descricao}">
				<span class="glyphicon glyphicon-remove"></span>
	</a>
```

*Mensagem.html foi desmembrada em dois arquivos*

```html
MensagemErro.html
	<div class="alert alert-danger" th:if="${#fields.hasAnyErrors()}">
		<div th:each="detailedError : ${#fields.detailedErrors()}">
			<span th:text="${detailedError.message}"></span>
		</div>
	</div>
```
```html
MensagemGeral.html
	<div class="alert alert-success" th:if="${!#strings.isEmpty(mensagem)}">
		<span th:text="${mensagem}"></span>
	</div>
```
*No caso de Cadastro deve-se incluir os dois arquivos de mensagem como abaixo:*
```html
CadastroEstado.html
	<div layout:include="MensagemGeral"></div>
	<div layout:include="MensagemErro"></div>
```

*Incluindo a mensagem de exclusão com sucesso na pesquisa*

```html
PesquisaEstados.html
	<section layout:fragment="conteudo">
		<div layout:include="MensagemGeral"></div>
```

```java
EstadoController.java						
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		
		estados.delete(codigo);	
		attributes.addFlashAttribute("mensagem", "Estado excluído com sucesso!");
		return "redirect:/estados";
	}
```	
