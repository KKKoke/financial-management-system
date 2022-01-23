layui.use(['table','form','layer'], function() {
    var table = layui.table;
    var $=layui.jquery;
    var form = layui.form;
    var layer=layui.layer;
    //展示所有的交易信息数据
    table.render({
        elem: '#memberData'
        , height: 600
        , url: '/manager'
        , method: 'get'
        , where: {}
        , cols: [
            [ //表头
                {field: 'id', title: '编号', width:250, sort: true, fixed: 'left'}
                ,{field: 'mname', title: '管理员姓名',width: 250}
                ,{field: 'mpwd', title: '管理员密码', width: 250, templet:function(d){
                    return d.mpwd=='*'?'**********':'**********';}}
                ,{field: 'type', title: '权限',width: 250, templet:function(d){
                    return d.type=='0'?'普通管理员':'超级普通管理员';}}
            ]
        ]
        , id: 'tableOne'
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

function searchMember() {
    layui.use(['table'], function() {
        var table = layui.table;
        table.render({
            elem: '#memberData'
            , height: 600
            , url: '/managerByExample'
            , method: 'get'
            , where: {
                "name": $("#name").val()
            }
            , cols: [
                [ //表头
                    {field: 'id', title: '编号', width:250, sort: true, fixed: 'left'}
                    ,{field: 'mname', title: '管理员姓名',width: 250}
                    ,{field: 'mpwd', title: '管理员密码', width: 250, templet:function(d){
                        return d.mpwd=='*'?'**********':'**********';}}
                    ,{field: 'type', title: '权限',width: 250, templet:function(d){
                        return d.type=='0'?'普通管理员':'超级普通管理员';}}
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
// layui.use(['table','form','layer'], function() {
//     var table = layui.table;
//     var form = layui.form;
//     var layer=layui.layer;
//     //展示所有的交易信息数据
//     table.render({
//         elem: '#memberData'
//         , height: 600
//         , url: '/manager'
//         , method: 'get'
//         , where: {
//             "name": $("#name").val()
//         }
//         , cols: [
//             [ //表头
//                 {field: 'id', title: '编号', width:250, sort: true, fixed: 'left'}
//                 ,{field: 'mname', title: '管理员姓名',width: 250}
//                 ,{field: 'mpwd', title: '管理员密码', width: 250, templet:function(d){
//                     return d.mpwd=='*'?'**********':'**********';}}
//                 ,{field: 'type', title: '权限',width: 250, templet:function(d){
//                     return d.type=='0'?'普通管理员':'超级普通管理员';}}
//             ]
//         ]
//         , toolbar: '#toolbar'
//         , page: true //开启分页
//         , limits: [3, 5, 10]  //一页选择显示3,5或10条数据
//         , limit: 10  //一页显示10条数据
//         , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据，res为从url中get到的数据
//             var result;
//             if (this.page.curr) {
//                 result = res.data.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
//             } else {
//                 result = res.data.slice(0, this.limit);
//             }
//             return {
//                 "code": 0, //解析接口状态
//                 "msg": "ok", //解析提示文本
//                 "count": res.data.length, //解析数据长度
//                 "data": result //解析数据列表
//             };
//         }
//     });
// });