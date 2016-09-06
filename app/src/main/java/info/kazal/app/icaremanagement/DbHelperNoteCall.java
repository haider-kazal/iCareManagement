package info.kazal.app.icaremanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by mrFarhad on 9/30/15.
 */
public class DbHelperNoteCall extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "noteCall";
    public static final int VERSION = 1;

    public static final String TABLE_NOTE = "note";
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_BODY = "body";
    public static final String ID_NOTE = "_ID_note";
    public static final String NOTE_DATE = "note_date";

    public static final String NOTE_SQL = "CREATE TABLE "+TABLE_NOTE+" ("+ID_NOTE+" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "+NOTE_TITLE+" TEXT, "+NOTE_BODY+" TEXT, "+NOTE_DATE+" TEXT)";


    public static final String TABLE_CALL = "call";
    public static final String ID_CALL = "_ID_call";
    public static final String CALL_NAME = "nameNumberHolder";
    public static final String CALL_NUMBER = "emergencyNumber";

    public static final String CALL_SQL = "CREATE TABLE "+TABLE_CALL+" ("+ID_CALL+" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "+CALL_NAME+" TEXT, "+CALL_NUMBER+" TEXT)";


    public DbHelperNoteCall(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(NOTE_SQL);
        db.execSQL(CALL_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public long addNote(Note note){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NOTE_TITLE, note.getNote_title());
        values.put(NOTE_BODY, note.getNote_body());
        values.put(NOTE_DATE, note.getModify_date());

        long inserted = db.insert(TABLE_NOTE, null, values);

        db.close();
        return inserted;
    }

    public int deleteNote(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete(TABLE_NOTE, ID_NOTE + "=?", new String[]{"" + id});

        db.close();
        return deleted;
    }

    public ArrayList<Note> getAllNote(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTE, null, null, null, null, null, null);
        ArrayList<Note> all = new ArrayList<Note>();

        if (cursor != null){

            if (cursor.getCount() > 0){

                cursor.moveToFirst();
                do {

                    int id = cursor.getInt(cursor.getColumnIndex(ID_NOTE));
                    String title = cursor.getString(cursor.getColumnIndex(NOTE_TITLE));
                    String body = cursor.getString(cursor.getColumnIndex(NOTE_BODY));
                    String date = cursor.getString(cursor.getColumnIndex(NOTE_DATE));

                    Note n  = new Note(id, title, body, date);
                    all.add(n);
                }while (cursor.moveToNext());
            }
        }
        db.close();
        cursor.close();
        return all;
    }

    public int updateNote(Note note, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NOTE_TITLE, note.getNote_title());
        values.put(NOTE_BODY, note.getNote_body());
        values.put(NOTE_DATE, note.getModify_date());

        int updated = db.update(TABLE_NOTE, values, ID_NOTE + "=?", new String[]{"" + id});
        db.close();
        return updated;
    }

    public Note getANote(int id_note){

        Note note = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NOTE+" where "+ID_NOTE+"='" + id_note + "'",null);

        if (cursor != null){

            if (cursor.getCount() > 0){

                cursor.moveToFirst();
                do {

                    int id = cursor.getInt(cursor.getColumnIndex(ID_NOTE));
                    String title = cursor.getString(cursor.getColumnIndex(NOTE_TITLE));
                    String body = cursor.getString(cursor.getColumnIndex(NOTE_BODY));
                    String date = cursor.getString(cursor.getColumnIndex(NOTE_DATE));

                    note = new Note(id,title,body,date);

                }while (cursor.moveToNext());
            }
        }
        db.close();
        cursor.close();
        return note;
    }

    public long addEmergencyCall(EmergencyCall eCall){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CALL_NAME, eCall.getNameNumberHolder());
        values.put(CALL_NUMBER, eCall.getEmergencyNumber());

        long inserted = db.insert(TABLE_CALL, null, values);
        db.close();
        return inserted;
    }

    public int deleteEmergencyCall(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete(TABLE_CALL, ID_CALL + "=?", new String[]{"" + id});

        db.close();
        return deleted;
    }

    public int updateEmergencyCall(EmergencyCall call, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CALL_NAME, call.getNameNumberHolder());
        values.put(CALL_NUMBER, call.getEmergencyNumber());

        int updated = db.update(TABLE_CALL, values, ID_CALL + "=?", new String[]{"" + id});
        db.close();
        return updated;
    }

    public ArrayList<EmergencyCall> getAllEmergencyNumber(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CALL, null, null, null, null, null, null, null);
        ArrayList<EmergencyCall> all = new ArrayList<EmergencyCall>();

        if (cursor != null){

            if (cursor.getCount() > 0){

                cursor.moveToFirst();
                do {

                    int id = cursor.getInt(cursor.getColumnIndex(ID_CALL));
                    String name = cursor.getString(cursor.getColumnIndex(CALL_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(CALL_NUMBER));

                    EmergencyCall call = new EmergencyCall(id,name,number);
                    all.add(call);
                }while (cursor.moveToNext());
            }
        }

        db.close();
        cursor.close();
        return all;
    }

    public EmergencyCall getEmergencyNumber(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CALL, null, null, null, null, null, null, null);
        EmergencyCall call = null;
        if (cursor != null){

            if (cursor.getCount() > 0){

                cursor.moveToFirst();
                do {

                    int id = cursor.getInt(cursor.getColumnIndex(ID_CALL));
                    String name = cursor.getString(cursor.getColumnIndex(CALL_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(CALL_NUMBER));

                    call = new EmergencyCall(id,name,number);

                }while (cursor.moveToNext());
            }
        }

        db.close();
        cursor.close();
        return call;
    }

    public EmergencyCall getAEmergencyNumber(int id_call){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_CALL + " where " + ID_CALL + "='" + id_call + "'", null);
        EmergencyCall call = null;
        if (cursor != null){

            if (cursor.getCount() > 0){

                cursor.moveToFirst();
                do {

                    int id = cursor.getInt(cursor.getColumnIndex(ID_CALL));
                    String name = cursor.getString(cursor.getColumnIndex(CALL_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(CALL_NUMBER));

                    call = new EmergencyCall(id,name,number);

                }while (cursor.moveToNext());
            }
        }

        db.close();
        cursor.close();
        return call;
    }
}
