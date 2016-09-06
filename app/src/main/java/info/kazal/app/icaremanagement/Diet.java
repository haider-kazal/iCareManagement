package info.kazal.app.icaremanagement;

/**
 * Created by mrFarhad on 10/03/15.
 */
public class Diet {

    private int id_diet;
    private int id_patient;
    private String day;
    private String breakfast;
    private String lunch;
    private String dinner;

    public Diet(int id_diet, int id_patient, String day, String breakfast, String lunch, String dinner) {
        this.id_diet = id_diet;
        this.id_patient = id_patient;
        this.day = day;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public Diet(int id_patient, String day, String breakfast, String lunch, String dinner) {
        this.id_patient = id_patient;
        this.day = day;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public Diet(String day, String breakfast, String lunch, String dinner) {
        this.day = day;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public Diet(){

    }

    public int getId_diet() {
        return id_diet;
    }

    public void setId_diet(int id_diet) {
        this.id_diet = id_diet;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}
