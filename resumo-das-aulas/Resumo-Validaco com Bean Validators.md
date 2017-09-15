# Validações com Beans Validations

#### 1º CadastroEstado.html

Na tag form inserir o modelo do obejto a ser transferido **th:object="${estado}"**
```html
    <form class="form-horizontal" method="POST" action="/estados" th:object="${estado}">
```
#### 2º EstadoController.java

Aqui foi incluído um ModelAndView para passar o objeto "estado" referenciado em form anteriormente.

```java
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroEstado");
		mv.addObject(new Estado());
		return mv;
	}
```
#### 3º Mensagem.html

Os alertas de mensagens foram transferidos para esta página.
Em especial a tag abaixo captura os erros que serão gerados ao clicar em salvar()

```html
    <div class="alert alert-danger" th:if="${#fields.hasAnyErrors()}">
	    <div th:each="detailedError : ${#fields.detailedErrors()}">
		    <span th:text="${detailedError.message}"></span>
	    </div>
    </div>
```

###### Veja como fica o código final

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<div class="alert alert-success" th:if="${!#strings.isEmpty(mensagem)}">
	<span th:text="${mensagem}"></span>
</div>
<div class="alert alert-danger" th:if="${#fields.hasAnyErrors()}">
	<div th:each="detailedError : ${#fields.detailedErrors()}">
		<span th:text="${detailedError.message}"></span>
	</div>
</div>

</html>
```
#### 4º CadastroEstado.html

Incluído o include e os campos que serão validados

```html
 	<div layout:include="Mensagem"></div>

				<div class="form-group" th:classappend="${#fields.hasErrors('sigla')} ? has-error">
					<label for="sigla" class="col-sm-2 control-label">Sigla</label>
					<div class="col-sm-2">  
						<input type="text" class="form-control"	id="sigla" name="sigla"/>
					</div>
				</div>
```
#### 5º EstadoController.java

Alteração do método salvar
Inclusão dos parâmetros:

```java
   (@Validated Estado estado, Errors errors)
```

Inclusão desta linha 
```java
		if (errors.hasErrors()) {
			return mv;
		}
```
###### Veja como fica o código final

```java
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Estado estado, Errors errors) {
		ModelAndView mv = new ModelAndView("CadastroEstado");
		if (errors.hasErrors()) {
			return mv;
		}
		estados.save(estado);	

		mv.addObject("mensagem", "Estado salvo com sucesso!");
		return mv;
	}
```
#### 6º Estado.java

Inclusão dos Beans Validators

Segue alguns exemplos:

```java
   	@NotEmpty(message="Descricao é obrigatório.")
	@Size(max=60, message="Tamanho máximo para descrição é de 60 caracteres.")	
	private String descricao;

	@NotNull(message="Data de vencimento é obrigatória.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@DecimalMin(value="0.01", message="Informe um valor maior que zero.")
	@DecimalMax(value="9999999.99", message="Informe um valor menor que 9.999.999,99")
	@NotNull(message="Valor é obrigatório.")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
```
    