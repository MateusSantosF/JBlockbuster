/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package locacaodvds.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaodvds.dao.ActorDAO;
import locacaodvds.models.Actor;

/**
 *
 * @author User
 */
@WebServlet(name = "ActorServlet", urlPatterns = {"/actor"})
public class ActorServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        ActorDAO dao = null;
        RequestDispatcher disp = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {

            dao = new ActorDAO();

            if (action.equals("insert")) {

                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String premiereDate = request.getParameter("premiereDate");

                Actor a = new Actor();
                a.setName(name);
                a.setSurname(surname);
                a.setPremiereDate(Date.valueOf(
                        LocalDate.parse(premiereDate, dtf)));

                dao.insert(a);

                // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
            } else if (action.equals("change")) {

                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String premiereDate = request.getParameter("premiereDate");

                Actor a = new Actor();
                a.setId(id);
                a.setName(name);
                a.setSurname(surname);
                a.setPremiereDate(Date.valueOf(
                        LocalDate.parse(premiereDate, dtf)));

                dao.update(a);

                // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
            } else if (action.equals("delete")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Actor a = new Actor();
                a.setId(id);

                dao.delete(a);

                // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
            } else {

                int id = Integer.parseInt(request.getParameter("id"));
                Actor a = dao.getById(id);
                request.setAttribute("actor", a);

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
        return "ActorServlet";
    }

}
