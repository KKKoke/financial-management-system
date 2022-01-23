//交易信息相关的js代码实现
layui.use(['table','form','layer'], function(){
    var table = layui.table;
    //展示所有的交易信息数据
    table.render({
        elem: '#DealData'
        ,height: 600
        ,url: '/deals'
        ,method:'get'
        ,where: {

        }
        ,cols: [
            [ //表头
                {field: 'id', title: '交易编号', width:250, sort: true, fixed: 'left'}
                ,{field: 'remark', title: '注明', width: 250}
                ,{field: 'paytime', title: '到账时间',width: 250}
                ,{field: 'company', title: '业务相关单位',width: 250}
                ,{field: 'handler', title: '汇款单位经办人',width: 250}
                ,{field: 'payee', title: '收款单位',width: 250}
                ,{field: 'receivePeo', title: '收款人',width: 250}
                ,{field: 'item', title: '用项',width: 250}
                ,{field: 'detail', title: '品名',width: 250}
                ,{field: 'payType', title: '收支',width: 250}
                ,{field: 'bankName', title: '银行名称',width: 250}
                ,{field: 'num', title: '卡号',width: 250}
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
                // ,{fixed: 'right', title:'汇款单位经办人', toolbar: '#barOpt', width:200}
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

// function strMapToObj(strMap){
//     let obj= Object.create(null);
//     for (let[k,v] of strMap) {
//         obj[k] = v;
//     }
//     return obj;
// }
// /**
//  *map转换为json
//  */
// function mapToJson(map) {
//     return JSON.stringify(strMapToObj(map));
// }

function searchData() {
    layui.use(['table','form','layer'], function() {
        var table = layui.table;
        // var $ = layui.jquery;
        //展示所有的交易信息数据
        // let pramList = new Map();
        // pramList.set("a", "b");
        // var fd = new FormData(document.querySelector( "form" ));
        // fd.set("a", "b");
        // alert(JSON.stringify(pramList));

        var pram = new Map();
        if ($("#company").val() != null) {
            // fd.append( "company", $("#company").val());
            pram.set("company", $("#company").val());
        }
        alert(mapToJson(pram));
        table.render({
            elem: '#DealData'
            , height: 600
            , url: '/getByExample'
            , method: 'get'
            , dataType: 'application/json;charset=utf-8'
            , where: mapToJson(pram)
                // {
            //     "bankName": $("#bankName").val(),
            //     "bid": $("#bid").val(),
            //     "byPeo": $("#byPeo").val(),
            //     "company": $("#company").val(),
            //     "computerBalance": $("#computerBalance").val(),
            //     "detail": $("#detail").val(),
            //     "expectMoney": $("#expectMoney").val(),
            //     "handler": $("#handler").val(),
            //     "id": $("#id").val(),
            //     "infoBalance": $("#infoBalance").val(),
            //     "infoMark": $("#infoMark").val(),
            //     "item": $("#item").val(),
            //     "leftMoney": $("#leftMoney").val(),
            //     "money": $("#money").val(),
            //     "num": $("#num").val(),
            //     "number": $("#number").val(),
            //     "payee": $("#payee").val(),
            //     "paytime": $("#paytime").val(),
            //     "realMoney": $("#realMoney").val(),
            //     "receivePeo": $("#receivePeo").val(),
            //     "receiver": $("#receiver").val(),
            //     "reduceBalance": $("#reduceBalance").val(),
            //     "remark": $("#remark").val(),
            //     "unit": $("#unit").val(),
            //     "unitPrice": $("#unitPrice").val()
            // }
            , cols: [
                [ //表头
                    {field: 'id', title: '交易编号', width: 250, sort: true, fixed: 'left'}
                    , {field: 'remark', title: '注明', width: 250}
                    , {field: 'paytime', title: '到账时间', width: 250}
                    , {field: 'company', title: '业务相关单位', width: 250}
                    , {field: 'handler', title: '汇款单位经办人', width: 250}
                    , {field: 'payee', title: '收款单位', width: 250}
                    , {field: 'receivePeo', title: '收款人', width: 250}
                    , {field: 'item', title: '用项', width: 250}
                    , {field: 'detail', title: '品名', width: 250}
                    , {field: 'payType', title: '收支', width: 250}
                    , {field: 'bankName', title: '银行名称', width: 250}
                    , {field: 'num', title: '卡号', width: 250}
                    , {field: 'money', title: '金额', width: 250}
                    , {field: 'cash', title: '现金收支', width: 250}
                    , {field: 'computerBalance', title: '电脑余额', width: 250}
                    , {field: 'infoBalance', title: '信息余额', width: 250}
                    , {field: 'reduceBalance', title: '余额差', width: 250}
                    , {field: 'receiver', title: '收货人', width: 250}
                    , {field: 'byPeo', title: '经办人', width: 250}
                    , {field: 'unitPrice', title: '单价', width: 250}
                    , {field: 'number', title: '数量', width: 250}
                    , {field: 'unit', title: '单位', width: 250}
                    , {field: 'expectMoney', title: '应收金额', width: 250}
                    , {field: 'realMoney', title: '实收金额', width: 250}
                    , {field: 'leftMoney', title: '余款', width: 250}
                    , {field: 'infoMark', title: '备注', width: 250}
                    // ,{fixed: 'right', title:'汇款单位经办人', toolbar: '#barOpt', width:200}
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
}
//
// layui.use('table', function () {
//     var table = layui.table;
//     table.render({
//         elem: '#DealData'
//         , url: '/getByExample'
//         , toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
//         , even: true
//         , cols: [
//             [ //表头
//                 {field: 'id', title: '交易编号', width: 250, sort: true, fixed: 'left'}
//                 , {field: 'remark', title: '注明', width: 250}
//                 , {field: 'paytime', title: '到账时间', width: 250}
//                 , {field: 'company', title: '业务相关单位', width: 250}
//                 , {field: 'handler', title: '汇款单位经办人', width: 250}
//                 , {field: 'payee', title: '收款单位', width: 250}
//                 , {field: 'receivePeo', title: '收款人', width: 250}
//                 , {field: 'item', title: '用项', width: 250}
//                 , {field: 'detail', title: '品名', width: 250}
//                 , {field: 'payType', title: '收支', width: 250}
//                 , {field: 'bankName', title: '银行名称', width: 250}
//                 , {field: 'num', title: '卡号', width: 250}
//                 , {field: 'money', title: '金额', width: 250}
//                 , {field: 'cash', title: '现金收支', width: 250}
//                 , {field: 'computerBalance', title: '电脑余额', width: 250}
//                 , {field: 'infoBalance', title: '信息余额', width: 250}
//                 , {field: 'reduceBalance', title: '余额差', width: 250}
//                 , {field: 'receiver', title: '收货人', width: 250}
//                 , {field: 'byPeo', title: '经办人', width: 250}
//                 , {field: 'unitPrice', title: '单价', width: 250}
//                 , {field: 'number', title: '数量', width: 250}
//                 , {field: 'unit', title: '单位', width: 250}
//                 , {field: 'expectMoney', title: '应收金额', width: 250}
//                 , {field: 'realMoney', title: '实收金额', width: 250}
//                 , {field: 'leftMoney', title: '余款', width: 250}
//                 , {field: 'infoMark', title: '备注', width: 250}
//                 // ,{fixed: 'right', title:'汇款单位经办人', toolbar: '#barOpt', width:200}
//             ]
//         ]
//         // , page: true //开启分页
//         // , limits: [3, 5, 10]  //一页选择显示3,5或10条数据
//         // , limit: 10  //一页显示10条数据
//         // , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据，res为从url中get到的数据
//         //     var result;
//         //     if (this.page.curr) {
//         //         result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
//         //     } else {
//         //         result = res.data.slice(0, this.limit);
//         //     }
//         //     return {
//         //         "code": 0, //解析接口状态
//         //         "msg": "ok", //解析提示文本
//         //         "count": res.data.length, //解析数据长度
//         //         "data": result //解析数据列表
//         //     };
//         // }
//     });
//
//     var $ = layui.$, active = {
//         reload: function () {
//             var company = $('#company').val();
//             //执行重载,会进入 table.render 的 url,并且会把 where 中参数传过去
//             table.reload('DealData', {
//                 where: {
//                     company: company
//                 }
//             });
//         }
//     };
//
//     $('#search').on('click', function () {
//         alert("hehe");
//         var type = $(this).data('type');
//         active[type] ? active[type].call(this) : '';
//     });
// });

// function searchTask() {
//     layui.use(['table','form','layer'], function() {
//         var table = layui.table;
//         var $ = layui.jquery;
//         var form = layui.form;
//         var layer = layui.layer
//         //展示所有的交易信息数据
//         table.render({
//             elem: '#DealData'
//             , height: 600
//             , url: '/getByExample'
//             , method: 'get'
//             , where: {
//                 "company":$("#search").val()
//             }
//             , cols: [
//                 [ //表头
//                     {field: 'id', title: '交易编号', width: 250, sort: true, fixed: 'left'}
//                     , {field: 'remark', title: '注明', width: 250}
//                     , {field: 'paytime', title: '到账时间', width: 250}
//                     , {field: 'company', title: '业务相关单位', width: 250}
//                     , {field: 'handler', title: '汇款单位经办人', width: 250}
//                     , {field: 'payee', title: '收款单位', width: 250}
//                     , {field: 'receivePeo', title: '收款人', width: 250}
//                     , {field: 'item', title: '用项', width: 250}
//                     , {field: 'detail', title: '品名', width: 250}
//                     , {field: 'payType', title: '收支', width: 250}
//                     , {field: 'bankName', title: '银行名称', width: 250}
//                     , {field: 'num', title: '卡号', width: 250}
//                     , {field: 'money', title: '金额', width: 250}
//                     , {field: 'cash', title: '现金收支', width: 250}
//                     , {field: 'computerBalance', title: '电脑余额', width: 250}
//                     , {field: 'infoBalance', title: '信息余额', width: 250}
//                     , {field: 'reduceBalance', title: '余额差', width: 250}
//                     , {field: 'receiver', title: '收货人', width: 250}
//                     , {field: 'byPeo', title: '经办人', width: 250}
//                     , {field: 'unitPrice', title: '单价', width: 250}
//                     , {field: 'number', title: '数量', width: 250}
//                     , {field: 'unit', title: '单位', width: 250}
//                     , {field: 'expectMoney', title: '应收金额', width: 250}
//                     , {field: 'realMoney', title: '实收金额', width: 250}
//                     , {field: 'leftMoney', title: '余款', width: 250}
//                     , {field: 'infoMark', title: '备注', width: 250}
//                     // ,{fixed: 'right', title:'汇款单位经办人', toolbar: '#barOpt', width:200}
//                 ]
//             ]
//             , toolbar: '#toolbar'
//             , page: true //开启分页
//             , limits: [3, 5, 10]  //一页选择显示3,5或10条数据
//             , limit: 10  //一页显示10条数据
//             , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据，res为从url中get到的数据
//                 var result;
//                 if (this.page.curr) {
//                     result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
//                 } else {
//                     result = res.data.slice(0, this.limit);
//                 }
//                 return {
//                     "code": 0, //解析接口状态
//                     "msg": "ok", //解析提示文本
//                     "count": res.data.length, //解析数据长度
//                     "data": result //解析数据列表
//                 };
//             }
//         });
//     });
//     // var finishStatus = $("#finishStatus").val();
//     // var company = $("#company").val();
//
//     layui.table.reload('DealData',{
//         where:{
//             company: company
//         }
//     })
//     table.reload('DealData', {
//         url: '/getByExample',
//         method:'get'
//         ,where:
//             {
//                 company : $("#company").val(),
//                 // token: window.localStorage.managerToken
//             }
//         ,page: {
//             curr: 1 //重新从第 1 页开始
//         }
//     }); //只重载数据
// }

function addDeal() {
    $.ajax({
        url: '/deals',
        type: 'post',
        headers: {
            'wrnm':localStorage.wrnm
        },
        data: {
            "bankName": $("#bankName").val(),
            "bid": $("#bid").val(),
            "byPeo": $("#byPeo").val(),
            "company": $("#company").val(),
            "computerBalance": $("#computerBalance").val(),
            "detail": $("#detail").val(),
            "expectMoney": $("#expectMoney").val(),
            "handler": $("#handler").val(),
            "id": $("#id").val(),
            "infoBalance": $("#infoBalance").val(),
            "infoMark": $("#infoMark").val(),
            "item": $("#item").val(),
            "leftMoney": $("#leftMoney").val(),
            "money": $("#money").val(),
            "num": $("#num").val(),
            "number": $("#number").val(),
            "payee": $("#payee").val(),
            "paytime": $("#paytime").val(),
            "realMoney": $("#realMoney").val(),
            "receivePeo": $("#receivePeo").val(),
            "receiver": $("#receiver").val(),
            "reduceBalance": $("#reduceBalance").val(),
            "remark": $("#remark").val(),
            "unit": $("#unit").val(),
            "unitPrice": $("#unitPrice").val()
        },
        success:function (res)
        {
            if(res.code == 200)
            {
                alert("添加成功");
            } else {
                alert("出现异常，请重试");
            }
        },
        error:function (res)
        {
            alert("出现异常，请重试");
        }
    });
}

function delDeal() {
    $.ajax({
        url: '/deals',
        type: 'delete',
        headers: {
            'wrnm': localStorage.wrnm
        },
        data: {
            "id": $("#id").val(),
        },
        success: function (res) {
            if (res.code == 200) {
                alert("删除成功");
            } else {
                alert("出现异常，请重试");
            }
        },
        error: function (res) {
            alert("出现异常，请重试");
        }
    });
}

function searchDeal() {
    layui.use(['table','form','layer'], function() {
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
        var layer = layui.layer
        //展示所有的交易信息数据
        table.render({
            elem: '#searchDealData'
            , height: 600
            , url: '/getByExample'
            , method: 'get'
            , where: {
                "bankName": $("#bankName").val(),
                "bid": $("#bid").val(),
                "byPeo": $("#byPeo").val(),
                "company": $("#company").val(),
                "computerBalance": $("#computerBalance").val(),
                "detail": $("#detail").val(),
                "expectMoney": $("#expectMoney").val(),
                "handler": $("#handler").val(),
                "id": $("#id").val(),
                "infoBalance": $("#infoBalance").val(),
                "infoMark": $("#infoMark").val(),
                "item": $("#item").val(),
                "leftMoney": $("#leftMoney").val(),
                "money": $("#money").val(),
                "num": $("#num").val(),
                "number": $("#number").val(),
                "payee": $("#payee").val(),
                "paytime": $("#paytime").val(),
                "realMoney": $("#realMoney").val(),
                "receivePeo": $("#receivePeo").val(),
                "receiver": $("#receiver").val(),
                "reduceBalance": $("#reduceBalance").val(),
                "remark": $("#remark").val(),
                "unit": $("#unit").val(),
                "unitPrice": $("#unitPrice").val(),
            }
            , cols: [
                [ //表头
                    {field: 'id', title: '交易编号', width: 250, sort: true, fixed: 'left'}
                    , {field: 'remark', title: '注明', width: 250}
                    , {field: 'paytime', title: '到账时间', width: 250}
                    , {field: 'company', title: '业务相关单位', width: 250}
                    , {field: 'handler', title: '汇款单位经办人', width: 250}
                    , {field: 'payee', title: '收款单位', width: 250}
                    , {field: 'receivePeo', title: '收款人', width: 250}
                    , {field: 'item', title: '用项', width: 250}
                    , {field: 'detail', title: '品名', width: 250}
                    , {field: 'payType', title: '收支', width: 250}
                    , {field: 'bankName', title: '银行名称', width: 250}
                    , {field: 'num', title: '卡号', width: 250}
                    , {field: 'money', title: '金额', width: 250}
                    , {field: 'cash', title: '现金收支', width: 250}
                    , {field: 'computerBalance', title: '电脑余额', width: 250}
                    , {field: 'infoBalance', title: '信息余额', width: 250}
                    , {field: 'reduceBalance', title: '余额差', width: 250}
                    , {field: 'receiver', title: '收货人', width: 250}
                    , {field: 'byPeo', title: '经办人', width: 250}
                    , {field: 'unitPrice', title: '单价', width: 250}
                    , {field: 'number', title: '数量', width: 250}
                    , {field: 'unit', title: '单位', width: 250}
                    , {field: 'expectMoney', title: '应收金额', width: 250}
                    , {field: 'realMoney', title: '实收金额', width: 250}
                    , {field: 'leftMoney', title: '余款', width: 250}
                    , {field: 'infoMark', title: '备注', width: 250}
                    // ,{fixed: 'right', title:'汇款单位经办人', toolbar: '#barOpt', width:200}
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
    // $.ajax({
    //     url: '/getByExample',
    //     type: 'get',
    //     headers: {
    //         'wrnm':localStorage.wrnm
    //     },
    //     data: {
    //         "bankName": $("#bankName").val(),
    //         "bid": $("#bid").val(),
    //         "byPeo": $("#byPeo").val(),
    //         "company": $("#company").val(),
    //         "computerBalance": $("#computerBalance").val(),
    //         "detail": $("#detail").val(),
    //         "expectMoney": $("#expectMoney").val(),
    //         "handler": $("#handler").val(),
    //         "id": $("#id").val(),
    //         "infoBalance": $("#infoBalance").val(),
    //         "infoMark": $("#infoMark").val(),
    //         "item": $("#item").val(),
    //         "leftMoney": $("#leftMoney").val(),
    //         "money": $("#money").val(),
    //         "num": $("#num").val(),
    //         "number": $("#number").val(),
    //         "payee": $("#payee").val(),
    //         "paytime": $("#paytime").val(),
    //         "realMoney": $("#realMoney").val(),
    //         "receivePeo": $("#receivePeo").val(),
    //         "receiver": $("#receiver").val(),
    //         "reduceBalance": $("#reduceBalance").val(),
    //         "remark": $("#remark").val(),
    //         "unit": $("#unit").val(),
    //         "unitPrice": $("#unitPrice").val()
    //     },
    //     success:function (res)
    //     {
    //         if(res.code!=200)
    //         {
    //             alert("你无权访问该资源")
    //         }
    //         alert("添加成功");
    //     },
    //     error:function (res)
    //     {
    //         alert("出现异常，请重试");
    //         return;
    //     }
    // });
}