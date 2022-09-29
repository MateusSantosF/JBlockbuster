
package locacaodvds.services;

import java.sql.SQLException;
import java.util.List;
import locacaodvds.dao.ActorDAO;
import locacaodvds.models.Actor;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class ActorServices {
    
  
    public void insert(Actor actor){
       
        ActorDAO dao;
        
        try{
            dao = new ActorDAO();
            dao.insert(actor);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void delete(Actor actor){
       
        ActorDAO dao;
        
        try{
            dao = new ActorDAO();
            dao.delete(actor);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
     public void update(Actor actor){
       
        ActorDAO dao;
        
        try{
            dao = new ActorDAO();
            dao.update(actor);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public Actor getById(int id){
       
        ActorDAO dao;
        
        try{
            dao = new ActorDAO();
            return dao.getById(id);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
     public List<Actor> ListAll(){
       
        ActorDAO dao;    
        try{
            dao = new ActorDAO();
            return dao.listAll();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
}
