package example.sql.com.providerexample.Database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Msi-Locale on 01/11/2017.
 */

public class Contract {

    public Contract() {
    }

    public static final String AUTHORITY = "example.sql.com.providerexample";
    public static final Uri BASE_URI = Uri.parse("content://"+AUTHORITY);
    public static final String PATH = "rubrica";
    public static final Uri URI_CONTENT = Uri.withAppendedPath(BASE_URI,PATH);

    public class ContractValues implements BaseColumns{

        public static final String TABLE_NAME = "rubrica";
        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_COGNOME = "cognome";
        public static final String COLUMN_NUMERO = "numero";


    }
}
