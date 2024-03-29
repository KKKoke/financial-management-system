layui.use(['table','form','layer'], function() {
    var table = layui.table;
    table.render({
        elem: '#banksDetail'
        , height: 600
        , url: '/getBanksUnderNowBankName'
        , even: true
        , method: 'get'
        , where: {
            "bankName":window.localStorage.getItem("bankName")
        }
        , cols: [
            [ //表头
                {field: 'id', title: '银行卡ID', width: 200}
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
                window.location.href = "http://localhost:8090/summaryBanks.html";
                break;
        };
    });
});

layui.use(['table','form','layer'], function() {
    var table = layui.table;
    table.render({
        elem: '#cardDetail'
        , height: 600
        , url: '/infos'
        , method: 'get'
        , where: {
            "id":window.localStorage.getItem("id")
        }
        , cols: [
            [ //表头
                {field: 'bid', title: '交易编号', width:250, sort: true, fixed: 'left'}
                ,{field: 'bankName', title: '银行名称',width: 250}
                ,{field: 'num', title: '卡号',width: 250}
                ,{field: 'remark', title: '注明', width: 250}
                ,{field: 'paytime', title: '到账时间',width: 250, templet:function(d){
                    return layui.util.toDateString(d.paytime,'yyyy-MM-dd HH:mm:ss');
                }}
                ,{field: 'company', title: '业务相关单位',width: 250}
                ,{field: 'handler', title: '汇款单位经办人',width: 250}
                ,{field: 'payee', title: '收款单位',width: 250}
                ,{field: 'receivePeo', title: '收款人',width: 250}
                ,{field: 'item', title: '用项',width: 250}
                ,{field: 'detail', title: '品名',width: 250}
                ,{field: 'payType', title: '收支',width: 250, templet:function(d){
                    return d.payType==1?'收入':'支出';}}
                ,{field: 'money', title: '金额',width: 250}
                ,{field: 'cash', title: '现金收支',width: 250}
                ,{field: 'computerBalance', title: '电脑余额',width: 250}
                ,{field: 'infoBalance', title: '信息余额',width: 250}
                ,{field: 'reduceBalance', title: '余额差',width: 250}
                ,{field: 'receiver', title: '收货人',width: 250}
                ,{field: 'byPeo', title: '经办人',width: 250}
                ,{field: 'unitPrice', title: '单价',width: 250}
                ,{field: 'number', title: '数量',width: 250}
                ,{field: 'unit', title: '单位',width: 250}
                ,{field: 'expectMoney', title: '应收金额',width: 250}
                ,{field: 'realMoney', title: '实收金额',width: 250}
                ,{field: 'leftMoney', title: '余款',width: 250}
                ,{field: 'infoMark', title: '备注',width: 250}
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
    table.on('toolbar(card)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'back':
                window.location.href = "http://localhost/bankTail.html";
                break;
        };
    });
});