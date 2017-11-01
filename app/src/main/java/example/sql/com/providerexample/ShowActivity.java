package example.sql.com.providerexample;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import example.sql.com.providerexample.Database.Contract;
import example.sql.com.providerexample.Database.CursorAdapter;

public class ShowActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private ListView mList;
    CursorAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        mList = findViewById(R.id.showData);
        mAdapter = new CursorAdapter(this,null);
        mList.setAdapter(mAdapter);
        getLoaderManager().initLoader(0,null,this);

    }


    public void DeleteDatabase(){
        getContentResolver().delete(Contract.URI_CONTENT,null,null);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteMenu:
            DeleteDatabase();
            Toast.makeText(this, "Cancellati", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projector = {
                Contract.ContractValues._ID,
                Contract.ContractValues.COLUMN_NOME,
                Contract.ContractValues.COLUMN_COGNOME,
                Contract.ContractValues.COLUMN_NUMERO
        };


            return new CursorLoader(getApplicationContext(),Contract.URI_CONTENT,projector,null,null,null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
