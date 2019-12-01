<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-3.3.1.js"></script>
    <script>
        function btn(page) {
            var res = $("#input").val();
            alert(res)
            var page = $("#btn1").val();
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/es/select",
                data: {name: res, page: page},
                dataType: "json",
                success: function (aa) {
                    $("#tb").empty();
                    $.each(aa, function (index, comment) {
                        var tr1 = $("<tr1>").html(comment.name);
                        var tr2 = $("<tr>").html(comment.author);
                        var tr8 = $("<tr>").html(comment.categoryName);
                        var tr3 = $("<button>").html("点我查看详情").click(function test() {
                            $.ajax({
                                type: "get",
                                url: "${pageContext.request.contextPath}/poem/selectOne",
                                data: {name: comment.name.replace(/<.*?>/ig, "")},
                                dataType: "json",
                                success: function (aa) {
                                    $("#" + comment.id).empty();
                                    console.log(aa);

                                    $("#" + comment.id).html(aa.content);
                                }
                            })
                        });
                        var hr = $("<hr>");
                        var tr4 = $("<tr4 id=" + comment.id + ">").html("");
                        var tr9 = $("<tr>").html("===================================================================================");
                        $("#tb").append(tr1);
                        $("#tb").append(tr2);
                        $("#tb").append(tr8);
                        $("#tb").append(tr3);
                        $("#tb").append(hr);
                        $("#tb").append(tr4);
                        $("#tb").append(tr9);

                    })
                }
            })
        }

        function click1() {
            var m;
            var page = $("#btn1").val();
            m = parseInt(page) - 1;
            $("#btn1").attr("value", m);
            btn(m);
        }

        function click2() {
            var m;
            var page = $("#btn1").val();
            m = parseInt(page) + 1;
            alert(m)
            $("#btn1").attr("value", m);
            btn(m);
        }


    </script>
</head>
<body style="text-align: center">

<input type="text" id="input">
<button onclick="btn(event)" id="btn"> 百度一下</button>
<a href="${pageContext.request.contextPath}/add.jsp"> 添加文章</a>

<table id="tb" style="text-align: center">


</table>

<button id="btn1" value="0" onclick="click1()">上一页</button>
<button id="btn2" onclick="click2()">下一页</button>

</body>
</html>