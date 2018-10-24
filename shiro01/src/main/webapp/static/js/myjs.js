
$(function(){
	//初始用户页面调用的请求
	var user = {};
	initPaging(user)
	//点击查询按钮
	$(".btn").click(function(){
		var userName = $("#username").val();
		var phone = $("#phone").val();
		console.log(userName)
		if(userName != ""){
			user.userName=userName;
		}
		if(phone != ""){
			user.phone=phone;
		}
		//alert(user);
		initPaging(user)	
	});
	
	//点击全选checkbox
	$("#all").click(function(){
		if($("#all").attr("checked")){
			$(".mycheck").attr("checked",true)	
		}else{
			$(".mycheck").attr("checked",false)
		}
	})
	
	
	$(".mycheck").click(function(){
		
		
	});
	
	
	//获取一行的数据
	$(".getData").click(function(){
		$(".mycheck").each(function () {//循环店铺里面的商品
            if ($(this).is(":checked")) {//如果该商品被选中
            	var user = JSON.parse($(this).attr('data'));
                alert(user.id+"--"+user.userName);
            }
        });	
	})

});




function initPaging(user){
	
	 var json = JSON.stringify(user);
	// alert(user);
	 console.log(json);
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
					str1 =str1+"<td> <input  class='mycheck' name ='' value ='' type='checkbox' data='"+JSON.stringify(dataObject.data[i])+"'/> </td>"
							  +"<td>"+dataObject.data[i].userName+"</td>"
							  +"<td>"+(dataObject.data[i].roleName || '')+"</td>"
							  +"<td>"+dataObject.data[i].reallyName+"</td>"
							  +"<td>"+dataObject.data[i].phone+"</td>"
					var str2="</tr>";
					str += str1+str2	
				}
				//alert(str);
				$('.userListPaging').empty();//清除旧的数据
				$('.userListPaging').append(str);//添加新的数据
			}	       
	    });
	
}



