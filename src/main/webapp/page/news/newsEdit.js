layui.config({
    id:"",
	base : "js/"
}).use(['form','layer','jquery','layedit'],function() {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        $ = layui.jquery;
    initForm();
    //创建一个编辑器
    var editIndex = layedit.build('news_content');
    var addNewsArray = [], addNews;

    function initForm() {
        var param = {
            "id": id,
            "noticeType": "1"
        }
        $.ajax({
            url: '/systemNotice/queryArticle.do',
            type: 'get',
            cache: false,
            async: false,
            data: {param: JSON.stringify(param)},
            dataType: "json",
            success: function (data) {
                console.log(data);
                data = data.model.articleList[0];
                $(".newsName").val(data.title);
                if (data.permission == '1') {
                    var dom = "<option value='1'selected>开放浏览</option>";
                    dom = dom + "<option value='2'>会员浏览</option>"
                    $(".newsLook").html(dom)
                } else if (data.permission == '2') {
                    var dom = "<option value='1'>开放浏览</option>";
                    dom = dom + "<option value='2'selected>会员浏览</option>"
                    $(".newsLook").html(dom)
                }
                if (data.content != null && data.content != '' && data.content != undefined) {
                    $("#news_content").val(data.content);
                }
                if (data.general != null && data.general != '' && data.general != undefined) {
                    $("#general").val(data.general);
                }
                form.render();
            }
        })
    }

    form.on("submit(addNews)", function (data) {
        console.log("qweq");
        var param = {
            "articleId":id,
            "title": $(".newsName").val(),
            "permission": $(".newsLook").val(),
            "general": $("#general").val(),
            "content": $("#news_content").val()
        }
        $.ajax({
            url: '/systemNotice/updateArticle.do',
            type: 'get',
            cache: false,
            async: false,
            data: {param: JSON.stringify(param)},
            dataType: "json",
            success: function (data) {
                console.log("success");
                if (data.model.result == 'success') {
                    location.href = '/index.html';
                } else {
                }
            },
        })
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            top.layer.close(index);
            top.layer.msg("文章修改成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        },2000);
        return false;
    })

})
function getId(ids){
    console.log(ids);
    id=ids;
}
