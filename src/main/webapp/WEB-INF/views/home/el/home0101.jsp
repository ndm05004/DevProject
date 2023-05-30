<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>str : ${str }</p>
	<p>contains : ${fn:contains(str,'Hello') }</p>
	<p>containsIgnoreCase : ${fn:containsIgnoreCase(str,'Hello') }</p>
	<p>startWith : ${fn:startsWith(str, 'Hello') }</p>
	<p>fn:endsWith : ${fn:endsWith(str, 'World!') }</p>
	<p>fn:indexOf : ${fn:indexOf(str, 'World') } </p>
	<p>fn:length : ${fn:length(str) }</p>
	<p>fn:escapeXml : ${fn:escapeXml(str) }</p>
	<p>fn:replace : ${fn:replace(str, 'Hello', 'Hi') }</p>
	<p>fn:toLowerCase : ${fn:toLowerCase(str) }</p>
	<p>fn:toUpperCase : ${fn:toUpperCase(str) }</p>
	<p>fn:trim  : ${fn:trim(str) }</p>
	<p>fn:substring : ${fn:substring(str, 7, 12) } </p>
	<p>fn:substringAfter  : $fn:substringAfter(str, 'World!' ) </p>
	<p>fn:substringBefore  : ${fn:substringBefore(str, 'World!') } </p>
	<c:set value="${fn:split(str, ' ') }" var="strArray2"/>
	<p>fn:split  : ${strArray2[1]} </p>
	
	<c:set value="${fn:split(str, ' ') }" var="strArray"/>
	<p>fn:join  : ${fn:join(strArray, '-') } </p>
	<p>fn:join  : ${fn:join(fn:split(str,' '), '-') } </p>
	
	
	
	
	
</body>
</html>