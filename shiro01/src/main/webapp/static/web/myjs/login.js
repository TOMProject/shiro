
/**
 * 登陆的js
 * @returns
 */
function login(){
	var loginName = $(".loginuser:first").val();
	alert(loginName);
    var loginPwd = $(".loginpwd:eq(0)").val();
    alert(loginPwd);
    var user = {"userName":loginName,"passWord":loginPwd};
    var json = JSON.stringify(user);
    //var json = $.toJSON(user);
    alert(loginPwd);
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
			if(dataObject.code == "0000"){
				window.location.href="main.html";
			}
		}
       
    });
	
}
