

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address Page</title>
        <link rel="stylesheet" type="text/css" href="addressPage.css">
    </head>
    <body>
        <h1>Create an Address</h1>
        <c:if test="${not empty requestScope.violations}">
            <div class="error-container">
                <p> Submission Issue: Please correct the following errors:</p>
                <ul>
                    <c:forEach items="${requestScope.violations}" var="violation">
                        <li>There is a problem with ${violation.propertyPath}. Error: ${violation.message}</li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>  
        <form method="post" action="/mbudhale-fp/address">
            <div>
                <label for="address">Address:  </label>
                <input type="text" id="address"  name="address" value="${requestScope.address.address}">
            </div>
            <div>
                <label for="address2">Address2: </label>
                <input type="text" id="address2"  name="address2" value="${requestScope.address.address2}">
            </div>
            <div>
                <label for="district">District: </label>
                <input type="text" id="district"  name="district" value="${requestScope.address.district}">
            </div>
            <div>
                <label for="cityId">City Id </label>
                <input type="text" id="cityId"  name="cityId" value="${requestScope.address.cityId}">
            </div>
            <div>
                <label for="postalCode">Postal Code: </label>
                <input type="text" id="postalCode"  name="postalCode" value="${requestScope.address.postalCode}">
            </div>
            <div>
                <label for="phone">Phone:  </label>
                <input type="text" id="phone"  name="phone" value="${requestScope.address.phone}">
            </div>
            <div>
                <button type="submit"> Create Address</button>
            </div>  
            <div id="feedback">
                <label id="feedback" for="feedback">Lab 3 Feedback: </label>
                <textarea id="feedback" name="feedback" rows="4" cols="50"></textarea>
                <button id="submitFeedback" type="submit">Submit Feedback</button>
            </div>
        </form>   

    </body>
</html>
