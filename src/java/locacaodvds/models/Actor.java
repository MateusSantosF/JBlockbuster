
package locacaodvds.models;

import java.lang.reflect.Field;
import java.sql.Date;


/**
 *
 * @author Mateus Santos & João Pedro
 */
public class Actor extends BaseEntity {
    
    private String name;
    private String surname;
    private Date premiereDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(Date premiereDate) {
        this.premiereDate = premiereDate;
    }
    
    
    @Override
    public String getColumnsName() {
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder stb = new StringBuilder();
  
        for (Field field : fields) {
            stb.append(field.getName());
            stb.append(",");
        }
        stb.deleteCharAt(stb.length() - 1);
        return stb.toString();
    }
    

    
}
