
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
 
    public abstract void insert( Model obj ) throws SQLException;
  
    public abstract void update( Model obj ) throws SQLException;

    public abstract void delete( Model obj ) throws SQLException;
  
    public abstract <Model> List<Model> listAll() throws SQLException;

    public abstract <Model> Model getById( int id ) throws SQLException;
}
