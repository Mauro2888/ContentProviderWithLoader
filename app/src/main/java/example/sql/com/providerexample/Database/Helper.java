package example.sql.com.providerexample.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by Msi-Locale on 01/11/2017.
 */

public class Helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Rubrica.db";
    public static final int DATABASE_VERSION = 1;


    public Helper(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_DATABASE = " CREATE TABLE " + Contract.ContractValues.TABLE_NAME + " ( " +
                Contract.ContractValues._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.ContractValues.COLUMN_NOME + " TEXT, " +
                Contract.ContractValues.COLUMN_COGNOME + " TEXT, " +
                Contract.ContractValues.COLUMN_NUMERO + " INTEGER " + " )";

        sqLiteDatabase.execSQL(CREATE_DATABASE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + Contract.ContractValues.TABLE_NAME);
    }
}
