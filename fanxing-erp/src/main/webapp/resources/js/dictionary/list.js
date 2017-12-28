$(function() {
	var $dictionaryName = $('.dictionaryName');
	$dictionaryName.css('cursor', 'pointer');
	$dictionaryName.click(function() {
		var leaf = $(this).data('leaf');
		$('#parentId').val(leaf == 1 ? $(this).data('id') : null);
		loadURL($searchForm.attr('action'), $container, $searchForm
				.serializeJson());
		return false;
	});
});