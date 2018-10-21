
$(function(){
//  $('.loginbtn').click(function(){
//	  var username =  document.getElementById("name").value;
//	    alert(username);
//	    var password = document.getElementById("password").value;
//	    alert(password);
//	    var user = {"userName":username,"passWord":password};
//	    var json = JSON.stringify(user);
//	    //var json = $.toJSON(user);
//	    alert(json);
//	    $.ajax({
//			type:"POST",
//			url:"../../user/login",
//			data:json,
//			contentType:"application/json",
//			async:true,
//			success:function(msg){
//				alert(msg);
//			}
//		});
//  })


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