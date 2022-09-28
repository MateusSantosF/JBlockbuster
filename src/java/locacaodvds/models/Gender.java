package locacaodvds.models;

import java.lang.reflect.Field;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class Gender extends BaseEntity{
    
    private String Description;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
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
