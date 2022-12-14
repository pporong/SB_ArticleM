<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE LIST" />
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<div class="table-box-type-1">
				<table class="list_table" align="center">

					<!-- 리스트 타이틀 -->
					<thead class="title">
						<tr>
							<th>번 호</th>
							<th>제 목</th>
							<th>작성자</th>
							<th>날 짜</th>
						</tr>
					</thead>

					<!-- 리스트 바디 -->
					<tbody class="body">
						<c:forEach var="article" items="${articles }">
							<tr>
								<td>${article.id}</td>
								<td><a class="hover:under-line" href="../article/detail?id=${article.id}"> ${article.title}</a></td>
								<td>${article.memberId}</td>
								<td>${article.regDate.substring(2, 16)}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="btn-box">

			</div>

		</div>
</section>

<%@ include file="../common/foot.jspf"%>