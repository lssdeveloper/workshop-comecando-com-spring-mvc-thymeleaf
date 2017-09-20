$('#confirmacaoExclusaoModalCobranca').on('show.bs.modal', function(event) {
	//pego o botão que disparou o evento
	var button = $(event.relatedTarget);
	//função do jquery (.data) para pegar o código e a descrição th:attr="..."
	var codigoCobranca = button.data('codigo');
	var descricaoCobranca = button.data('descricao');
	//pega o modal
	var modal = $(this);
	//método find para encontrar o componente form
	var form = modal.find('form');
	//pega o atributo action dentro do form
	
	//truque para se não terminar com a a barra incluí-la com o código
	//ex: /cobrancas/8
	var action = form.attr('action');
	if (!action.endsWith('/')) {
		action += '/';
	}
	//altera o action desse form
	form.attr('action', action + codigoCobranca);
	//coloca uma mensagem para confirmar 
	modal.find('.modal-body span').html('Tem certeza que deseja excluir a cobrança <strong>' + descricaoCobranca + '</strong>?');
});