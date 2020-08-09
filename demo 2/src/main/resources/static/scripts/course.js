layui.use('table', function(){
    var table = layui.table;

    //第一个实例
    table.render({
        elem: '#demo'
        ,height: 500
        ,url: 'courseList' //数据接口
        ,page: true
        ,cols: [[ //表头
            {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
            ,{field: 'title', title: '课程主题', width:300}
            ,{field: 'subTitle', title: '课程子主题', width:300}
            ,{field: 'type', title: '类型', width:100}
            ,{field: 'price', title: '价格', width: 100, sort: true}
        ]]
        ,request: {
            pageName: 'page' //页码的参数名称，默认：page
            ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
        ,method: 'post'
        ,response: {
            statusCode: 1 //重新规定成功的状态码为 200，table 组件默认为 0
        }
        ,parseData: function(res){ //res 即为原始返回的数据
            console.log(res);
            console.log(res.data.pageCount);
            console.log(res.data.dataList);
            return {
                "code": res.code, //解析接口状态
                "msg": res.msg, //解析提示文本
                "count": res.data.pageCount*res.data.pageSize, //解析数据长度
                "data": res.data.dataList //解析数据列表
            };
        }
    });

});

$(function(){
    $("#addBtn").click(function(){
        var index = layer.open({
            type: 2,
            area: ['320px', '195px'],
            maxmin: true,
            content: 'addCoursePage1'
        });
        layer.full(index);
    });
});

