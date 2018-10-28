
$(function(){
	/**
	 * 初始用户页面调用的请求
	 */
	initPaging({})
	/**
	 * 点击查询按钮
	 */
	$(".btn").click(function(){
		var user = {};
		var userName = $("#username").val();
		var phone = $("#phone").val();
		console.log(userName)
		if(userName != ""){
			user.userName=userName;
		}
		if(phone != ""){
			user.phone=phone;
		}
		initPaging(user)	
	});
	
	/**
	 * 点击全选checkbox
	 */
	$("#all").click(function(){
		if($("#all").attr("checked")){
			$(".mycheck").attr("checked",true)	
		}else{
			$(".mycheck").attr("checked",false)
		}
	})

	/**
	 * 获取一行的数据
	 */
	$(".getData").click(function(){
		$(".mycheck").each(function () {//循环店铺里面的商品
            if ($(this).is(":checked")) {//如果该商品被选中
            	var user = JSON.parse($(this).attr('data'));
                alert(user.id+"--"+user.userName);
            }
        });	
	})

	/**
	 * 点击框时清空里面的数据
	 */
//	$("input").click(function(){
//		$(this).val("");
//	})

		
	/**
	 * 当点击添加用户时候执行
	 */
	$('#myModal').on('show.bs.modal', function () {
		//查询角色
		selectListRole({});
	})

	/**
	 * 点击保存用户的时候
	 */
	$("#saveUser").click(function(){
		saveUser();
	})

	
});




function initPaging(user){
	 var json = JSON.stringify(user);
	 console.log(json);
	 $.ajax({
	        type:"POST",
	        url:"../../user/listPaging",
	        data:json,
	        contentType:"application/json",
	        async:true,
			success:function(data){
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
				$('.userListPaging').empty();//清除旧的数据
				$('.userListPaging').append(str);//添加新的数据
			}	       
	    });
}

function selectListRole(role){
  var role=JSON.stringify(role);
  $.ajax({
	  type:"POST",
	  url:"../../role/getRoleList",
	  data:role,
	  contentType:"application/json",
	  success:function(mydata){
		  var roleObject = JSON.parse(mydata);
		  var str = ""
		  for(var i= 0 ;i<roleObject.data.length;i++){
			  str=str+ "<option value="+"'"+roleObject.data[i].id+"'"+">"+roleObject.data[i].roleName+"</option>"			  		
		  }
		  $("#role").empty();
		  $("#role").append(str);		  
	  }
  });

}

function saveUser(){
	var reallyName = $("#reallyName").val();
	var userName = $("#userName").val();
	var passWord = $("#passWord").val();
	var roleId = $("#role").val();
	var phone = $("#addphone").val();
	var user ={};
	if(reallyName != ""){
		user.reallyName = reallyName;
	}
	if(userName != ""){
		user.userName = userName;
	}
	if(passWord != ""){
		user.passWord=passWord;
	}
	if(role != ""){
		user.roleId = roleId;
	}
	if(phone != ""){
		user.phone=phone;
	}
	
	var json = JSON.stringify(user);
	$.ajax({
		type:"POST",
		url:"../../user/addUser",
		data:json,
		contentType:"application/json",
		success:function(mydata){
			console.log(mydata);
			var object = JSON.parse(mydata);
			if(object.code == "0000"){
				$('#myModal').modal("hide")
			}
			if(object.code == "0001"){
				
			}
		}
	});
	
}





