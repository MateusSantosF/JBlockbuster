
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
