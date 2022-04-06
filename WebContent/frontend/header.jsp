<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div align="center">
	<div>
		<input type="text" name="keyword" size="50" />
		<input type="button" value="Search" />
		&nbsp&nbsp&nbsp&nbsp
		<b>
			<a href="#">sign In</a> |
			<a href="#">Register</a> |
			<a href="#">Cart</a>
		</b>
	</div>
	<br>
	<div>
	<b>
		Categories: &nbsp
		<c:forEach var="category" items="${categories}" varStatus="status">
			<a href="view_category?categoryId=${category.categoryId}"><c:out value="${category.name}" /></a>
			<c:if test="${!status.last}" >
				|
			</c:if>
		</c:forEach>
	</b>
	</div>
</div>