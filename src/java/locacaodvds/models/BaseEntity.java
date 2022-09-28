
package locacaodvds.models;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public abstract class BaseEntity {
    
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public abstract String getColumnsName();
    

    
}
