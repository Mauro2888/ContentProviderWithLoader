package example.sql.com.providerexample.Database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.sql.com.providerexample.R;

/**
 * Created by Msi-Locale on 01/11/2017.
 */

public class CursorAdapter extends android.widget.CursorAdapter {


    public CursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View v = LayoutInflater.from(context).inflate(R.layout.listview_layout,viewGroup,false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView mTextNome = view.findViewById(R.id.nome_layout);
        TextView mTextCognome = view.findViewById(R.id.cognome_layout);
        TextView mTextNumero = view.findViewById(R.id.numero_layout);

        int id = cursor.getColumnIndexOrThrow(Contract.ContractValues._ID);
        int nome = cursor.getColumnIndexOrThrow(Contract.ContractValues.COLUMN_NOME);
        int cognome = cursor.getColumnIndexOrThrow(Contract.ContractValues.COLUMN_COGNOME);
        int numero = cursor.getColumnIndexOrThrow(Contract.ContractValues.COLUMN_NUMERO);

        mTextNome.setText(cursor.getString(nome));
        mTextCognome.setText(cursor.getString(cognome));
        mTextNumero.setText(cursor.getString(numero));

    }
}
