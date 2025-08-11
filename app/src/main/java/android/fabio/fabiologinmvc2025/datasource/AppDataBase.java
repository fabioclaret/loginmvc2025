package android.fabio.fabiologinmvc2025.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.fabio.fabiologinmvc2025.datamodel.UsuarioDataModel;

import androidx.annotation.Nullable;

public class AppDataBase extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;
    public static final String NAME = "app.sqlite";
    public static int version = 1;
    public AppDataBase( Context context ) {
        super(context, NAME, null, version);
        sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UsuarioDataModel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(String tabela, ContentValues dados) {
        boolean retorno = false;
        try {
            retorno = sqLiteDatabase.insert(tabela, null, dados) > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return retorno;
    }

    public boolean checkUserPass(String email, String senha) {

        sqLiteDatabase=getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM usuario WHERE email = ? AND senha = ?",
                new String[]{email, senha});
        return cursor.getCount() > 0;
    }

    public boolean checkUser(String email ) {
        sqLiteDatabase=getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM usuario WHERE email = ?",
                new String[]{email} );
        return cursor.getCount() >0;
    }
}
