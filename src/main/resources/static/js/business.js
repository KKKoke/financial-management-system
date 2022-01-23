//业务单位交易的js代码实现
layui.use(['table','form','layer'], function(){
    var table = layui.table;
    var $=layui.jquery;
    var form = layui.form;
    var layer=layui.layer
    //展示所有的交易信息数据
    table.render({
        elem: '#businessData'
        ,height: 600
        ,url: '/deals'
        ,method:'get'
        ,where: {

        }
        ,cols: [
            [ //表头
                {field: 'id', title: '序号', width:250, sort: true, fixed: 'left'}
                ,{field: 'leftMoney', title: '欠款', width: 250}
                ,{field: 'company', title: '业务相关单位',width: 550}
                ,{fixed: 'right',title: '操作', align:'center', toolbar: '#barDemo'}
            ]
        ]
        , toolbar: '#toolbar'
        , page: true //开启分页
        , limits: [3, 5, 10]  //一页选择显示3,5或10条数据
        , limit: 10  //一页显示10条数据
        , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据，res为从url中get到的数据
            var result;
            if (this.page.curr) {
                result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
            } else {
                result = res.data.slice(0, this.limit);
            }
            return {
                "code": 0, //解析接口状态
                "msg": "ok", //解析提示文本
                "count": res.data.length, //解析数据长度
                "data": result //解析数据列表
            };
        }
    });
});

function business() {
    layui.use(['table'], function() {
        var table = layui.table;
        table.on('tool(business)', function (obj) {
            var tr = obj.data;
            window.localStorage.setItem("companyName", tr.company);
            window.location.href = "http://localhost/company.html";
        })
    });
}

function addBank() {
    $.ajax({
        url: '/banks',
        type: 'post',
        headers: {
            'wrnm':localStorage.wrnm
        },
        data: {
            "bankName": $("#bankName").val(),
            "computerBalance": $("#computerBalance").val(),
            "infoBalance": $("#infoBalance").val(),
            "num": $("#num").val(),
            "reduceBalance": $("#reduceBalance").val(),
        },
        success:function (res)
        {
            if(res.code!=200)
            {
                alert("你无权访问该资源")
            }
            alert("添加成功");
        },
        error:function (res)
        {
            alert("出现异常，请重试");
            return;
        }
    });
}


