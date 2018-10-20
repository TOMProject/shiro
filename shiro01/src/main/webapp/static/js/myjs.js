
$(function(){
  $('.loginbtn').click(function(){
	  var username =  document.getElementById("name").value;
	    alert(username);
	    var password = document.getElementById("password").value;
	    alert(password);
	    var user = {"userName":username,"passWord":password};
	    var json = JSON.stringify(user);
	    //var json = $.toJSON(user);
	    alert(json);
	    $.ajax({
			type:"POST",
			url:"../../user/login",
			data:json,
			contentType:"application/json",
			async:true,
			success:function(msg){
				alert(msg);
				
			}
		
		});
//	    $.ajax({
//	        type:"POST",
//	        url:"user/login",
//	        data:json,
//	        contentType:"application/json",
//	        async:true,
//			success:function(data){
//				alert(data);
//				alert(data.msg);
//			}
//	       
//	    });
 
  })


});

//function login(){
//
//    var loginName =  document.getElementById("name").value;
//    alert(loginName);
//    var loginPwd = document.getElementById("password").value;
//    alert(loginPwd);
//    var user = {"username":loginName,"password":loginPwd};
//    var json = JSON.stringify(user);
//    //var json = $.toJSON(user);
//    alert(json);
//    $.ajax({
//        type:"POST",
//        url:"user/login",
//        data:json,
//        contentType:"application/json",
//        async:true,
//		success:function(data){
//			alert(data);
//			alert(data.msg);
//		}
//       
//    });
//
// }