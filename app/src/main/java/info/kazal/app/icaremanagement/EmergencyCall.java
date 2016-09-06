package info.kazal.app.icaremanagement;

/**
 * Created by arif on 6/26/15.
 */
public class EmergencyCall {

    private int id_call;
    private String nameNumberHolder;
    private String emergencyNumber;

    public EmergencyCall(int id_call, String nameNumberHolder, String emergencyNumber) {
        this.id_call = id_call;
        this.nameNumberHolder = nameNumberHolder;
        this.emergencyNumber = emergencyNumber;
    }

    public EmergencyCall(String nameNumberHolder, String emergencyNumber) {
        this.nameNumberHolder = nameNumberHolder;
        this.emergencyNumber = emergencyNumber;
    }

    public int getId_call() {
        return id_call;
    }

    public void setId_call(int id_call) {
        this.id_call = id_call;
    }

    public String getNameNumberHolder() {
        return nameNumberHolder;
    }

    public void setNameNumberHolder(String nameNumberHolder) {
        this.nameNumberHolder = nameNumberHolder;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }
}
