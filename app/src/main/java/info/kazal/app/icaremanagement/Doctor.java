package info.kazal.app.icaremanagement;

/**
 * Created by mrFarhad on 10/03/15.
 */
public class Doctor {

    private int id_doctor;
    private int id_patientDoctor;
    private String doctorName;
    private String doctorNumber;
    private String doctorEmail;
    private String doctorAddress;
    private String aboutDoctor;

    public Doctor(int id_doctor, int id_patientDoctor, String doctorName, String doctorNumber, String doctorEmail, String doctorAddress, String aboutDoctor) {
        this.id_doctor = id_doctor;
        this.id_patientDoctor = id_patientDoctor;
        this.doctorName = doctorName;
        this.doctorNumber = doctorNumber;
        this.doctorEmail = doctorEmail;
        this.doctorAddress = doctorAddress;
        this.aboutDoctor = aboutDoctor;
    }

    public Doctor(int id_patientDoctor, String doctorName, String doctorNumber, String doctorEmail, String doctorAddress, String aboutDoctor) {
        this.id_patientDoctor = id_patientDoctor;
        this.doctorName = doctorName;
        this.doctorNumber = doctorNumber;
        this.doctorEmail = doctorEmail;
        this.doctorAddress = doctorAddress;
        this.aboutDoctor = aboutDoctor;
    }

    public Doctor(String doctorName, String doctorNumber, String doctorEmail, String doctorAddress, String aboutDoctor) {
        this.doctorName = doctorName;
        this.doctorNumber = doctorNumber;
        this.doctorEmail = doctorEmail;
        this.doctorAddress = doctorAddress;
        this.aboutDoctor = aboutDoctor;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_patientDoctor() {
        return id_patientDoctor;
    }

    public void setId_patientDoctor(int id_patientDoctor) {
        this.id_patientDoctor = id_patientDoctor;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getAboutDoctor() {
        return aboutDoctor;
    }

    public void setAboutDoctor(String aboutDoctor) {
        this.aboutDoctor = aboutDoctor;
    }
}
