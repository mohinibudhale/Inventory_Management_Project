

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address Created</title>
        <link rel="stylesheet" type="text/css" href="confirmation.css">
    </head>
    <body style="background-image: url('background.jpg'); background-size: cover; background-repeat: no-repeat; background-attachment: fixed;">
        <div style="color:white">
        <h1>Confirmation of Address creation!</h1>
        <ul>
            <li>${requestScope.address.address}</li>
            <li>${requestScope.address.address2}</li>
            <li>${requestScope.address.district}</li>            
            <li>${requestScope.address.cityId}</li>
            <li>${requestScope.address.postalCode}</li>
            <li>${requestScope.address.phone}</li>
        </ul>
        </div>
    </body>
</html>
