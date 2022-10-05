package locacaodvds.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaodvds.models.Actor;
import locacaodvds.models.AgeRating;
import locacaodvds.models.Dvd;
import locacaodvds.models.Gender;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class DvdDAO extends DAO<Dvd> {

    public DvdDAO() throws SQLException {

    }

    @Override
    public void insert(Dvd model) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO "
                + "dvd("
                + "    title, "
                + "    releaseYear, "
                + "    releaseDate, "
                + "    mainActorFK, "
                + "    supportingActorFK, "
                + "    duration, "
                + "    genderFK, "
                + "    ageRatingFK) "
                + "VALUES( ?, ?, ?, ?, ?, ?, ?, ? );");
        stmt.setString(1, model.getTitle());
        stmt.setString(2, model.getReleaseYear());
        stmt.setDate(3, model.getReleaseDate());
        stmt.setInt(4, model.getMainActor().getId());
        stmt.setInt(5, model.getSupportingActor().getId());
        stmt.setLong(6, model.getDuration());
        stmt.setInt(7, model.getGender().getId());
        stmt.setInt(8, model.getAgeRating().getId());

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void update(Dvd model) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE dvd "
                + "SET"
                + "    title = ?, "
                + "    releaseYear =?, "
                + "    releaseDate = ?, "
                + "    mainActorFK = ?, "
                + "    supportingActorFK = ?, "
                + "    duration = ?, "
                + "    genderFK = ?, "
                + "    ageRatingFK = ? "
                + "WHERE id = ?;");

        stmt.setString(1, model.getTitle());
        stmt.setString(2, model.getReleaseYear());
        stmt.setDate(3, model.getReleaseDate());
        stmt.setInt(4, model.getMainActor().getId());
        stmt.setInt(5, model.getSupportingActor().getId());
        stmt.setLong(6, model.getDuration());
        stmt.setInt(7, model.getGender().getId());
        stmt.setInt(8, model.getAgeRating().getId());
        stmt.setInt(9, model.getId());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void delete(Dvd model) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM dvd "
                + "WHERE id = ?;");

        stmt.setInt(1, model.getId());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Dvd> listAll() throws SQLException {

        List<Dvd> dvds = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT "+
                  "  d.id , "+ 
                  "  d.title,  "+
                  "  d.releaseYear,  "+ 
                   " d.releaseDate,  "+
                 "   d.duration, "+
                   " d.mainActorFK,  "+
                  "  d.supportingActorFK,  "+
                  "  d.genderFK,  "+
                   " d.ageRatingFK, "+
                 "   g.id as genderId, "+
                  "  g.description as genderDescription, "+
                  "  ag.id as ageRatingId, "+
                 "   ag.description as ageRatingDescription, "+
                 "   a.id, "+
                 "   a.name, "+
                 "   a.surname, "+
                  "  a.premiereDate, "+
                 "   sa.id, "+
                 "   sa.name, "+
                 "   sa.surname, "+
                 "   sa.premiereDate "+
                "FROM "+
                 "   dvd d, "+
                "    actor a, "+
                "    actor sa, "+
                 "   gender g, "+
                 "   ageRating ag "+
             "  WHERE "+
                   " d.mainActorFK = a.id AND  "+
                  "  d.supportingActorFK = sa.id AND  "+
                   " d.genderFK= g.id AND "+
                   " d.ageRatingFK = ag.id; ");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Dvd dvd = new Dvd();
            Actor mainActor = new Actor();
            Actor supportingActor = new Actor();
            Gender gender = new Gender();
            AgeRating ageRating = new AgeRating();

       
            mainActor.setId(rs.getInt("a.id"));
            mainActor.setName(rs.getString("a.name"));
            mainActor.setSurname(rs.getString("a.surname"));
            mainActor.setPremiereDate(rs.getDate("a.premiereDate"));
            
            supportingActor.setId(rs.getInt("sa.id"));
            supportingActor.setName(rs.getString("sa.name"));
            supportingActor.setSurname(rs.getString("sa.surname"));
            supportingActor.setPremiereDate(rs.getDate("sa.premiereDate"));

            gender.setId(rs.getInt("genderId"));
            gender.setDescription(rs.getString("genderDescription"));

            ageRating.setId(rs.getInt("ageRatingId"));
            ageRating.setDescription(rs.getString("ageRatingDescription"));
            
            dvd.setId(rs.getInt("d.id"));
            dvd.setTitle(rs.getString("d.title"));
            dvd.setReleaseYear(rs.getString("d.releaseYear"));
            dvd.setReleaseDate(rs.getDate("d.releaseDate"));
            dvd.setDuration(rs.getLong("d.duration"));
 
            dvd.setGender(gender);
            dvd.setAgeRating(ageRating);
            dvd.setMainActor(mainActor);
            dvd.setSupportingActor(supportingActor);

            dvds.add(dvd);
        }
        
        rs.close();
        stmt.close();

        return dvds;
    }

    @Override
    public Dvd getById(int id) throws SQLException {

        Dvd dvd = null;

         PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT "+
                  "  d.id , "+ 
                  "  d.title,  "+
                  "  d.releaseYear,  "+ 
                   " d.releaseDate,  "+
                 "   d.duration, "+
                   " d.mainActorFK,  "+
                  "  d.supportingActorFK,  "+
                  "  d.genderFK,  "+
                   " d.ageRatingFK, "+
                 "   g.id as genderId, "+
                  "  g.description as genderDescription, "+
                  "  ag.id as ageRatingId, "+
                 "   ag.description as ageRatingDescription, "+
                 "   a.id, "+
                 "   a.name, "+
                 "   a.surname, "+
                  "  a.premiereDate, "+
                 "   sa.id, "+
                 "   sa.name, "+
                 "   sa.surname, "+
                 "   sa.premiereDate "+
                "FROM "+
                 "   dvd d, "+
                "    actor a, "+
                "    actor sa, "+
                 "   gender g, "+
                 "   ageRating ag "+
             "  WHERE "+
                   " d.mainActorFK = a.id AND  "+
                   "  d.supportingActorFK = sa.id AND  "+
                   " d.genderFK= g.id AND "+
                   " d.id = ? AND "+
                   " d.ageRatingFK = ag.id; ");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            dvd = new Dvd();
            Actor mainActor = new Actor();
            Actor supportingActor = new Actor();
            Gender gender = new Gender();
            AgeRating ageRating = new AgeRating();
 
            mainActor.setId(rs.getInt("a.id"));
            mainActor.setName(rs.getString("a.name"));
            mainActor.setSurname(rs.getString("a.surname"));
            mainActor.setPremiereDate(rs.getDate("a.premiereDate"));
            
            supportingActor.setId(rs.getInt("sa.id"));
            supportingActor.setName(rs.getString("sa.name"));
            supportingActor.setSurname(rs.getString("sa.surname"));
            supportingActor.setPremiereDate(rs.getDate("sa.premiereDate"));

            gender.setId(rs.getInt("genderId"));
            gender.setDescription(rs.getString("genderDescription"));

            ageRating.setId(rs.getInt("ageRatingId"));
            ageRating.setDescription(rs.getString("ageRatingDescription"));
            
            dvd.setId(rs.getInt("d.id"));
            dvd.setTitle(rs.getString("d.title"));
            dvd.setReleaseYear(rs.getString("d.releaseYear"));
            dvd.setReleaseDate(rs.getDate("d.releaseDate"));
            dvd.setDuration(rs.getLong("d.duration"));
 
            dvd.setGender(gender);
            dvd.setAgeRating(ageRating);
            dvd.setMainActor(mainActor);
            dvd.setSupportingActor(supportingActor);
        }

        rs.close();
        stmt.close();

        return dvd;

    }
}
