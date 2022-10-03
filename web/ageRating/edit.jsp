<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${cp}/src/styles/main.css"/>
        <link rel="stylesheet" href="${cp}/src/styles/global.css"/> 
        <link rel="stylesheet" href="${cp}/src/styles/forms.css"/> 
        <title>Age Rating</title>
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
                
                <form method="post" action="${cp}/agerating">
                    <input name="action" type="hidden" value="update"/>
                    <input name="id" type="hidden" value="${requestScope.agerating.id}"/>
                    
                    <h2>EDIT AGE RATING</h2>
                    <div class="input-group">
                        <label for="description" class="sr-only">Description:</label>
                        <input id="description"
                               name="description"
                               maxlength="45"
                               required
                               size="1"
                               type="text"
                               value="${requestScope.agerating.description}"
                               placeholder="Description" />
                    </div>
                    <div class="input-group">
                        <a  class="back-btn" href="${cp}/agerating/list.jsp">BACK<a/>
                        <input type="submit" value="UPDATE" />
                        
                    </div>      
                    
                </form>
            </div>
        </main>
        <footer>

        </footer>
    </body>
</html>
