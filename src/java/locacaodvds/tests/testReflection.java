
package locacaodvds.tests;

import java.sql.Date;
import locacaodvds.models.Actor;
import locacaodvds.services.ActorServices;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class testReflection {
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        
        ActorServices actorServices = new ActorServices();
        
        Actor actor = new Actor();
        
        actor.setName("Mateus");
        actor.setSurname("Santos");
        actor.setPremiereDate(new Date(22, 2, 3));
        System.out.println(actor.getColumnsName());
        //actorServices.insert(actor);
        actorServices.ListAll().forEach( ac ->{
           System.out.println(ac.getName());
        });
        
     
    }
}
