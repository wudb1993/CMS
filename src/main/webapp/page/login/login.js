layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
	$(window).resize(function(){
		if($(".video-player").width() > $(window).width()){
			$(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}else{
			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}
	}).resize();
	
	//登录按钮事件
    form.on("submit(login)",function(data){
        var userName = $("#userName").val();
        var loginPwd = $("#loginPwd").val();
        $.ajax({
            url:'/user/login.do',
            type:'get',
            cache:false,
            async:false,
            data:{
                userName:userName,
                loginPwd:loginPwd
            },
            dataType:"json",
            success:function(data){
                if(data.model.result=='success'){
                    location.href = '/index.html';
                }else{
                    if(data.model.result==1){
                        layer.tips("密码不对啊大兄弟，再猜猜？",'#loginPwd',
                            {
                                tips: [2, '#FF82AB']
                            });
                    }
                    if(data.model.result==2){
                        layer.tips("用户名都不对啊，大兄弟",'#userName',
                            {
                                tips: [2, '#FF82AB']
                            });
                    }
                }

            },

        })

        return false;
    })
})
