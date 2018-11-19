

var $addMenuForm=$("#addMenuform");


$(function(){
	console.log("菜单")
	createMenuTree();
	
	
	/**
	 * 保存菜单
	 * @returns
	 */
	$("#saveMenu").click(function(){
		console.log($("#menuName").val());


	})
	
});









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
