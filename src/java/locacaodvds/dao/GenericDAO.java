package locacaodvds.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import locacaodvds.models.BaseEntity;


/**
 *
 * @author Mateus Santos & João Pedro
 * @param <Model>
 */
public class GenericDAO <Model extends BaseEntity> extends DAO<Model>{
    
    private final String tableName;
    private final Supplier<Model> supplier;
    
    public GenericDAO(Supplier<Model> supplier) throws SQLException{
        this.tableName = supplier.get().getClass().getSimpleName();
        this.supplier = supplier;
    }
    
    @Override
    public void insert(Model model) throws SQLException {
        
        String sqlQuery = String.format("INSERT INTO %s (%s) VALUES (%s)", 
                tableName, model.getColumnsName(), getQuestionMarks());

        PreparedStatement preparedStatement = getConnection()
                                              .prepareStatement(sqlQuery);
        
        setPreparedStatementValues(preparedStatement, model);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        
    }

    @Override
    public void update(Model model) throws SQLException {
           
        String sqlQuery = String.format("UPDATE %s VALUES (%s) WHERE id = %d ", 
                tableName, model.getColumnsName(),
                model.getId());
        PreparedStatement preparedStatement = getConnection()
                                              .prepareStatement(sqlQuery);
        
        setPreparedStatementValues(preparedStatement, model);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void delete(Model model) throws SQLException {
        
        String sqlQuery = String.format("DELETE %s WHERE id = %d ", tableName, model.getId());        
        PreparedStatement preparedStatement = getConnection()
                                              .prepareStatement(sqlQuery);
        
        setPreparedStatementValues(preparedStatement, model);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public <Model> List<Model> listAll() throws SQLException {
        
        String sqlQuery = String.format("SELECT * FROM %s ", tableName);        
        PreparedStatement preparedStatement = getConnection()
                                              .prepareStatement(sqlQuery);
        
        List<Model> models = new ArrayList<>();
        
        ResultSet rs = preparedStatement.executeQuery();

        while ( rs.next() ) {  
           Object newModel = supplier.get();  
           newModel = setModelAtributes(rs, newModel);
           models.add((Model) newModel);
        }
        preparedStatement.close();
        rs.close();
        return models;
    }

    @Override
    public <Model> Model getById(int id) throws SQLException {
        
        String sqlQuery = String.format("SELECT * FROM %s WHERE id = %d ", tableName, id);        
        PreparedStatement preparedStatement = getConnection()
                                              .prepareStatement(sqlQuery);
        
        Object model = supplier.get();       
        
        ResultSet rs = preparedStatement.executeQuery();

        while ( rs.next() ) {      
           model = setModelAtributes(rs, model);
        }
        
        preparedStatement.close();
        rs.close();
        return (Model) model;
    }
    
  
   
   
    private Model setModelAtributes(ResultSet rs, Object model){
        String[] columns = ((Model)model).getColumnsName().split(",");
       
         for( int i = 0; i < columns.length; i++){      
            try {
                Field field =  model.getClass().getDeclaredField(columns[i]);
                field.setAccessible(true);
                field.set(model, rs.getObject(columns[i]));
            } catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
                Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  
        return (Model)model;
    }
    private void setPreparedStatementValues(PreparedStatement stmt, Model model){
        
        String[] columns = model.getColumnsName().split(",");
        
        for( int i = 0; i < columns.length; i++){      
            try {
                Field field = model.getClass().getDeclaredField(columns[i]);             
                field.setAccessible(true);    
                //System.out.println("columns["+ i + "] = "+ columns[i]+ " | value = " + field.get(model) );
                stmt.setObject(i+1, field.get(model));
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException | SQLException ex) {
                Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            }                
        }
       
    }
    
   private String getQuestionMarks(){ 
       
       StringBuilder sb = new StringBuilder();
       
       for(String s:  supplier.get().getColumnsName().split(",") ){
           sb.append("?");
           sb.append(",");
       }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
}
