<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Reports App</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>

<body>
	<div class="container">
		<h3 class="pb-3 pt-3">Report Application</h3>

		<!-- MAIN FORM -->
		<form:form action="search" modelAttribute="search" method="post">

			<table class="table">
				<tr>
					<td>Plan Name:</td>
					<td><form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${names}" />
						</form:select></td>

					<td>Plan Status:</td>
					<td><form:select path="planstatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${status}" />
						</form:select></td>

					<td>Gender:</td>
					<td><form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Female">Female</form:option>
						</form:select></td>
				</tr>

				<tr>
					<td>Start Date:</td>
					<td><form:input path="startDate" type="date" /></td>

					<td>End Date:</td>
					<td><form:input path="endDate" type="date" /></td>
				</tr>
			</table>

			<a href="/" class="btn btn-secondary ms-2">Reset</a>

			<input type="submit" value="Search" class="btn btn-primary" />
			<a href="excel" class="btn btn-success ms-2">Export Excel</a>
			<a href="pdf" class="btn btn-danger ms-2">Export PDF</a>

		</form:form>

		<!-- RESULTS TABLE -->
		<table class="table table-striped mt-4">
			<thead class="table-dark">
				<tr>
					<th>Sr.No</th>
					<th>Holder Name</th>
					<th>Gender</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Benefit Amt</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="p" items="${plans}" varStatus="s">

					<tr>
						<td>${s.count}</td>
						<td>${p.citizenName}</td>
						<td>${p.gender}</td>
						<td>${p.planName}</td>
						<td>${p.planStatus}</td>
						<td>${p.planStartDate}</td>
						<td>${p.planEndDate}</td>
						<td>${p.benefitAmt}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty p}">
					<tr>
						<td colspan="8" style="color: red; text-align: center;">No
							Record Found</td>
					</tr>
				</c:if>

			</tbody>
		</table>

	</div>
</body>
</html>
