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
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o Estado <strong>' + descricaoEstado + '</strong>?');
});