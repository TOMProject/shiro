

$(function(){
	createMenuTree();
	
	
})

$("#addMenuModel").click(function(){
	$('#addUserModal').modal("show")
})
	
function createMenuTree() {
    $.post(ctx + "menu/tree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
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
            $MB.n_danger(r.msg);
        }
    })

}	

function getMenu() {
    var ref = $('#menuTree').jstree(true);
    $("[name='parentId']").val(ref.get_checked()[0]);
}	
