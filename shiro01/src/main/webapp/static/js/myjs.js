
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
		getCondition(user)		
		initPaging(user)
		$(".form-control").removeClass("error");//去掉
		$(".error").hide();
	});

	/**
	 * 点击全选checkbox
	 */
	$("#all").click(function(){
		if($(this).is(":checked")){
			$(".mycheck").attr("checked",true)	
		}else{
			$(".mycheck").attr("checked",false)
		}
	})

	/**
	 * 关闭弹出框
	 */
	$("#close").click(function(){
		$("#warningInfo").hide(200);
	})
	/**
	 * 关闭模态框
	 */
	$(".close").click(function(){
		$(".form-control").removeClass("error");//去掉
		$(".error").hide();
	})
	/**
	 * 当点击添加用户时候执行
	 */
	$('#addUserModal').on('show.bs.modal', function () {
		//查询角色
		selectListRole({});
	})

	/**
	 * 点击保存用户的时候
	 */
	$("#saveUser").click(function(){
		if(! $("#adduserform").valid()){
			return;
		}
		saveUser();
	})

	/**
	 * 点击修改按钮
	 */
	//全局的返回user的对象的id;
	var id = -1;
	$("#updateUser").click(function(){
		var updateId = new Array();
		$(".mycheck").each(function () {//循环店铺里面的商品
            if ($(this).is(":checked")) {//如果该商品被选中
            	var user = JSON.parse($(this).attr('data'));
            	updateId.push(user);
            }
        });	
		
		if(updateId.length == 0){
			message("请选择修改的数据！");
			return;
		}
		if(updateId.length > 1){
			message("一次只能修改一个用户！");
			return;
		}
		var user = updateId[0];
		console.log(user);
		//数据回填
		$("#updateReallyName").val(user.reallyName);
		$("#updateUserName").val(user.userName);
		$("#updatePhone").val(user.phone);
		$("#updatePassWord").val(user.passWord);
		$("#updateRole").empty();//清除旧的数据
		$("#updateRole").append("<option value="+user.roleId+">"+user.roleName+"</option>");
		$('#updateUserModal').modal('show');
		id = user.id;//设置userid
	});
	/**
	 * 点击保存修改
	 */
	$("#saveUpdateUser").click(function(){
		
		if(! $("#updateuserform").valid()){
			return;
		}
		var user = getUpdateValue();
			user.id = id;
		updateUser(user);	
	})
	
	
	
	/**
	 * jquery。validator 验证表单提交。
	 */
	jQuery.validator.addMethod("chinese",function(value,element){
		var chinese = /^[\u4e00-\u9fa5]+$/;
		return this.optional(element)||(chinese.test(value));
	},"只能输入中文！");
	//规则名：byteRangeLength，value检测对像的值，element检测的对像,param参数  
    jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {  
        var length = value.length;  
       // alert(length);
        for (var i = 0; i < value.length; i++) {  
            if (value.charCodeAt(i) > 127) {  
                length++;  
            }  
        }  
        return this.optional(element) || (length >= param[0] && length <= param[1]);  
    }, $.validator.format("请确保输入的值在{4}-{8}个字节之间(一个中文字算2个字节)"));  

	valid={
		debug:true,
		rules:{
			reallyname:{
				required:true,
				chinese:true,//自定义验证规则
				byteRangeLength:[4,8] //判断字节的长度
			},
			username:"required",
			password:{
				required:true,
				minlength:6
			},
			phone:{
				required:true,
				digits:true //验证是整数
			},
			role:{
				required:true,
			}
			
		},
		messages:{
			reallyname:{
				required:"请输入真实姓名！",
			},
			username:"请输入用户名！",
			password:{
				required:"请输入密码！",
				minlength:"最小长度不低于六位！"	
			},
			phone:{
				requird:"电话号码必填！",
				digits:"输入必须是整数"
			},
			role:{
				required:"请选择角色！",
			}
		}
	}
	
	$("#adduserform").validate(valid);
	$("#updateuserform").validate(valid);
	
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
				var status = warningInfo(dataObject);//异常提示框
				if(status == false){
					return;
				}
				var str = "";
				for(var i = 0 ;i<dataObject.data.list.length;i++){
					var cla = "";
					if(i%2 == 1){
						cla = 'class="active"'
					}
					var str1 ="<tr "+cla+">";
					str1 =str1+"<td> <input  class='mycheck' name ='' value ='' type='checkbox' data='"+JSON.stringify(dataObject.data.list[i])+"'/> </td>"
							  +"<td>"+dataObject.data.list[i].userName+"</td>"
							  +"<td>"+(dataObject.data.list[i].roleName || '')+"</td>"
							  +"<td>"+dataObject.data.list[i].reallyName+"</td>"
							  +"<td>"+dataObject.data.list[i].phone+"</td>"
					var str2="</tr>";
					str += str1+str2	
				}
				$('.userListPaging').empty();//清除旧的数据
				$('.userListPaging').append(str);//添加新的数据
				
				var data = dataObject.data;
				paging(data);
				
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
		  $("#role").append("<option value=''>---请选择---</option>"+str);
	  }
  });

}
/**
 * 获取去修改用户input标签里面的值
 * @returns
 */
function getUpdateValue(){
	var reallyName = $("#updateReallyName").val();
	var userName = $("#updateUserName").val();
	var passWord = $("#updatePassWord").val();
	var roleId = $("#updateRole").val();
	var phone = $("#updatePhone").val();
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
	return user;
}
/**
 * 获取去添加用户input标签里面的值
 * @returns
 */
function getAddValue(){
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
	return user;
}

function saveUser(){
	var user = getAddValue();	
	var json = JSON.stringify(user);
	$.ajax({
		type:"POST",
		url:"../../user/addUser",
		data:json,
		contentType:"application/json",
		success:function(mydata){
			var object = JSON.parse(mydata);
			var status = warningInfo(object);
			if(status == false){
				return;
			}
			if(object.code == "0000"){
				$('#addUserModal').modal("hide")
			}
		}
	});
	
}
/**
 * 修改用户api
 * @param user
 * @returns
 */
function updateUser(user){	
	var json = JSON.stringify(user);
	console.log(json);
	$.ajax({
		type:"POST",
		url:"../../user/updataUser",
		data:json,
		contentType:"application/json",
		success:function(mydata){
			console.log(mydata);
			var object = JSON.parse(mydata);
			var status = warningInfo(object);
			if(status == false){
				return;
			}
			if(object.code == "0000"){
				$('#addUserModal').modal("hide")
			} 
		}
	})
}


/**
 * 异常提示框
 * @param dataObject
 * @returns
 */
function  warningInfo(dataObject){
	if(dataObject.code == "0001"){
		$("#showWarningInfo").text("警告！："+dataObject.msg)
		$("#warningInfo").fadeIn(200);
		return false;
	}
	return true;
}

function message(msg){
	$("#showWarningInfo").text("警告！："+msg)
	$("#warningInfo").show(200);
	
}

/**
 * 分页
 */
function paging (data){
		layui.use('laypage', function() {	
		var laypage = layui.laypage;
		console.log(data.pageCount);
		laypage.render({
			elem : 'paging', // 注意，这里的 test1 是 ID，不用加 # 号
			count : data.pageCount,// 数据总数，从服务端得到
			limit : 5,
			curr : data.pageNo, // 获取起始页
			jump : function(obj, first) {// 切换分页回调函数
				// obj包含了当前分页的所有参数，比如：
				console.log(obj.curr); // 得到当前页，以便向服务端请求对应页的数据。
				console.log(obj.limit); // 得到每页显示的条数
				// 首次不执行
				if (!first) {
					console.log(user);
					var user ={};
					getCondition(user)	
					user.pageNo = obj.curr;
					user.pageSize=obj.limit;
					initPaging(user);
				}
			}
		});
	
	});
}

/**
 * 获取查询条件
 * @param user
 * @returns
 */

function getCondition(user){
	var userName = $("#username").val();
	var phone = $("#phone").val();
	console.log(userName)
	if(userName != ""){
		user.userName=userName;
	}
	if(phone != ""){
		user.phone=phone;
	}
}


