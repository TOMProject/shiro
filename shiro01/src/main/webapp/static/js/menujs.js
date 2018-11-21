
//获取根路径
var validator;
var pathName =document.location.pathname;
var index =pathName.substr(1).indexOf("/");
var path =pathName.substr(0,index+1);
var $addMenuForm=$("#addMenuform");


$(function(){
	createMenuTree();
	validateRule();
	
	/**
	 * 保存菜单
	 * @returns
	 */
	$("#saveMenu").click(function(){
		var flag = $("#addMenuform").valid();
		console.log(flag);
		if(!flag){
			console.log("验证为不通过！")
			return;
		}
		var menuName = $("#menuName").val();
		var menuType = $("input[name='menuType']:checked").val();
		var permission = $("#permission").val();
        getMenu();
        var parentId = $("#parentId").val();
        var menu = {"menuName":menuName,"menuType":menuType,"permission":permission,"parentId":parentId};
        var jsonData = JSON.stringify(menu);
        console.log(jsonData);
        $.ajax({
        	type:"POST",
        	url:path+"/menu/addMenu",
        	data:jsonData,
        	contentType:"application/json",
        	success:function(data){
        		$('#addMenuform').modal("hide");
        		$("#addMenuform")[0].reset();
        		createMenuTree();
        		var menu = JSON.parse(data);
        		console.log(menu);
        		
        	}
        })
        

	})
	
});
/**
 * 验证规则
 * @returns
 */
function validateRule(){	
	valid={
		onfocusout: false,
		rules: {
			menuName:{
				required: true,
				minlength: 2,
				maxlength: 10,
				remote:{
					type: "get",
					url: path+"/menu/checkMenuName",
					dataType: "json",
					data: {
						menuName:function(){
							return $("#menuName").val().trim();
						},					
						menuType:function(){
							return $("input[name='menuType']:checked").val();
						}
	
					},
					dataFilter:function(data){
						console.log("my-->"+data)
						  if (data == true) {
                              //alert("正确时怎样处理");
                          } else {
                              //alert("错误时怎样处理");
                          }
						  return data;

					}
					
				}
			}
		},
		messages: {
			menuName:{
				required: "请输入名称",
				minlength: "名称长度在2-10个字符",
				maxlength: "名称长度在2-10个字符",
				remote: "该名称已经存在"	
			}
	
		}
		
		
	};
	
	$("#addMenuform").validate(valid);

}



$("#addMenuModel").click(function(){
	$('#addMenuform').modal("show")
})
	
function createMenuTree() {
    $.post("../../menu/tree", {}, function (r) {
    	 var r=JSON.parse(r);
    	
        if (r.code == "0000") {
            var data = r.data;
            console.log(data);
            
            $('#menuTree').jstree({
                "core": {
                    'data': data.children,
                    'multiple': false
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
           console.log("异常！--》"+r)
        }
    })

}	

function getMenu() {
    var ref = $('#menuTree').jstree(true);
    $("[name='parentId']").val(ref.get_checked()[0]);
}	
