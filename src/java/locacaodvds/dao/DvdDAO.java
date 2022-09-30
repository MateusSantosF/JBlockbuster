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
                + "    mainActor_id, "
                + "    supportingActor_id, "
                + "    duration, "
                + "    gender_id, "
                + "    ageRating) "
                + "VALUES( ?, ?, ?, ?, ?, ?, ?, ? );");
        stmt.setString(1, model.getTitle());
        stmt.setString(2, model.getReleaseYear());
        stmt.setDate(3, model.getReleaseDate());
        stmt.setInt(4, model.getMainActor().getId());
        stmt.setInt(5, model.getSupportingActor().getId());
        stmt.setLong(6, model.getDuration());
        stmt.setInt(7, model.getGender().getId());
        stmt.setInt(8, model.getAgeClassification().getId());

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
                + "    releaseDate = ? "
                + "    mainActor_id = ? "
                + "    supportingActor_id = ? "
                + "    duration = ? "
                + "    gender_id = ? "
                + "    ageRating = ? "
                + "WHERE id = ?;");

        stmt.setString(1, model.getTitle());
        stmt.setString(2, model.getReleaseYear());
        stmt.setDate(3, model.getReleaseDate());
        stmt.setInt(4, model.getMainActor().getId());
        stmt.setInt(5, model.getSupportingActor().getId());
        stmt.setLong(6, model.getDuration());
        stmt.setInt(7, model.getGender().getId());
        stmt.setInt(8, model.getAgeClassification().getId());
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
		"d.id as dvdId,"+
                "d.title, "+
                "d.releaseYear,"+ 
                "d.releaseDate, "+
                "d.duration,"+
                "d.mainActorFK, "+
                "d.supportingActorFK,"+ 
                "d.genderFK, "+
                "d.ageRatingFK,"+
                "g.id as genderId,"+
                "g.description as genderDescription,"+
                "ag.id as ageRatingId,"+
                "ag.description ageRatingDescription,"+
                    "(SELECT id from actor where actor.id = mainActorFK) as mainActorId,"+
                    "(SELECT name from actor where actor.id = mainActorFK) as mainActorName,"+
                    "(SELECT surname from actor where actor.id = mainActorFK) as mainActorSurname,"+
                    "(SELECT premiereDate from actor where actor.id = mainActorFK) as mainActorPremiereDate,"+
                    "(SELECT id from actor where actor.id = supportingActorFK) as supportingActorId,"+
                    "(SELECT name from actor where actor.id = supportingActorFK) as supportingActorName,"+
                    "(SELECT surname from actor where actor.id = supportingActorFK) as supportingActorSurname,"+
                    "(SELECT premiereDate from actor where actor.id = supportingActorFK) as supportingActorPremiereDate "+
                "FROM dvd d, gender g, ageRating ag "+
                "WHERE g.id = d.genderFK AND ag.id = d.ageRatingFK;");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Dvd dvd = new Dvd();
            Actor mainActor = new Actor();
            Actor supportingActor = new Actor();
            Gender gender = new Gender();
            AgeRating ageRating = new AgeRating();

       
            mainActor.setId(rs.getInt("mainActorId"));
            mainActor.setName(rs.getString("mainActorName"));
            mainActor.setSurname(rs.getString("mainActorSurname"));
            mainActor.setPremiereDate(rs.getDate("mainActorPremiereDate"));
            
            supportingActor.setId(rs.getInt("supportingActorId"));
            supportingActor.setName(rs.getString("supportingActorName"));
            supportingActor.setSurname(rs.getString("supportingActorSurname"));
            supportingActor.setPremiereDate(rs.getDate("supportingActorPremiereDate"));

            gender.setId(rs.getInt("genderId"));
            gender.setDescription(rs.getString("genderDescription"));

            ageRating.setId(rs.getInt("ageRatingId"));
            ageRating.setDescription(rs.getString("ageRatingDescription"));
            
            dvd.setId(rs.getInt("dvdId"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseYear(rs.getString("releaseYear"));
            dvd.setReleaseDate(rs.getDate("releaseDate"));
            dvd.setDuration(rs.getLong("duration"));
 
            dvd.setGender(gender);
            dvd.setAgeClassification(ageRating);
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
		"d.id as dvdId,"+
                "d.title, "+
                "d.releaseYear,"+ 
                "d.releaseDate, "+
                "d.duration,"+
                "d.mainActorFK, "+
                "d.supportingActorFK,"+ 
                "d.genderFK, "+
                "d.ageRatingFK,"+
                "g.id as genderId,"+
                "g.description as genderDescription,"+
                "ag.id as ageRatingId,"+
                "ag.description ageRatingDescription,"+
                    "(SELECT id from actor where actor.id = mainActorFK) as mainActorId,"+
                    "(SELECT name from actor where actor.id = mainActorFK) as mainActorName,"+
                    "(SELECT surname from actor where actor.id = mainActorFK) as mainActorSurname,"+
                    "(SELECT premiereDate from actor where actor.id = mainActorFK) as mainActorPremiereDate,"+
                    "(SELECT id from actor where actor.id = supportingActorFK) as supportingActorId,"+
                    "(SELECT name from actor where actor.id = supportingActorFK) as supportingActorName,"+
                    "(SELECT surname from actor where actor.id = supportingActorFK) as supportingActorSurname,"+
                    "(SELECT premiereDate from actor where actor.id = supportingActorFK) as supportingActorPremiereDate "+
                "FROM dvd d, gender g, ageRating ag "+
                "WHERE g.id = d.genderFK AND ag.id = d.ageRatingFK AND d.id = ?;");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            dvd = new Dvd();
            Actor mainActor = new Actor();
            Actor supportingActor = new Actor();
            Gender gender = new Gender();
            AgeRating ageRating = new AgeRating();
 
            mainActor.setId(rs.getInt("mainActorId"));
            mainActor.setName(rs.getString("mainActorName"));
            mainActor.setSurname(rs.getString("mainActorSurname"));
            mainActor.setPremiereDate(rs.getDate("mainActorPremiereDate"));
            
            supportingActor.setId(rs.getInt("supportingActorId"));
            supportingActor.setName(rs.getString("supportingActorName"));
            supportingActor.setSurname(rs.getString("supportingActorSurname"));
            supportingActor.setPremiereDate(rs.getDate("supportingActorPremiereDate"));

            gender.setId(rs.getInt("genderId"));
            gender.setDescription(rs.getString("genderDescription"));

            ageRating.setId(rs.getInt("ageRatingId"));
            ageRating.setDescription(rs.getString("ageRatingDescription"));
            
            dvd.setId(rs.getInt("dvdId"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseYear(rs.getString("releaseYear"));
            dvd.setReleaseDate(rs.getDate("releaseDate"));
            dvd.setDuration(rs.getLong("duration"));
 
            dvd.setGender(gender);
            dvd.setAgeClassification(ageRating);
            dvd.setMainActor(mainActor);
            dvd.setSupportingActor(supportingActor);
        }

        rs.close();
        stmt.close();

        return dvd;

    }
}
