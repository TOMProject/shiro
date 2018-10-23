
$(function(){
	//初始用户页面调用的请求
	var user = {};
	initPaging(user)
	
	$(".btn").click(function(){
		alert(22)
		var username = $("#username").val();
		var phone = $("#phone").val();
		var user = {"userName":username,"phone":phone}
		initPaging(user)	
	});
	
});




function initPaging(user){
	
	 var json = JSON.stringify(user);
	 alert(json)
	 $.ajax({
	        type:"POST",
	        url:"../../user/listPaging",
	        data:json,
	        contentType:"application/json",
	        async:true,
			success:function(data){
				//alert(data);
				var dataObject = JSON.parse(data);
				var str = "";
				for(var i = 0 ;i<dataObject.data.length;i++){
					var cla = "";
					if(i%2 == 1){
						cla = 'class="active"'
					}
					var str1 ="<tr "+cla+">";
					str1 =str1+"<td>"+dataObject.data[i].userName+"</td>"
							  +"<td>"+dataObject.data[i].passWord+"角色名称</td>"
							  +"<td>"+dataObject.data[i].reallyName+"</td>"
							  +"<td>"+dataObject.data[i].phone+"</td>"
					var str2="</tr>";
					str += str1+str2	
				}
				alert(str);
				$('.userListPaging').empty();//清除旧的数据
				$('.userListPaging').append(str);//添加新的数据
			}	       
	    });
	
}



