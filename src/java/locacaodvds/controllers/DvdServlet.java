package locacaodvds.controllers;

import java.io.IOException;
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
import locacaodvds.dao.DvdDAO;
import locacaodvds.models.Actor;
import locacaodvds.models.AgeRating;
import locacaodvds.models.Dvd;
import locacaodvds.models.Gender;

/**
 *
 * @author Mateus Santos & João Pedro
 */
@WebServlet(name = "DvdServlet", urlPatterns = {"/dvd"})
public class DvdServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
   
        String action = request.getParameter("action");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DvdDAO dao = null;
        RequestDispatcher disp = null;
 

        try {
            dao = new DvdDAO();
            switch (action) {
                case "insert":
                    {
                        Dvd dvd = new Dvd();
                        Gender gender = new Gender();
                        AgeRating ageRating = new AgeRating();
                        Actor mainActor = new Actor();
                        Actor supportingActor = new Actor();
                        
                        mainActor.setId(Integer.parseInt(request.getParameter("mainActor")));
                        supportingActor.setId(Integer.parseInt(request.getParameter("supportingActor")));
                        ageRating.setId(Integer.parseInt(request.getParameter("ageRating")));
                        gender.setId(Integer.parseInt(request.getParameter("gender")));
                        
                        dvd.setTitle(request.getParameter("title"));
                        dvd.setDuration(Integer.parseInt(request.getParameter("duration")));
                        dvd.setReleaseDate(Date.valueOf(
                                LocalDate.parse(request.getParameter("releaseDate"),dtf)));
                        dvd.setReleaseYear(request.getParameter("releaseYear"));
                        dvd.setMainActor(mainActor);
                        dvd.setSupportingActor(supportingActor);
                        dvd.setGender(gender);
                        dvd.setAgeRating(ageRating);
                        dao.insert(dvd);
                        disp = request.getRequestDispatcher(
                        "/dvd/list.jsp" );
                        break;
                    }
                case "update":
                    {
                        
                        Dvd dvd = dao.getById(Integer.parseInt(request.getParameter("id")));
                        Gender gender = new Gender();
                        AgeRating ageRating = new AgeRating();
                        Actor mainActor = new Actor();
                        Actor supportingActor = new Actor();
                        
                        mainActor.setId(Integer.parseInt(request.getParameter("mainActor")));
                        supportingActor.setId(Integer.parseInt(request.getParameter("supportingActor")));
                        ageRating.setId(Integer.parseInt(request.getParameter("ageRating")));
                        gender.setId(Integer.parseInt(request.getParameter("gender")));
                        
                        dvd.setTitle(request.getParameter("title"));
                        dvd.setDuration(Integer.parseInt(request.getParameter("duration")));
                        dvd.setReleaseDate(Date.valueOf(
                                LocalDate.parse(request.getParameter("releaseDate"),dtf)));
                        dvd.setReleaseYear(request.getParameter("releaseYear"));
                        dvd.setMainActor(mainActor);
                        dvd.setSupportingActor(supportingActor);
                        dvd.setGender(gender);
                        dvd.setAgeRating(ageRating);
                        
                        dao.update(dvd);
                        disp = request.getRequestDispatcher(
                        "/dvd/list.jsp" );
                        break;
                    }
                case "delete":
                    {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Dvd d = new Dvd();
                        d.setId(id);
                        dao.delete(d);
                        
                        disp = request.getRequestDispatcher(
                        "/dvd/list.jsp" );
                        break;
                    }
                default:
                    {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Dvd d = new Dvd();
                        d = dao.getById(id);
                        request.setAttribute("dvd", d);
                        if (action.equals("prepareChange")) {
                            
                            disp = request.getRequestDispatcher("/dvd/edit.jsp" );
                        
                        } else if (action.equals("prepareDelete")) {
                     
                            disp = request.getRequestDispatcher("/dvd/delete.jsp" );
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
        return "DvdServlet";
    }

}
