/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package locacaodvds.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaodvds.dao.AgeRatingDAO;
import locacaodvds.models.AgeRating;

/**
 *
 * @author User
 */
@WebServlet(name = "AgeRatingServlet", urlPatterns = {"/agerating"})
public class AgeRatingServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        AgeRatingDAO dao = null;
        RequestDispatcher disp = null;

        try {

            dao = new AgeRatingDAO();

            switch (action) {
                case "insert":
                    {
                        String description = request.getParameter("description");
                        AgeRating a = new AgeRating();
                        a.setDescription(description);
                        dao.insert(a);
                        
                        disp = request.getRequestDispatcher(
                        "/agerating/list.jsp" );
                        break;
                    }
                case "update":
                    {
                        int id = Integer.parseInt(request.getParameter("id"));
                        String description = request.getParameter("description");
                        AgeRating a = new AgeRating();
                        a.setId(id);
                        a.setDescription(description);
                        dao.update(a);
                        disp = request.getRequestDispatcher(
                        "/agerating/list.jsp" );
                        break;
                    }
                case "delete":
                    {
                        int id = Integer.parseInt(request.getParameter("id"));
                        AgeRating a = new AgeRating();
                        a.setId(id);
                        dao.delete(a);

                        disp = request.getRequestDispatcher(
                        "/agerating/list.jsp" );
                        break;
                    }
                default:
                    {
                        int id = Integer.parseInt(request.getParameter("id"));
                        AgeRating a = dao.getById(id);
                        request.setAttribute("agerating", a);
                        if (action.equals("prepareChange")) {
                            disp = request.getRequestDispatcher("/agerating/edit.jsp" );
                          
                        } else if (action.equals("prepareDelete")) {
                            disp = request.getRequestDispatcher("/agerating/delete.jsp" );
                        
                        }     
                        
                        
                        break;
                    }
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            if (dao != null) {
                try {
                    dao.closeConnection();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
        }

        if (disp != null) {
            disp.forward(request, response);
        }

    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "AgeRatingServlet";
    }

}
