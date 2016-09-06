package info.kazal.app.icaremanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by mrFarhad on 9/20/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "iCareDatabase";
    public static final int VERSION = 1;

    public static final String TABLE_NAME = "patient_profile";
    public static final String ID_FIELD = "_ID";
    public static final String PATIENT_NAME_FIELD = "patient_name";
    public static final String PATIENT_PROFILE_TYPE = "patient_profile_type";
    public static final String PATIENT_GENDER = "patient_gender";
    public static final String PATIENT_BLOOD_GROUP = "patient_blood_group";
    public static final String CURRENT_DATE = "current_date";
    public static final String PATIENT_AGE = "patient_age";
    public static final String PATIENT_HEIGHT = "patient_height";
    public static final String PATIENT_WEIGHT = "patient_weight";
    public static final String PATIENT_PHONE_NUMBER = "patent_phone_number";
    public static final String PATIENT_EMAIL = "patient_email";
    public static final String PATIENT_CONDITION = "patient_condition";
    public static final String PATIENT_IMAGE = "patient_image";

    public static final String PATIENT_TABLE_SQL = "CREATE TABLE "+TABLE_NAME+" ("+ID_FIELD+" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "+PATIENT_NAME_FIELD+" TEXT, "+PATIENT_PROFILE_TYPE +" TEXT, "+PATIENT_GENDER+" TEXT, "+PATIENT_BLOOD_GROUP+" TEXT, "+CURRENT_DATE+" TEXT, "+PATIENT_AGE+" INTEGER, "+PATIENT_HEIGHT+" DOUBLE, "+PATIENT_WEIGHT+" DOUBLE, "+PATIENT_PHONE_NUMBER+" TEXT, "+PATIENT_EMAIL+" TEXT, "+PATIENT_CONDITION+" TEXT, "+PATIENT_IMAGE+" BLOB)";


    public static final String DIET_TABLE = "diet_table";
    public static final String ID_DIET = "_ID_diet";
    public static final String ID_PATIENT_DIET = "_ID_patient";
    public static final String DIET_DAY_FIELD = "diet_day";
    public static final String DIET_BREAKFAST = "diet_breakfast";
    public static final String DIET_LUNCH = "diet_lunch";
    public static final String DIET_DINNER = "diet_dinner";


    public static final String DIET_TABLE_SQL = "CREATE TABLE "+DIET_TABLE+" ("+ID_DIET+" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "+ID_PATIENT_DIET+" INTEGER, "+DIET_DAY_FIELD+" TEXT, "+DIET_BREAKFAST+" TEXT, "+DIET_LUNCH+" TEXT, "+DIET_DINNER+" TEXT)";


    public static final String DOCTOR_TABLE  = "doctor_table";
    public static final String ID_DOCTOR = "_ID_doctor";
    public static final String ID_PATIENT_DOCTOR = "_ID_patient_doctor";
    public static final String DOCTOR_NAME = "doctor_name";
    public static final String DOCTOR_NUMBER = "doctor_number";
    public static final String DOCTOR_EMAIL = "doctor_email";
    public static final String DOCTOR_ADDRESS = "doctor_address";
    public static final String DOCTOR_ABOUT = "doctor_about";

    public static final String DOCTOR_TABLE_SQL = "CREATE TABLE "+DOCTOR_TABLE+" ("+ID_DOCTOR+" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "+ID_PATIENT_DOCTOR+" INTEGER, "+DOCTOR_NAME+" TEXT, "+DOCTOR_NUMBER+" TEXT, "+DOCTOR_EMAIL+" TEXT, "+DOCTOR_ADDRESS+" TEXT, "+DOCTOR_ABOUT+" TEXT)";


//    public static final String HOSPITAL_TABLE  = "hospital_table";
//    public static final String ID_HOSPITAL = "_ID_hospital";
//    public static final String ID_PATIENT_HOSPITAL = "_ID_patient_hospital";
//    public static final String HOSPITAL_NAME = "hospital_name";
//    public static final String HOSPITAL_ADDRESS = "hospital_address";
//    public static final String HOSPITAL_PHONE = "hospital_phone";
//    public static final String HOSPITAL_LATITUDE = "hospital_latitude";
//    public static final String HOSPITAL_LONGITUDE = "hospital_longitude";
//
//
//    public static final String HOSPITAL_TABLE_SQL = "CREATE TABLE "+HOSPITAL_TABLE+" ("+ID_HOSPITAL+" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "+ID_PATIENT_HOSPITAL+" INTEGER, "+HOSPITAL_NAME+" TEXT, "+HOSPITAL_ADDRESS+" TEXT, "+HOSPITAL_PHONE+" TEXT, "+HOSPITAL_LATITUDE+" TEXT, "+HOSPITAL_LONGITUDE+" TEXT)";
//

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(PATIENT_TABLE_SQL);
        sqLiteDatabase.execSQL(DIET_TABLE_SQL);
        sqLiteDatabase.execSQL(DOCTOR_TABLE_SQL);
        //sqLiteDatabase.execSQL(HOSPITAL_TABLE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }



    //Add a patient profile to database.
    public long addPatient(PatientTemplate p){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(ID_FIELD, p.getId());
        values.put(PATIENT_NAME_FIELD, p.getName());
        values.put(PATIENT_PROFILE_TYPE, p.getPatientType());
        values.put(PATIENT_GENDER, p.getGender());
        values.put(PATIENT_BLOOD_GROUP, p.getBloodGroup());
        values.put(CURRENT_DATE, p.getCurrentDate());
        values.put(PATIENT_AGE, p.getAge());
        values.put(PATIENT_HEIGHT, p.getHeight());
        values.put(PATIENT_WEIGHT, p.getWeight());
        values.put(PATIENT_PHONE_NUMBER, p.getPhoneNumber());
        values.put(PATIENT_EMAIL, p.getEmail());
        values.put(PATIENT_CONDITION, p.getPatientCondition());
        values.put(PATIENT_IMAGE, p.getPatient_image());

        long inserted = db.insert(TABLE_NAME, null, values);

        db.close();

        return inserted;
    }

    // Updating a patient profile.
    public int updatePatient(PatientTemplate p, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values  = new ContentValues();

        values.put(PATIENT_NAME_FIELD, p.getName());
        values.put(PATIENT_PROFILE_TYPE, p.getPatientType());
        values.put(PATIENT_GENDER, p.getGender());
        values.put(PATIENT_BLOOD_GROUP, p.getBloodGroup());
        values.put(CURRENT_DATE, p.getCurrentDate());
        values.put(PATIENT_AGE, p.getAge());
        values.put(PATIENT_HEIGHT, p.getHeight());
        values.put(PATIENT_WEIGHT, p.getWeight());
        values.put(PATIENT_PHONE_NUMBER, p.getPhoneNumber());
        values.put(PATIENT_EMAIL, p.getEmail());
        values.put(PATIENT_CONDITION, p.getPatientCondition());
        values.put(PATIENT_IMAGE, p.getPatient_image());

        int updated = db.update(TABLE_NAME, values, ID_FIELD + "=?", new String[]{"" + id});
        db.close();
        return updated;
    }


    // Get all patient profile.
    public ArrayList<PatientTemplate> getAllPatient(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null,null);
        ArrayList<PatientTemplate> all = new ArrayList<PatientTemplate>();

        if (cursor != null){

            if (cursor.getCount()>0){

                cursor.moveToFirst();
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(ID_FIELD));
                    String name = cursor.getString(cursor.getColumnIndex(PATIENT_NAME_FIELD));
                    String profileType = cursor.getString(cursor.getColumnIndex(PATIENT_PROFILE_TYPE));
                    String gender = cursor.getString(cursor.getColumnIndex(PATIENT_GENDER));
                    String bloodGroup = cursor.getString(cursor.getColumnIndex(PATIENT_BLOOD_GROUP));
                    String currentDate = cursor.getString(cursor.getColumnIndex(CURRENT_DATE));
                    int age = cursor.getInt(cursor.getColumnIndex(PATIENT_AGE));
                    double height = cursor.getDouble(cursor.getColumnIndex(PATIENT_HEIGHT));
                    double weight = cursor.getDouble(cursor.getColumnIndex(PATIENT_WEIGHT));
                    String phoneNumber = cursor.getString(cursor.getColumnIndex(PATIENT_PHONE_NUMBER));
                    String email = cursor.getString(cursor.getColumnIndex(PATIENT_EMAIL));
                    String patientCondition = cursor.getString(cursor.getColumnIndex(PATIENT_CONDITION));
                    byte[] patientImage = cursor.getBlob(cursor.getColumnIndex(PATIENT_IMAGE));

                    PatientTemplate patient = new PatientTemplate(id,name,profileType,gender,bloodGroup,currentDate,age,height,weight,phoneNumber,email,patientCondition,patientImage);
                    all.add(patient);

                }while (cursor.moveToNext());
            }
        }

        db.close();
        cursor.close();
        return all;
    }

    public PatientTemplate getAPatient(int id_patient){

        PatientTemplate patient = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + ID_FIELD + "='" + id_patient + "'", null);

        if (cursor != null){

            if (cursor.getCount()>0){

                cursor.moveToFirst();
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(ID_FIELD));
                    String name = cursor.getString(cursor.getColumnIndex(PATIENT_NAME_FIELD));
                    String profileType = cursor.getString(cursor.getColumnIndex(PATIENT_PROFILE_TYPE));
                    String gender = cursor.getString(cursor.getColumnIndex(PATIENT_GENDER));
                    String bloodGroup = cursor.getString(cursor.getColumnIndex(PATIENT_BLOOD_GROUP));
                    String currentDate = cursor.getString(cursor.getColumnIndex(CURRENT_DATE));
                    int age = cursor.getInt(cursor.getColumnIndex(PATIENT_AGE));
                    double height = cursor.getDouble(cursor.getColumnIndex(PATIENT_HEIGHT));
                    double weight = cursor.getDouble(cursor.getColumnIndex(PATIENT_WEIGHT));
                    String phoneNumber = cursor.getString(cursor.getColumnIndex(PATIENT_PHONE_NUMBER));
                    String email = cursor.getString(cursor.getColumnIndex(PATIENT_EMAIL));
                    String patientCondition = cursor.getString(cursor.getColumnIndex(PATIENT_CONDITION));
                    byte[] patientImage = cursor.getBlob(cursor.getColumnIndex(PATIENT_IMAGE));

                    patient = new PatientTemplate(id,name,profileType,gender,bloodGroup,currentDate,age,height,weight,phoneNumber,email,patientCondition,patientImage);

                }while (cursor.moveToNext());
            }
        }

        db.close();
        cursor.close();
        return patient;
    }


    // Delete a patient profile.
    public int deletePatient(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete(TABLE_NAME, ID_FIELD + "=?", new String[]{"" + id});

        db.close();
        return deleted;
    }


    // Add diet chart under a patient profile.
    public long addDietChart(Diet diet){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID_PATIENT_DIET, diet.getId_patient());
        values.put(DIET_DAY_FIELD, diet.getDay());
        values.put(DIET_BREAKFAST, diet.getBreakfast());
        values.put(DIET_LUNCH, diet.getLunch());
        values.put(DIET_DINNER, diet.getDinner());

        long inserted = db.insert(DIET_TABLE, null, values);
        db.close();
        return inserted;
    }

    public int updateDiet(Diet diet, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID_PATIENT_DIET, diet.getId_patient());
        values.put(DIET_DAY_FIELD, diet.getDay());
        values.put(DIET_BREAKFAST, diet.getBreakfast());
        values.put(DIET_LUNCH, diet.getLunch());
        values.put(DIET_DINNER, diet.getDinner());

        int updated = db.update(DIET_TABLE, values, ID_DIET + "=?", new String[]{"" + id});
        db.close();
        return updated;
    }


    // Get diet chart of a patient profile holder.
    public ArrayList<Diet> getDietChart(int id){

        ArrayList<Diet> all = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+DIET_TABLE+" where "+ID_PATIENT_DIET+"='" + id + "'",null);

        if (cursor != null){

            if (cursor.getCount() > 0){

                cursor.moveToFirst();

                do {

                    int id_diet = cursor.getInt(cursor.getColumnIndex(ID_DIET));
                    int id_patient_diet = cursor.getInt(cursor.getColumnIndex(ID_PATIENT_DIET));
                    String diet_day = cursor.getString(cursor.getColumnIndex(DIET_DAY_FIELD));
                    String diet_breakfast = cursor.getString(cursor.getColumnIndex(DIET_BREAKFAST));
                    String diet_lunch = cursor.getString(cursor.getColumnIndex(DIET_LUNCH));
                    String diet_dinner = cursor.getString(cursor.getColumnIndex(DIET_DINNER));

                    Diet diet = new Diet(id_diet,id_patient_diet,diet_day,diet_breakfast,diet_lunch,diet_dinner);
                    all.add(diet);

                }while (cursor.moveToNext());
            }
        }
        db.close();
        cursor.close();
        return all;
    }

    public Diet getADietChart(int id){

        Diet diet = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+DIET_TABLE+" where "+ID_DIET+"='" + id + "'",null);

        if (cursor != null){

            if (cursor.getCount() > 0){

                cursor.moveToFirst();

                do {

                    int id_diet = cursor.getInt(cursor.getColumnIndex(ID_DIET));
                    int id_patient_diet = cursor.getInt(cursor.getColumnIndex(ID_PATIENT_DIET));
                    String diet_day = cursor.getString(cursor.getColumnIndex(DIET_DAY_FIELD));
                    String diet_breakfast = cursor.getString(cursor.getColumnIndex(DIET_BREAKFAST));
                    String diet_lunch = cursor.getString(cursor.getColumnIndex(DIET_LUNCH));
                    String diet_dinner = cursor.getString(cursor.getColumnIndex(DIET_DINNER));

                    diet = new Diet(id_diet,id_patient_diet,diet_day,diet_breakfast,diet_lunch,diet_dinner);

                }while (cursor.moveToNext());
            }
        }
        db.close();
        cursor.close();
        return diet;
    }


    // Delete diet chart.
    public int deleteDietChartSingle(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete(DIET_TABLE, ID_DIET + "=?", new String[]{"" + id});
        db.close();
        return deleted;
    }

    // Deleting full diet chart against a patient profile
    public int deleteDietChartAll(int id){

        int deleted = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+DIET_TABLE+" where "+ID_PATIENT_DIET+"='" + id + "'",null);
        if (cursor != null){

            if (cursor.getCount() >0 ){

                do {
                    deleted = db.delete(DIET_TABLE, ID_PATIENT_DIET+"=?", new String[]{""+id});
                }while (cursor.moveToNext());
            }
        }

        db.close();
        cursor.close();
        return deleted;
    }

    public long addDoctor(Doctor doctor){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values  = new ContentValues();

        values.put(ID_PATIENT_DOCTOR, doctor.getId_patientDoctor());
        values.put(DOCTOR_NAME, doctor.getDoctorName());
        values.put(DOCTOR_NUMBER, doctor.getDoctorNumber());
        values.put(DOCTOR_EMAIL,doctor.getDoctorEmail());
        values.put(DOCTOR_ADDRESS, doctor.getDoctorAddress());
        values.put(DOCTOR_ABOUT, doctor.getAboutDoctor());

        long inserted = db.insert(DOCTOR_TABLE, null, values);
        db.close();
        return inserted;
    }

    public int updateDoctor(Doctor doctor, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values  = new ContentValues();

        values.put(ID_PATIENT_DOCTOR, doctor.getId_patientDoctor());
        values.put(DOCTOR_NAME, doctor.getDoctorName());
        values.put(DOCTOR_NUMBER, doctor.getDoctorNumber());
        values.put(DOCTOR_EMAIL,doctor.getDoctorEmail());
        values.put(DOCTOR_ADDRESS, doctor.getDoctorAddress());
        values.put(DOCTOR_ABOUT, doctor.getAboutDoctor());

        int updated = db.update(DOCTOR_TABLE, values, ID_DOCTOR + "=?", new String[]{"" + id});
        db.close();
        return updated;
    }

    public ArrayList<Doctor> getAllDoctor(int id){

        ArrayList<Doctor> all = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DOCTOR_TABLE + " where " + ID_PATIENT_DOCTOR + "='" + id + "'", null);

        if (cursor != null){

            if (cursor.getCount() > 0){

                cursor.moveToFirst();
                do {

                    int id_doctor = cursor.getInt(cursor.getColumnIndex(ID_DOCTOR));
                    int id_patientDoctor = cursor.getInt(cursor.getColumnIndex(ID_PATIENT_DOCTOR));
                    String name = cursor.getString(cursor.getColumnIndex(DOCTOR_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(DOCTOR_NUMBER));
                    String email = cursor.getString(cursor.getColumnIndex(DOCTOR_EMAIL));
                    String address = cursor.getString(cursor.getColumnIndex(DOCTOR_ADDRESS));
                    String about = cursor.getString(cursor.getColumnIndex(DOCTOR_ABOUT));


                    Doctor doctor = new Doctor(id_doctor,id_patientDoctor,name,number,email,address,about);
                    all.add(doctor);

                }while (cursor.moveToNext());
            }
        }

        db.close();
        cursor.close();
        return all;

    }

    public Doctor getADoctor(int id){

        Doctor doctor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DOCTOR_TABLE + " where " + ID_DOCTOR + "='" + id + "'", null);

        if (cursor != null){

            if (cursor.getCount() > 0){

                cursor.moveToFirst();
                do {

                    int id_doctor = cursor.getInt(cursor.getColumnIndex(ID_DOCTOR));
                    int id_patientDoctor = cursor.getInt(cursor.getColumnIndex(ID_PATIENT_DOCTOR));
                    String name = cursor.getString(cursor.getColumnIndex(DOCTOR_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(DOCTOR_NUMBER));
                    String email = cursor.getString(cursor.getColumnIndex(DOCTOR_EMAIL));
                    String address = cursor.getString(cursor.getColumnIndex(DOCTOR_ADDRESS));
                    String about = cursor.getString(cursor.getColumnIndex(DOCTOR_ABOUT));


                    doctor = new Doctor(id_doctor,id_patientDoctor,name,number,email,address,about);

                }while (cursor.moveToNext());
            }
        }

        db.close();
        cursor.close();
        return doctor;

    }

    public int deleteDoctor(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete(DOCTOR_TABLE, ID_DOCTOR + "=?", new String[]{"" + id});
        db.close();
        return deleted;
    }

}
