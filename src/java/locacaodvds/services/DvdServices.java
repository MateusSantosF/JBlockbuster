
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
    
    
     public List<Dvd> ListAll(){
       
        DvdDAO dao = null;    
        try{
            dao = new DvdDAO();
            return dao.listAll();
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            if(dao != null){
                try {
                    dao.closeConnection();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }  
        }
        return null;
    }
}
