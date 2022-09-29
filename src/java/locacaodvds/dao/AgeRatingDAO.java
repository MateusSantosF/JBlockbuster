
package locacaodvds.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaodvds.models.AgeRating;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class AgeRatingDAO extends DAO<AgeRating> {
    
    public AgeRatingDAO() throws SQLException{
        
    }
    
    @Override
    public void insert(AgeRating model) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "agerating(" + 
                "    description, " + 
                "VALUES( ?);");
        stmt.setString( 1, model.getDescription());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void update(AgeRating model) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE agerating " + 
                "SET" + 
                "    description = ? " + 
                "WHERE id = ?;");
        stmt.setInt( 1, model.getId());
        stmt.setString( 2, model.getDescription() );
        
        stmt.executeUpdate();
        stmt.close();               
    }

    @Override
    public void delete(AgeRating model) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM agerating " + 
                "WHERE id = ?;");

        stmt.setInt( 1, model.getId() );
        
        stmt.executeUpdate();
        stmt.close();  
    }

    @Override
    public List<AgeRating> listAll() throws SQLException {
        
        List<AgeRating> ageRatings = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT " + 
                "    id     " +
                "    description, " + 
                "FROM agerating;");
        
        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {
            AgeRating ageRating = new AgeRating();
            ageRating.setId(rs.getInt("id"));
            ageRating.setDescription(rs.getString("decription"));
            ageRatings.add(ageRating);
        }
        rs.close();
        stmt.close();
        
        return ageRatings;
    }

    @Override
    public AgeRating  getById(int id) throws SQLException {
         
        AgeRating ageRating = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT " + 
                "    id     " +
                "    description" + 
                "FROM agerating"+
                "WHERE id = ?;");
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {
            ageRating = new AgeRating();
            ageRating.setId(rs.getInt("id"));
            ageRating.setDescription(rs.getString("name"));
        }
        
        rs.close();
        stmt.close();
        
        return ageRating;
    }   
}
