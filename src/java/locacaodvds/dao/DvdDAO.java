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
        stmt.setTime(6, model.getDuration());
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
        stmt.setTime(6, model.getDuration());
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
                "SELECT"
                + "    d.id dvdId, "
                + "    d.title dvdTitle, "
                + "    d.releaseYear dvdReleaseYear, "
                + "    d.releaseDate dvdReleaseDate, "
                + "    d.duration dvdDuration, "
                + "    a.id mainActor_id, "
                + "    a.id supportingActor_id, "
                + "    a.name mainActor, "
                + "    a.name supportingActor, "
                + "    g.id gender_id, "
                + "    g.description genderDescription, "
                + "   ag.id ageClassification_id, "
                + "   ag.description ageClassificationDescription, "
                + "FROM"
                + "    dvd d, "
                + "    actor a, "
                + "    gender g, "
                + "    ageClassification ag "
                + "WHERE"
                + "    d.actor_id = a.id AND "
                + "    d.gender_id = g.id AND"
                + "    d.ageClassification_id = ag.id "
                + "ORDER BY d.title, d.releaseYear, d.duration;");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Dvd dvd = new Dvd();
            Actor actor = new Actor();
            Gender gender = new Gender();
            AgeRating ageRating = new AgeRating();

            dvd.setId(rs.getInt("dvdId"));
            dvd.setTitle(rs.getString("dvdTitle"));
            dvd.setReleaseYear(rs.getString("dvdReleaseYear"));
            dvd.setReleaseDate(rs.getDate("dvdReleaseDate"));
            dvd.setDuration(rs.getTime("dvdDuration"));
            dvd.setMainActor(actor);
            dvd.setSupportingActor(actor);
            dvd.setGender(gender);
            dvd.setAgeClassification(ageRating);

            actor.setId(rs.getInt("actor_id"));
            actor.setName(rs.getString("actorName"));
            actor.setSurname(rs.getString("actorSurname"));
            actor.setPremiereDate(rs.getDate("actorPremiereDate"));

            gender.setId(rs.getInt("genderId"));
            gender.setDescription(rs.getString("genderDescription"));

            ageRating.setId(rs.getInt("ageRating_id"));
            ageRating.setDescription(rs.getString("ageRatingDescription"));

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
                "SELECT"
                + "    d.id dvdId, "
                + "    d.title dvdTitle, "
                + "    d.releaseYear dvdReleaseYear, "
                + "    d.releaseDate dvdReleaseDate, "
                + "    d.duration dvdDuration, "
                + "    a.id mainActor_id, "
                + "    a.id supportingActor_id, "
                + "    a.name mainActor, "
                + "    a.name supportingActor, "
                + "    g.id gender_id, "
                + "    g.description genderDescription, "
                + "   ag.id ageClassification_id, "
                + "   ag.description ageClassificationDescription, "
                + "FROM"
                + "    dvd d, "
                + "    actor a, "
                + "    gender g, "
                + "    ageClassification ag "
                + "WHERE"
                + "    d.actor_id = a.id AND "
                + "    d.gender_id = g.id AND"
                + "    d.ageClassification_id = ag.id "
                + "ORDER BY d.title, d.releaseYear, d.duration;");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            dvd = new Dvd();
            Actor actor = new Actor();
            Gender gender = new Gender();
            AgeRating ageRating = new AgeRating();

            dvd.setId(rs.getInt("dvdId"));
            dvd.setTitle(rs.getString("dvdTitle"));
            dvd.setReleaseYear(rs.getString("dvdReleaseYear"));
            dvd.setReleaseDate(rs.getDate("dvdReleaseDate"));
            dvd.setDuration(rs.getTime("dvdDuration"));
            dvd.setMainActor(actor);
            dvd.setSupportingActor(actor);
            dvd.setGender(gender);
            dvd.setAgeClassification(ageRating);

            actor.setId(rs.getInt("actor_id"));
            actor.setName(rs.getString("actorName"));
            actor.setSurname(rs.getString("actorSurname"));
            actor.setPremiereDate(rs.getDate("actorPremiereDate"));

            gender.setId(rs.getInt("genderId"));
            gender.setDescription(rs.getString("genderDescription"));

            ageRating.setId(rs.getInt("ageRating_id"));
            ageRating.setDescription(rs.getString("ageRatingDescription"));

        }

        rs.close();
        stmt.close();

        return dvd;

    }
}
