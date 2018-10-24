
/**
 * 登陆的js
 * @returns
 */
function login(){
	var user ={};
	var loginName = $(".loginuser:first").val();
    var loginPwd = $(".loginpwd:eq(0)").val();
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
			//alert(data);
			var dataObject = JSON.parse(data);
			if(dataObject.code == "0000"){
				window.location.href="main.html";
			}
		}
       
    });
	
}
