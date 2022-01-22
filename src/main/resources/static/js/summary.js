//汇总银行的js代码实现
layui.use(['table','form','layer'], function(){
    var table = layui.table;
    var $=layui.jquery;
    var form = layui.form;
    var layer=layui.layer
    //展示所有的交易信息数据
    table.render({
        elem: '#banksData'
        ,height: 600
        ,url: '/banks'
        ,method:'get'
        ,where: {

        }
        ,cols: [
            [ //表头
                {field: 'bankName', title: '银行名称', width: 450}
                ,{field: 'computerBalance', title: '总余额', width: 450}
                ,{title: '操作', align:'center', toolbar: '#barDemo'}
            ]
        ]
        ,page: true //开启分页
        , limits: [3, 5, 10]  //一页选择显示3,5或10条数据
        , limit: 10  //一页显示10条数据
        , toolbar: '#toolbar'
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

    table.on('tool(bank)', function (obj) {
        var tr = obj.data;
        layui.use(['table','form','layer'], function() {
            var table = layui.table;
            table.render({
                elem: '#banksDetail'
                , height: 600
                , url: '/infos'
                , method: 'get'
                , where: {
                    "num":tr.num
                }
                , cols: [
                    [ //表头
                        {field: 'id', title: '交易编号', width: 200}
                        , {field: 'bankName', title: '银行名称', width: 200}
                        , {field: 'computerBalance', title: '电脑余额', width: 200}
                        , {field: 'computerBalance', title: '实际余额', width: 200}
                        , {field: 'computerBalance', title: '交易详情', width: 200}
                        , {title: '操作', align: 'center', toolbar: '#barDemo2'}
                    ]
                ]
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
    });



    //管理员删除事件
    table.on('tool(managerFilter)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'del')
        {
            //删除管理员
            var del_index=layer.confirm('确认删除管理员？', {
                btn: ['确认', '取消']
                ,yes: function(index, layero)
                {
                    $.ajax({
                        url:'del_one_manager',
                        type:'post',
                        data:{"m_id": data.id,token:window.localStorage.managerToken},
                        success: function (res)
                        {
                            obj.del();
                            layer.msg("删除成功",{icon:1})
                            layer.close(index);
                        },
                        error: function ()
                        {
                            layer.msg("删除失败",{icon:5})
                        }
                    })
                }
            });
            layer.title("<font color=red>删除管理员</font>",del_index)
        }
    });

    //搜索管理员
    $("#man_search").click(function (){
        //表格重载实现搜索功能
        //上述方法等价于
        table.reload('managerData',
            {
                url: 'get_target_manager',
                method:'post'
                ,where:
                    {
                        m_name : $("#man_name").val(),
                        token: window.localStorage.managerToken
                    }
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            }); //只重载数据
    })
});

function enterBank() {
    layui.use(['table'], function() {
        var table = layui.table;
        table.on('tool(bank)', function (obj) {
            var tr = obj.data;
            window.localStorage.setItem("num", tr.num);
            window.location.href = "http://localhost/bankTail.html";
        })
    });
}

function showDeal() {
    window.location.href = "http://localhost/tradeInfo.html";
}