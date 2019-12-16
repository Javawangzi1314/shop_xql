<%--
  Created by IntelliJ IDEA.
  User: 许清磊
  Date: 2019/12/11
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
<script src="${path}/js/jquery.js"></script>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="china" style="width: 600px;height: 600px;margin-top: 30px;margin-left: 30px">

</div>

<script type="text/javascript">
    $(function () {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('china'));
    $.ajax({
        url:'${path}/User/querychainTongji',
        type:'post',
        datatype:'json',
        success:function (result) {
        console.log(result);
        var arr = new Array();
        var i=0;
        arr[0]=result;
        for(var key1 in result){
            console.log("属性：" + key1 + ",值：" + result[key1]);
            arr[i]={name: key1, value: result[key1]};
            i=i+1;
        }
            console.log(arr);
            var option = {
                title: {
                    text: '皮皮磊商城全球注册动态统计',
                    subtext: '2019年12月11日 最新数据',
                    left: 'center'
                },
                tooltip: {},
                // 说明
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['男', '女']
                },
                visualMap: {
                    min: 0,
                    max: 30,
                    left: 'left',
                    top: 'bottom',
                    text: ['高', '低'],           // 文本，默认为数值文本
                    calculable: true
                },
                // 工具箱
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        dataView: {readOnly: false},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                series: [
                    {
                        name: '男',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: true
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: arr
                    },
                    {
                        name: '女',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: true
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: arr
                    }
                ]
            };
            myChart.setOption(option);
        }
    });

    var goEasy = new GoEasy({
        host:'hangzhou.goeasy.io',
        appkey: "BC-78e854d33820422194e9e5700eadc9ae"
    });
    goEasy.subscribe({
        channel: "shop_xql", //替换为您自己的channel
        onMessage: function (message) {
            $.ajax({
                url:'${path}/User/querychainTongji',
                type:'post',
                datatype:'json',
                success:function (result) {
                    console.log(result);
                    var arr = new Array();
                    var i=0;
                    arr[0]=result;
                    for(var key1 in result){
                        console.log("属性：" + key1 + ",值：" + result[key1]);
                        arr[i]={name: key1, value: result[key1]};
                        i=i+1;
                    }
                    console.log(arr);
                    var option = {
                        title: {
                            text: '持明法洲用户全球注册统计',
                            subtext: '2019年12月11日 最新数据',
                            left: 'center'
                        },
                        tooltip: {},
                        // 说明
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data: ['男', '女']
                        },
                        visualMap: {
                            min: 0,
                            max: 30,
                            left: 'left',
                            top: 'bottom',
                            text: ['高', '低'],           // 文本，默认为数值文本
                            calculable: true
                        },
                        // 工具箱
                        toolbox: {
                            show: true,
                            orient: 'vertical',
                            left: 'right',
                            top: 'center',
                            feature: {
                                dataView: {readOnly: false},
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        series: [
                            {
                                name: '男',
                                type: 'map',
                                mapType: 'china',
                                roam: false,
                                label: {
                                    normal: {
                                        show: true
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data: arr
                            },
                            {
                                name: '女',
                                type: 'map',
                                mapType: 'china',
                                label: {
                                    normal: {
                                        show: true
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data: arr
                            }
                        ]
                    };
                    myChart.setOption(option);
                }
            });
        }
    })

    })
</script>













