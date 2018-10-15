	function deleteConfirm(url,name){
		var result = confirm("您确认要删除<"+name+">吗");
		if(result){
			window.location.href=url;
		}
	}
	
	function ajaxSelect(name,position,typecode,selectedId){
		//1.创建select对象
		//2.添加请选择option
		var $select = $("<select name='"+name+"' ><option value='' >---请选择---</option></select>");
		//3.发送ajax请求
		$.getJSON("${pageContext.request.contextPath }/BaseDictAction", { dict_type_code: typecode}, function(json){
		//4.得到响应,遍历json对象.
			$.each( json, function(i, dict){
				//创建option对象,
				var $option = $("<option value='"+dict['dict_id']+"' >"+dict['dict_item_name']+"</option>");
				
				//判断当前的option是否需要被选中
				if(selectedId == dict['dict_id']){
					$option.attr("selected","selected");
				};
				//并放入select中
					$($select).append($option);
				});
		});
		//5.将select放入页面中的指定位置
		$("#"+position).append($select);
	};
	
	function changePageSize(pageSize){
		//1 为表单pageSize隐藏域赋值
		$("#pageSizeInput").val(pageSize);
		//2 表单提交
		$("#pageForm").submit();
		
	};
	function changeCurrentPage(currentPage){
		//1 为表单currentPage隐藏域赋值
		$("#currentPageInput").val(currentPage);
		//2 表单提交
		$("#pageForm").submit();
	};