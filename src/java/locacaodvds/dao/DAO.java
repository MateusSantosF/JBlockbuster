
package locacaodvds.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import locacaodvds.jdbc.ConnectionFactory;

/**
 *
 * @author Mateus Santos & João Pedro
 * @param <Model>
 */
public abstract class DAO<Model> {
    
    private final Connection connection;

    public DAO() throws SQLException {
        connection = ConnectionFactory.getConnection();

    }

    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection() throws SQLException {
        connection.close();
    }
 
    public abstract void insert( Model model ) throws SQLException;
  
    public abstract void update( Model model ) throws SQLException;

    public abstract void delete( Model model ) throws SQLException;
  
    public abstract List<Model> listAll() throws SQLException;

    public abstract Model getById( int id ) throws SQLException;
}
