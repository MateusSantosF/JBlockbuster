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
        <title>Dvd</title>
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
            <div class="container form dvd">

                <form method="post" action="${cp}/dvd">
                    <input name="action" type="hidden" value="insert"/>

                    <h2>CREATE NEW DVD</h2>
                    <div class="input-group">
                        <label for="title" class="sr-only">Title:</label>
                        <input id="title"
                               name="title"
                               maxlength="45"
                               required
                               size="30"
                               type="text"
                               placeholder="Title" />

                        <label for="releaseYear" class="sr-only">Release Year:</label>
                        <input id="releaseYear"
                               name="releaseYear"
                               maxlength="45"
                               required
                               size="30"
                               type="text"
                               placeholder="Release Year" />
                    </div>

                    <div class="input-group">
                        <label for="releaseDate" class="sr-only">Release Date:</label>
                        <input id="releaseDate"
                               name="releaseDate"
                               maxlength="45"
                               required
                               size="8"
                               type="text"
                               onfocus="(this.type = 'date')"
                               onblur="(this.type = 'text')"
                               placeholder="Release Date" />
                        <label for="duration" class="sr-only">Duration:</label>
                        <input id="duration"
                               name="duration"
                               maxlength="45"
                               required
                               size="30"
                               type="number" 
                               placeholder="Duration" />
                    </div>

                    <div class="input-group">

                        <jsp:useBean 
                            id="services" 
                            scope="page" 
                            class="locacaodvds.services.ActorServices"/>

                        <select name="mainActor" required>
                            <option hidden value="">Main Actor</option>
                            <c:forEach items="${services.all}" var="mainActor">
                                <option value="${mainActor.id}">
                                    ${mainActor.name} ${mainActor.surname}
                                </option>
                            </c:forEach>
                        </select>
                        <jsp:useBean 
                            id="services1" 
                            scope="page" 
                            class="locacaodvds.services.ActorServices"/>

                        <select name="supportingActor" required>
                            <option hidden value="">Supporting Actor</option>
                            <c:forEach items="${services1.all}" var="supportingActor">
                                <option value="${supportingActor.id}">
                                    ${supportingActor.name} ${supportingActor.surname}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="input-group">

                        <jsp:useBean 
                            id="services2" 
                            scope="page" 
                            class="locacaodvds.services.GenderServices"/>

                        <select name="gender" required>
                            <c:forEach items="${services2.all}" var="gender">
                                <option value="${gender.id}">
                                    ${gender.description}
                                </option>
                            </c:forEach>
                        </select>
                        
                        <jsp:useBean  
                            id="services3" 
                            scope="page" 
                            class="locacaodvds.services.AgeRatingServices"/>

                        <select name="ageRating" required>
                            <c:forEach items="${services3.all}" var="ageRating">
                                <option value="${ageRating.id}">
                                    ${ageRating.description}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="input-group">
                        <a  class="back-btn" href="${cp}/dvd/list.jsp">BACK<a/>
                            <input type="submit" value="CREATE" />
                    </div>     

                </form>
            </div>
        </main>
        <footer>

        </footer>
    </body>
</html>
