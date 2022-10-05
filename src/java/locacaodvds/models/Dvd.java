
package locacaodvds.models;
import java.sql.Date;

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
    private long duration;
    private Gender gender;
    private AgeRating ageRating;


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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }   
}
