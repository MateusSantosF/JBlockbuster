
package locacaodvds.services;

import java.sql.SQLException;
import java.util.List;
import locacaodvds.dao.GenderDAO;
import locacaodvds.models.Gender;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class GenderServices {
    
  
    public void insert(Gender gender){
       
        GenderDAO dao;
        
        try{
            dao = new GenderDAO();
            dao.insert(gender);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void delete(Gender gender){
       
        GenderDAO dao;
        
        try{
            dao = new GenderDAO();
            dao.delete(gender);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
     public void update(Gender gender){
       
        GenderDAO dao;
        
        try{
            dao = new GenderDAO();
            dao.update(gender);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public Gender getById(int id){
       
        GenderDAO dao;
        
        try{
            dao = new GenderDAO();
            return dao.getById(id);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
    public List<Gender> getAll(){
       
        GenderDAO dao = null;    
        try{

            dao = new GenderDAO();
            return dao.listAll();
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            if ( dao != null ) {
                try {
                    dao.closeConnection();
                } catch ( SQLException exc ) {
                    exc.printStackTrace();
                }
            }
        }
        return null;
    }
}
