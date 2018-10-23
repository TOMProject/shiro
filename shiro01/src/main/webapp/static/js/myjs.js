
$(function(){
	//初始用户页面调用的请求
	var user = {};
	 var json = JSON.stringify(user);
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
				$('.userListPaging').append(str);
			}	       
	    });
});

function login(){

    var loginName =  document.getElementById("name").value;
    var loginPwd = document.getElementById("password").value;
    var user = {"userName":loginName,"passWord":loginPwd};
    var json = JSON.stringify(user);
    //var json = $.toJSON(user);
    $.ajax({
        type:"POST",
        url:"../../user/login",
        data:json,
        contentType:"application/json",
        async:true,
		success:function(data){
			alert(data);
			var dataObject = JSON.parse(data);
			alert(dataObject.code);
			alert(dataObject.msg);
			alert(dataObject.data.userName)
		}
       
    });

 }