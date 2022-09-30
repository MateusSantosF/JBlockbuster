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
@WebServlet(name = "AgeRatingServlet", urlPatterns = {"/AgeRatingServlet"})
public class AgeRatingServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        AgeRatingDAO dao = null;
        RequestDispatcher disp = null;

        try {

            dao = new AgeRatingDAO();

            if (action.equals("insert")) {

                String description = request.getParameter("description");

                AgeRating a = new AgeRating();

                a.setDescription(description);

                dao.insert(a);

                // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
            } else if (action.equals("change")) {

                int id = Integer.parseInt(request.getParameter("id"));
                String description = request.getParameter("description");

                AgeRating a = new AgeRating();

                a.setId(id);
                a.setDescription(description);

                dao.update(a);

                // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
            } else if (action.equals("delete")) {

                int id = Integer.parseInt(request.getParameter("id"));

                AgeRating a = new AgeRating();
                a.setId(id);

                dao.delete(a);

                // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
            } else {

                int id = Integer.parseInt(request.getParameter("id"));
                AgeRating a = dao.getById(id);
                request.setAttribute("ageRating", a);

                if (action.equals("prepareChange")) {

                    // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
                } else if (action.equals("prepareDelete")) {

                    // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
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
