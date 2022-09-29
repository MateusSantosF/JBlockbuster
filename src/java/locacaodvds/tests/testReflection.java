
package locacaodvds.tests;

import java.sql.Date;
import locacaodvds.models.Actor;
import locacaodvds.models.Gender;
import locacaodvds.services.ActorServices;
import locacaodvds.services.GenderServices;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class testReflection {
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        


        GenderServices services = new GenderServices();
        
        Gender gender = new Gender();
        
        gender.setDescription("Genero 3");
        //services.insert(gender);
        services.getAll().forEach(t->{
            System.out.println(t.getDescription());
        });
    }
}
