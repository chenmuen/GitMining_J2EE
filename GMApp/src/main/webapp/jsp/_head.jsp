<head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;
%>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>${param.title}</title>
<link rel="shortcut icon" href="assets/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="assets/css/app.css">
</head>