package example.sql.com.providerexample;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.sql.com.providerexample.Database.Contract;

public class MainActivity extends AppCompatActivity {

    private EditText mNome;
    private EditText mCognome;
    private EditText mNumero;
    private Button mBtnInsertData;
    private Button mBtnShowData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting obj
        mNome = findViewById(R.id.editNome);
        mCognome = findViewById(R.id.editCognome);
        mNumero = findViewById(R.id.editNumero);
        mBtnInsertData = findViewById(R.id.btn_insert);
        mBtnShowData = findViewById(R.id.btn_show);

        mBtnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();
                Toast.makeText(MainActivity.this, "Dati Inseriti Correttamente", Toast.LENGTH_SHORT).show();
            }
        });
        mBtnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showIntent = new Intent(MainActivity.this,ShowActivity.class);
                startActivity(showIntent);
            }
        });

    }

    public void InsertData(){

        ContentValues mContentValues = new ContentValues();
        mContentValues.put(Contract.ContractValues.COLUMN_NOME,mNome.getText().toString());
        mContentValues.put(Contract.ContractValues.COLUMN_COGNOME,mCognome.getText().toString());
        mContentValues.put(Contract.ContractValues.COLUMN_NUMERO,mNumero.getText().toString());
        Uri insert = getContentResolver().insert(Contract.URI_CONTENT,mContentValues);

    }

}
