<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefix" value="agerating?action="/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${cp}/src/styles/main.css"/>
        <link rel="stylesheet" href="${cp}/src/styles/global.css"/>  
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
                    <li>DvD's</li>
                    <li>Actors</li>
                    <li><a href="${cp}/gender/list.jsp">Genders</a></li>
                    <li><a href="${cp}/agerating/list.jsp">Age Rating</a></li>
                </ul>
            </nav>
        </header>

        <main>
            <div class="container">
                <section class="main-header">
                    <h2>Genders</h2>
                    <a class="btn" href="${cp}/agerating/create.jsp">Create New</a>
                </section>

                <section>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Description</th>
                                <th style="text-align:center;">Actions</th>                             
                            </tr>
                        </thead>
                        <tbody>
                             <jsp:useBean
                                    id="services"
                                    scope="page"
                                    class="locacaodvds.services.AgeRatingServices"/>
                             
                             <c:forEach items="${services.all}" var="agerating">
                                 <tr>
                                    <td>${agerating.id}</td>
                                    <td>${agerating.description}</td>
                                    <td>
                                        <div class="buttons-contianer">
                                            <a class="btn" href="${cp}/${prefix}prepareChange&id=${agerating.id}">
                                                <img src="${cp}/src/images/edit.svg">
                                            </a>
                                            <a class="btn"
                                               href="${cp}/${prefix}prepareDelete&id=${agerating.id}">
                                                <img src="${cp}/src/images/delete.svg">
                                            </a>
                                        </div>
                                       
                                    </td>
                                </tr>
                             </c:forEach>
                            
                        </tbody>
                    </table>
                </section>
            </div>
        </main>
    </body>
</html>
