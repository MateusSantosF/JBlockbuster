
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

    
     public List<AgeRating> ListAll(){
       
        AgeRatingDAO dao = null;    
        try{
            dao = new AgeRatingDAO();
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
