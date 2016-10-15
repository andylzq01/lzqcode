<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="/resources/js/plugin/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/resources/js/plugin/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/taotao.css" />
<script type="text/javascript" src="/resources/js/plugin/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/plugin/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/plugin/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/resources/js/comoms/common.js"></script>

<link href="/resources/js/plugin/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/resources/js/plugin/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/resources/js/plugin/kindeditor-4.1.10/lang/zh_CN.js"></script>