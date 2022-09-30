
package locacaodvds.services;

import java.sql.SQLException;
import java.util.List;
import locacaodvds.dao.DvdDAO;
import locacaodvds.models.Dvd;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class DvdServices {
    
  
    public void insert(Dvd dvd){
       
        DvdDAO dao;
        
        try{
            dao = new DvdDAO();
            dao.insert(dvd);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void delete(Dvd dvd){
       
        DvdDAO dao;
        
        try{
            dao = new DvdDAO();
            dao.delete(dvd);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
     public void update(Dvd dvd){
       
        DvdDAO dao;
        
        try{
            dao = new DvdDAO();
            dao.update(dvd);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public Dvd getById(int id){
       
        DvdDAO dao;
        
        try{
            dao = new DvdDAO();
            return dao.getById(id);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
     public List<Dvd> ListAll(){
       
        DvdDAO dao;    
        try{
            dao = new DvdDAO();
            return dao.listAll();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
}
