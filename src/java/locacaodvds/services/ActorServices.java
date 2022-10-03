
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

    
     public List<Actor> getAll(){
       
        ActorDAO dao = null;    
        try{
            dao = new ActorDAO();
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
