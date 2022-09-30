
package locacaodvds.tests;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import locacaodvds.dao.DvdDAO;


/**
 *
 * @author Mateus Santos & João Pedro
 */
public class testReflection {
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        


        DvdDAO dAO = null;
        try {
            dAO = new DvdDAO();
             System.out.println(dAO.getById(2).getTitle());
        } catch (SQLException ex) {
            Logger.getLogger(testReflection.class.getName()).log(Level.SEVERE, null, ex);
        }
       

       
      
    }
}
