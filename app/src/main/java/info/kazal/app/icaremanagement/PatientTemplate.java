package info.kazal.app.icaremanagement;

/**
 * Created by Mrfarhad on 10/02/15.
 */
public class PatientTemplate {

    private int id;
    private String name;
    private String patientType;
    private String gender;
    private String bloodGroup;
    private String currentDate;
    private int age;
    private double height;
    private double weight;
    private String phoneNumber;
    private String email;
    private String patientCondition;
    private byte[] patient_image;

    public PatientTemplate(int id, String name, String patientType, String gender, String bloodGroup, String currentDate, int age, double height, double weight, String phoneNumber, String email, String patientCondition, byte[] patient_image) {
        this.id = id;
        this.name = name;
        this.patientType = patientType;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.currentDate = currentDate;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.patientCondition = patientCondition;
        this.patient_image = patient_image;
    }

    public PatientTemplate(String name, String patientType, String gender, String bloodGroup, String currentDate, int age, double height, double weight, String phoneNumber, String email, String patientCondition, byte[] patient_image) {
        this.name = name;
        this.patientType = patientType;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.currentDate = currentDate;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.patientCondition = patientCondition;
        this.patient_image = patient_image;
    }

    public PatientTemplate(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPatientCondition() {
        return patientCondition;
    }

    public void setPatientCondition(String patientCondition) {
        this.patientCondition = patientCondition;
    }

    public byte[] getPatient_image() {
        return patient_image;
    }

    public void setPatient_image(byte[] patient_image) {
        this.patient_image = patient_image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
