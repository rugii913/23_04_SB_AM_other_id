<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE WRITE" />
<%@ include file="../common/head.jspf"%>
<hr />



<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doWrite" method="post">
			제목
			<input type="text" name="title" class="rounded w-full" />
			내용
			<textarea name="body" rows="15" class="rounded w-full"></textarea>
			<button type="submit" class="w-24 h-6 border-solid border-gray-400 bg-black text-white text-sm rounded">작성 완료</button>
		</form>
	</div>
</section>



<%@ include file="../common/foot.jspf"%>