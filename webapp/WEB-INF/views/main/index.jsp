<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp"/>
		</div>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="https://scontent.xx.fbcdn.net/hphotos-xap1/v/t1.0-9/77115_453842391359449_1437082635_n.jpg?oh=a2cb1e88d37dde718a4695da9a43d00e&oe=55D2C767" width=360 height=300>
					<h2>안녕하세요.<br>  박슬아의  mysite에 오신 것을 환영합니다. </h2>
					<p>
						어서오셔용*_*<br>
						메뉴는  사이트 소개, 방명록, 게시판입니당<br>
						프로그래밍의 세계는 어려웡ㅠㅠ<br>
						내일은 주말 야호!<br><br>
						<a href="/mysite3/gb">방명록</a>에 글 남겨죠용<br>
					</p>
				</div>
			</div>
		</div>
		<div id="navigation">
			<c:import url="/WEB-INF/views/include/navigation.jsp">
			 	<c:param name="type" value="main"/>
			 </c:import>
		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/include/footer.jsp"/>
		</div>
	</div>
</body>
</html>