package locacaodvds.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaodvds.dao.GenderDAO;
import locacaodvds.models.Gender;

/**
 *
 * @author Mateus Santos & João Pedro
 */
@WebServlet(name = "GenderServlet", urlPatterns = {"/gender"})
public class GenderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        GenderDAO dao = null;
        RequestDispatcher disp = null;

        try {

            dao = new GenderDAO();

            if (action.equals("insert")) {

                String description = request.getParameter("description");

                Gender g = new Gender();

                g.setDescription(description);

                dao.insert(g);

                // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
            } else if (action.equals("change")) {

                int id = Integer.parseInt(request.getParameter("id"));
                String description = request.getParameter("description");

                Gender g = new Gender();

                g.setId(id);
                g.setDescription(description);

                dao.update(g);

                // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
            } else if (action.equals("delete")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Gender g = new Gender();
                g.setId(id);

                dao.delete(g);

                // FALTA ADICIONAR UM REQUEST DISPATCHER AINDA VOU COLOCAR DEPOIS QUE A JSP ESTIVER FEITA :D
            } else {

                int id = Integer.parseInt(request.getParameter("id"));
                Gender g = dao.getById(id);
                request.setAttribute("gender", g);

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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "GenderServlet";
    }

}
