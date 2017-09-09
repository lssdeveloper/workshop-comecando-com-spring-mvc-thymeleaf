# Template com Thymeleaf

http://www.ultraq.net.nz/thymeleaf/layout

`Transformar códigos repetidos de html em um layout padrão(template).`

Não é preciso configurar nada, pois no pom.xml quando informamos a dependência do Spring Boot o mesmo já incorpora a referência do ultraq.

```
Objectivo dessa aula:

 - definir um layout padrão.
 - definir uma seção (conteudo) que será preenchida por uma página.
 - definir o decorator (LayoutPadrao), inclusive posso ter vários layout para outras páginas.
 - definir um include (cabecalho).
 - apresentação da classe .clearfix do bootstrap, que faz um ajuste na bagunça deixada ao utilizar o float no css.
```

Veja abaixo as inclusões necessárias para introduzir um template:


```html
LayoutPadrao.html

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
		xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="UTF-8" />
<title>Cobranças</title>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>

	<header layout:include="Cabecalho"></header>

	<section layout:fragment="conteudo">
		<p>Conteúdo principal</p>
	
	</section>
	
	<script src="/js/jquery-2.1.4.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>	
</body>
</html>	

```

```html
Cabecalho.html

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">


	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <img alt="LSS Developer"
					src="/images/logo-lssdeveloper.png" />
				</a>
			</div>
		</div>
	</nav>

</html>

```

```html
CadastroCobranca.html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorator="LayoutPadrao">
<head>

<title>Cadastro de cobrança</title>

</head>
<section layout:fragment="conteudo">
	<form class="form-horizontal" method="POST" action="/cobrancas">
		<div class="alert alert-success"
			th:if="${!#strings.isEmpty(mensagem)}">
			<span th:text="${mensagem}">Cobrança salva com sucesso?</span>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h1 class="panel-title">Nova cobrança</h1>
			</div>

			<div class="panel-body">
				<div class="form-group">
					<label for="descricao" class="col-sm-2 control-label">Descrição</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="descricao"
							name="descricao" />
					</div>
				</div>
				<div class="form-group">
					<label for="dataVencimento" class="col-sm-2 control-label">Data
						de vencimento</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="dataVencimento"
							name="dataVencimento" />
					</div>
				</div>
				<div class="form-group">
					<label for="valor" class="col-sm-2 control-label">Valor</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="valor" name="valor" />
					</div>
				</div>
				<div class="form-group">
					<label for="status" class="col-sm-2 control-label">Status</label>
					<div class="col-sm-2">
						<select class="form-control" name="status">
							<option th:each="status : ${todosStatusCobranca}"
								th:value="${status}" th:text="${status.descricao}"></option>

						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">Salvar</button>
					</div>
				</div>
			</div>
		</div>

	</form>
</section>

</html>
```
```html
PesquisaCobranca.html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorator="LayoutPadrao">


<head>

<title>Pesquisa de cobranças</title>

</head>

<section layout:fragment="conteudo">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h1 class="panel-title">Pesquisa de cobranças</h1>
		</div>

		<div class="panel-body">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th class="text-center col-md-1">#</th>
						<th>Descrição</th>
						<th class="text-center col-md-2">Data de Vencimento</th>
						<th class="text-right col-md-2">Valor</th>
						<th class="text-center col-md-2">Status</th>
						<th class="col-md-1"></th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td colspan="6">Nenhum registro foi encontrado.</td>
					</tr>
				</tbody>

			</table>
		</div>
	</div>
</section>

</html>
```
Inclusão de um link para voltar na página de CadastroCobranca.html

Aqui foi preciso incluir algumas configuraçãoes no style.css

```html
Novidade:
    class="clearfix"
```
No bootstrap, getBootStrap.com, tem uma classe .clearfix, ela ajuda a configurar alguns problemas quando adicionamos a propriedade float em uma classe:
Na documentação do getBootStrap tem uma referência do uso desta classe para maiores esclarecimentos.
Devido a bagunça gerada quando atribuimos um float.

```html
PesquisaCobranca.html
<div class="panel-heading">
	<div class="clearfix">
	    <h1 class="panel-title lssd-titulo-panel">Pesquisa de cobranças</h1>
	    <a class="btn btn-link lssd-link-panel" href="/cobrancas/novo">Nova Cobrança</a>
	</div>
</div>
```
```html
CadastroCobranca.html
<div class="panel-heading">
    <div class="clearfix">
    	<h1 class="panel-title lssd-titulo-panel">Nova cobrança</h1>
    	<a class="btn btn-link lssd-link-panel" href="/cobrancas">Pesquisa Cobrança</a>
	</div>
</div>
```
Resultado:
```html
PesquisaCobranca.html
<section layout:fragment="conteudo">

	<div class="panel panel-default">

		<div class="panel-heading">
			<div class="clearfix">
				<h1 class="panel-title lssd-titulo-panel">Pesquisa de cobranças</h1>
				<a class="btn btn-link lssd-link-panel" href="/cobrancas/novo">Nova Cobrança</a>
			</div>
		</div>

		<div class="panel-body">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th class="text-center col-md-1">#</th>
						<th>Descrição</th>
						<th class="text-center col-md-2">Data de Vencimento</th>
						<th class="text-right col-md-2">Valor</th>
						<th class="text-center col-md-2">Status</th>
						<th class="col-md-1"></th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td colspan="6">Nenhum registro foi encontrado.</td>
					</tr>
				</tbody>

			</table>
		</div>
	</div>
</section>
```
```html
CadastroCobranca.html
<section layout:fragment="conteudo">
	<form class="form-horizontal" method="POST" action="/cobrancas">
		<div class="alert alert-success"
			th:if="${!#strings.isEmpty(mensagem)}">
			<span th:text="${mensagem}">Cobrança salva com sucesso?</span>
		</div>

		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="clearfix">
					<h1 class="panel-title lssd-titulo-panel">Nova cobrança</h1>
					<a class="btn btn-link lssd-link-panel" href="/cobrancas">Pesquisa Cobrança</a>
				</div>
			</div>

			<div class="panel-body">
				<div class="form-group">
					<label for="descricao" class="col-sm-2 control-label">Descrição</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="descricao"
							name="descricao" />
					</div>
				</div>
				<div class="form-group">
					<label for="dataVencimento" class="col-sm-2 control-label">Data
						de vencimento</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="dataVencimento"
							name="dataVencimento" />
					</div>
				</div>
				<div class="form-group">
					<label for="valor" class="col-sm-2 control-label">Valor</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="valor" name="valor" />
					</div>
				</div>
				<div class="form-group">
					<label for="status" class="col-sm-2 control-label">Status</label>
					<div class="col-sm-2">
						<select class="form-control" name="status">
							<option th:each="status : ${todosStatusCobranca}"
								th:value="${status}" th:text="${status.descricao}"></option>

						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">Salvar</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</section>
```
Inclusão do respectivo CSS para alinhar e direcionar os links.
```css
style.css

.lssd-link-panel {
	float: right;
	padding: 0px;
}
.lssd-titulo-panel {
	float: left;
	padding-top: 2px; 
}
```
