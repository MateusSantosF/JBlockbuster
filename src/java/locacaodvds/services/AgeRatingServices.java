
package locacaodvds.services;

import java.sql.SQLException;
import java.util.List;
import locacaodvds.dao.AgeRatingDAO;
import locacaodvds.models.AgeRating;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class AgeRatingServices {
    
  
    public void insert(AgeRating ageRating){
       
        AgeRatingDAO dao;
        
        try{
            dao = new AgeRatingDAO();
            dao.insert(ageRating);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void delete(AgeRating ageRating){
       
        AgeRatingDAO dao;
        
        try{
            dao = new AgeRatingDAO();
            dao.delete(ageRating);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
     public void update(AgeRating ageRating){
       
        AgeRatingDAO dao;
        
        try{
            dao = new AgeRatingDAO();
            dao.update(ageRating);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public AgeRating getById(int id){
       
        AgeRatingDAO dao;
        
        try{
            dao = new AgeRatingDAO();
            return dao.getById(id);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
     public List<AgeRating> ListAll(){
       
        AgeRatingDAO dao;    
        try{
            dao = new AgeRatingDAO();
            return dao.listAll();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
}
