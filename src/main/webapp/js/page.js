/**
 * ajaxPage：bootstrap-paginator实现异步分页
 */
$.fn.extend({
	ajaxPage: function(pageDiv, pageSize){
		pageSize = isNaN(pageSize) ? 10 : pageSize;
		var container = $(this);
		$.ajax({
			url : $(pageDiv).attr('rel-url'),
			type : 'POST',
			datatype : 'json',
			data : {'pageSize' : pageSize},
			success : function(data) {
				if (data) {
					$(container).html(data);
					var options = {
						bootstrapMajorVersion : 3,
						currentPage : $(pageDiv).attr('pageNum'),
						totalPages : Math.ceil($(pageDiv).attr('totalSize') / pageSize),
						numberOfPages : 13,
						itemTexts : function(type, page, current) {
							switch (type) {
							case "first":
								return "首页";
							case "prev":
								return "上一页";
							case "next":
								return "下一页";
							case "last":
								return "末页";
							case "page":
								return page;
							}
						},
						onPageClicked : function(event, originalEvent, type, page) {
							$.ajax({
								url : $(pageDiv).attr('rel-url'),
								type : "POST",
								data : {
									'pageNum' : page,
									'pageSize' : pageSize
								},
								success : function(data) {
									if (data) {
										$(container).html(data);
										options.currentPage = page;
										$(pageDiv).find('ul').bootstrapPaginator(options);
										$(pageDiv).find('#pageBar_total').text('共 '+ $(pageDiv).attr('totalSize') +' 条');
									}
								}
							})
						},
					};
					$(pageDiv).find('ul').bootstrapPaginator(options);
					$(pageDiv).find('#pageBar_total').text('共 '+ $(pageDiv).attr('totalSize') +' 条');
				}
			}
		});
	},
});

$.extend({
	changePage: function(obj){
		var pageDiv = $(obj).parent().parent();
		$('.listPage-container').ajaxPage('#' + $(pageDiv).prop('id'), obj.value);
	}
});