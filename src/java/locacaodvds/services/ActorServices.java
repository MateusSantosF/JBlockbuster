
package locacaodvds.services;

import java.sql.SQLException;
import java.util.List;
import locacaodvds.dao.GenericDAO;
import locacaodvds.models.Actor;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class ActorServices {
    
  
    public void insert(Actor actor){
       
        GenericDAO<Actor> dao;
        
        try{
            dao = new GenericDAO<>(Actor::new);
            dao.insert(actor);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void delete(Actor actor){
       
        GenericDAO<Actor> dao;
        
        try{
            dao = new GenericDAO<>(Actor::new);
            dao.delete(actor);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
     public void update(Actor actor){
       
        GenericDAO<Actor> dao;
        
        try{
            dao = new GenericDAO<>(Actor::new);
            dao.update(actor);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public Actor getById(int id){
       
        GenericDAO<Actor> dao;
        
        try{
            dao = new GenericDAO<>( Actor::new);
            return dao.getById(id);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
     public List<Actor> ListAll(){
       
        GenericDAO<Actor> dao;    
        try{
            dao = new GenericDAO<>( Actor::new);
            return dao.listAll();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
}
