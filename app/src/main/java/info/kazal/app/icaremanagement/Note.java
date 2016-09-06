package info.kazal.app.icaremanagement;

/**
 * Created by Mrfarhad on 10/02/15.
 */
public class Note {

    private int id_note;
    private String note_title;
    private String note_body;
    private String modify_date;

    public Note(int id_note, String note_title, String note_body, String modify_date) {
        this.id_note = id_note;
        this.note_title = note_title;
        this.note_body = note_body;
        this.modify_date = modify_date;
    }

    public Note(String note_title, String note_body, String modify_date) {
        this.note_title = note_title;
        this.note_body = note_body;
        this.modify_date = modify_date;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_body() {
        return note_body;
    }

    public void setNote_body(String note_body) {
        this.note_body = note_body;
    }

    public String getModify_date() {
        return modify_date;
    }

    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }
}
