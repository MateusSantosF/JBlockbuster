package locacaodvds.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaodvds.models.Gender;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class GenderDAO extends DAO<Gender> {
    
    public GenderDAO() throws SQLException{
        
    }
     
    @Override
    public void insert(Gender model) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "gender (" + 
                "    description) " + 
                "VALUES(?);");
        stmt.setString( 1, model.getDescription());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void update(Gender model) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE gender " + 
                "SET" + 
                "    description = ? " + 
                "WHERE id = ?;");
        stmt.setString( 1, model.getDescription() );
        stmt.setInt( 2, model.getId());
        
        
        stmt.executeUpdate();
        stmt.close();               
    }

    @Override
    public void delete(Gender model) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM gender " + 
                "WHERE id = ?;");

        stmt.setInt( 1, model.getId() );
        
        stmt.executeUpdate();
        stmt.close();  
    }

    @Override
    public List<Gender> listAll() throws SQLException {
        
        List<Gender> genders = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT " + 
                "    id,     " +
                "    description " + 
                "FROM gender;");
        
        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {
            Gender gender = new Gender();
            gender.setId(rs.getInt("id"));
            gender.setDescription(rs.getString("description"));
            genders.add(gender);
        }
        rs.close();
        stmt.close();
        
        return genders;
    }

    @Override
    public Gender getById(int id) throws SQLException {
         
        Gender gender = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT " + 
                "    id,     " +
                "    description " + 
                "FROM gender "+
                "WHERE id = ?;");
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {
            gender = new Gender();
            gender.setId(rs.getInt("id"));
            gender.setDescription(rs.getString("description"));
        }
        
        rs.close();
        stmt.close();
        
        return gender;
    }   
    
}
