
$(function(){
  

   

});

function login(){

    var loginName =  document.getElementById("name").value;
    alert(loginName);
    var loginPwd = document.getElementById("password").value;
    alert(loginPwd);
    var user = {"username":loginName,"password":loginPwd};
    var json = JSON.stringify(user);
     alert(json);
    $ajax({
        type:POST,
        url:"user/login",
        data:json,
        contentType:"application/json",
        success:function(mydata){
            alert(mydata);
        },
        error:function(mydata){
        	 alert(mydata);
        }
    })

 }