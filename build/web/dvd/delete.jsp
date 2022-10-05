<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${cp}/src/styles/main.css"/>
        <link rel="stylesheet" href="${cp}/src/styles/global.css"/> 
        <link rel="stylesheet" href="${cp}/src/styles/dvdForm.css"/> 
        <title>DvDs</title>
    </head>

    <body>

        <header>
            <div id="logo-container">          
                <div id="logo">
                    <h4>BlockB</h4>
                </div>
            </div>
            <nav>
                <ul>
                    <li><a href="${cp}/dvd/list.jsp">DvD's</a></li>
                    <li><a href="${cp}/actor/list.jsp">Actors</a></li>
                    <li><a href="${cp}/gender/list.jsp">Genders</a></li>
                    <li><a href="${cp}/agerating/list.jsp">Age Rating</a></li>
                </ul>
            </nav>
        </header>

        <main>
            <div class="container form">
               
                <form method="post" action="${cp}/dvd">
                    <input name="action" type="hidden" value="delete"/> 
                    <input name="id" type="hidden" value="${requestScope.dvd.id}"/>
                    
                    <div class="model-info-container">
                         <h2>Really want to delete?</h2>           
                        <ul>
                            <li><span>Id: </span>${requestScope.dvd.id}</li>
                            <li><span>Title: </span>${requestScope.dvd.title}</li>
                            <li><span>Release Year: </span>${requestScope.dvd.releaseYear}</li>
                            <li><span>Release Date: </span>${requestScope.dvd.releaseDate}</li>
                            <li><span>Main Actor: </span>${requestScope.dvd.mainActor.name}</li>
                            <li><span>Supporting Actor: </span>${requestScope.dvd.supportingActor.name}</li>
                            <li><span>Duration: </span>${requestScope.dvd.duration}</li>
                            <li><span>Gender: </span>${requestScope.dvd.gender.description}</li>
                            <li><span>Age Rating: </span>${requestScope.dvd.ageRating.description}</li>
                        </ul>
                    </div>
                    <div class="input-group">
                         <a class="back-btn" href="${cp}/dvd/list.jsp">BACK<a/>
                        <input type="submit" class="btn-delete" value="DELETE" />
                    </div>      
                </form>
            </div>
        </main>
        <footer>

        </footer>
    </body>
</html>
