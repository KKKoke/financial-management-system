layui.use(['table','form','layer'], function() {
    var table = layui.table;
    table.render({
        elem: '#banksDetail'
        , height: 600
        , url: '/infos'
        , method: 'get'
        , where: {
            "num":window.localStorage.getItem("num")
        }
        , cols: [
            [ //表头
                {field: 'id', title: '交易编号', width: 200}
                , {field: 'bankName', title: '银行名称', width: 200}
                , {field: 'num', title: '卡号', width: 200}
                , {field: 'computerBalance', title: '电脑余额', width: 200}
                , {field: 'computerBalance', title: '实际余额', width: 200}
                , {title: '操作', align: 'center', toolbar: '#barDemo2'}
            ]
        ]
        , toolbar: '#toolbar'
        , page: true //开启分页
        , limits: [3, 5, 10]  //一页选择显示3,5或10条数据
        , limit: 10  //一页显示10条数据
        , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据，res为从url中get到的数据
            if (res.data.length == 1) {
                return {
                    "code": 0, //解析接口状态
                    "msg": "ok", //解析提示文本
                    "count": 1, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
            else {
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
        }
    });
    table.on('toolbar(bank)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'back':
                window.location.href = "http://localhost/summaryBanks.html";
                break;
        };
    });
});