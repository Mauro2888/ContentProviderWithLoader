package example.sql.com.providerexample.Database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Msi-Locale on 01/11/2017.
 */

public class Provider extends ContentProvider {

    private static final int ROWS = 100;
    private static final int ROW_ID = 101;

    Helper mHelper;
    private final static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI(Contract.AUTHORITY,Contract.PATH + "/#",ROW_ID);
        mUriMatcher.addURI(Contract.AUTHORITY,Contract.PATH,ROWS);
    }

    @Override
    public boolean onCreate() {
        mHelper = new Helper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase mSqLite = mHelper.getReadableDatabase();
        Cursor mCursor;
        int match = mUriMatcher.match(uri);
        switch (match){
            case ROWS:
                mCursor = mSqLite.query(Contract.ContractValues.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case ROW_ID:
                selection = Contract.ContractValues._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                mCursor = mSqLite.query(Contract.ContractValues.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
                default:
                    throw new IllegalArgumentException("Errore Query " + uri);
        }
        mCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return mCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase mSqLite = mHelper.getWritableDatabase();
        int match = mUriMatcher.match(uri);
        long ID;
        Uri uriReturn;
        switch (match){
            case ROWS:
                ID = mSqLite.insertOrThrow(Contract.ContractValues.TABLE_NAME,null,contentValues);
                if (ID > 0){
                    uriReturn = ContentUris.withAppendedId(uri,ID);
                }else {
                    throw new IllegalArgumentException("Errore " + uri);
                }
                break;
                default:
                    throw new IllegalArgumentException("Errore Insert " +uri);
        }
        return uriReturn;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase mSqLite = mHelper.getWritableDatabase();
        int match = mUriMatcher.match(uri);
        int ID;
        switch (match){
            case ROWS:
                ID = mSqLite.delete(Contract.ContractValues.TABLE_NAME,s,strings);
                break;
            case ROW_ID:
                s = Contract.ContractValues._ID + " =?";
                strings = new String[]{String.valueOf(ContentUris.parseId(uri))};
                ID = mSqLite.delete(Contract.ContractValues.TABLE_NAME,s,strings);
                break;
                default:
                    throw new IllegalArgumentException("Errore delete_menu " + uri);
        }
        if (ID != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return ID;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
