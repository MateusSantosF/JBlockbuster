
package locacaodvds.models;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Mateus Santos & João Pedro
 */
public class Dvd extends BaseEntity{
   
    private String title;
    private String releaseYear;
    private Date releaseDate;
    private Actor mainActor;
    private Actor supportingActor;
    private Time duration;
    private Gender gender;
    private AgeClassification ageClassification;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Actor getMainActor() {
        return mainActor;
    }

    public void setMainActor(Actor mainActor) {
        this.mainActor = mainActor;
    }

    public Actor getSupportingActor() {
        return supportingActor;
    }

    public void setSupportingActor(Actor supportingActor) {
        this.supportingActor = supportingActor;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AgeClassification getAgeClassification() {
        return ageClassification;
    }

    public void setAgeClassification(AgeClassification ageClassification) {
        this.ageClassification = ageClassification;
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
