
package locacaodvds.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaodvds.models.Actor;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class ActorDAO extends DAO<Actor> {
    
    public ActorDAO() throws SQLException{
    
    }
    
    @Override
    public void insert(Actor model) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "actor(" + 
                "    name, " + 
                "    surname, " + 
                "    premiereDate) " + 
                "VALUES( ?, ?, ? );");
        stmt.setString( 1, model.getName() );
        stmt.setString( 2, model.getSurname() );
        stmt.setDate( 3, model.getPremiereDate() );
        
        stmt.executeUpdate();
        stmt.close();                       
    }

    @Override
    public void update(Actor model) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE actor " + 
                "SET" + 
                "    name = ?, " + 
                "    surname =?, " + 
                "    premiereDate = ? " + 
                "WHERE id = ?;");
        
        stmt.setInt( 1, model.getId() );
        stmt.setString( 2, model.getName() );
        stmt.setString( 3, model.getSurname() );
        stmt.setDate(4, model.getPremiereDate());
        
        stmt.executeUpdate();
        stmt.close();               
    }

    @Override
    public void delete(Actor model) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM actor " + 
                "WHERE id = ?;");

        stmt.setInt( 1, model.getId() );
        
        stmt.executeUpdate();
        stmt.close();  
    }

    @Override
    public List<Actor> listAll() throws SQLException {
        
        List<Actor> actors = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT " + 
                "    id     " +
                "    name, " + 
                "    surname, " + 
                "    premiereDate" + 
                "FROM actor"+
                "ORDER BY name;");
        
        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {
            Actor actor = new Actor();
            actor.setId(rs.getInt("id"));
            actor.setName(rs.getString("name"));
            actor.setSurname(rs.getString("surname"));
            actor.setPremiereDate(rs.getDate("premiereDate"));
            actors.add(actor);
        }
        rs.close();
        stmt.close();
        
        return actors;
    }

    @Override
    public Actor getById(int id) throws SQLException {
         
        Actor actor = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT " + 
                "    id     " +
                "    name, " + 
                "    surname, " + 
                "    premiereDate" + 
                "FROM actor"+
                "WHERE id = ?;");
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {
            actor = new Actor();
            actor.setId(rs.getInt("id"));
            actor.setName(rs.getString("name"));
            actor.setSurname(rs.getString("surname"));
            actor.setPremiereDate(rs.getDate("premiereDate"));

        }
        rs.close();
        stmt.close();
        
        return actor;
    }   
}
