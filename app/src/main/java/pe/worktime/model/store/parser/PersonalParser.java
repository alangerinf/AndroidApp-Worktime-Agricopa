package pe.worktime.model.store.parser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Personal;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class PersonalParser extends AbstractParserRS<Personal> {
    public PersonalParser(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void insert(SQLiteDatabase db, Personal entity) throws Exception {
        ContentValues registro = new ContentValues();

        registro.put("dni", entity.getDni());
        registro.put("nombreApellido", entity.getNombreApellido());

        db.insert(getTableName(), null, registro);
    }

    @Override
    public void insertWithParent(SQLiteDatabase db, Personal entity) throws Exception {
        throw new Exception("No Implement Exception");
    }

    @Override
    public void update(SQLiteDatabase db, Personal entity) throws Exception {
        ContentValues registro = new ContentValues();

        registro.put("nombreApellido", entity.getNombreApellido());


        String[] whereArgs = {
                entity.getDni()};

        db.update(getTableName(), registro, " dni = ? ", whereArgs);
    }

    @Override
    public void delete(SQLiteDatabase db, Personal entity) throws Exception {
        String[] whereArgs = {
                entity.getDni()};
        db.delete(getTableName(), " dni = ? ", whereArgs);
    }

    @Override
    public Personal read(Cursor c) throws Exception {
        Personal obj = new Personal();


        obj.setDni(c.getString(c.getColumnIndex("dni")));
        obj.setNombreApellido(c.getString(c.getColumnIndex("nombreApellido")));

        return obj;
    }

    @Override
    protected String getSqlCreate() {
        StringBuffer bufer = new StringBuffer();
        bufer.append("CREATE TABLE ");
        bufer.append(this.getTableName());
        bufer.append("( ");
        {
            bufer.append("dni TEXT, ");
            bufer.append("nombreApellido TEXT ");

        }
        bufer.append(" );");
        return bufer.toString();
    }

    @Override
    protected String getTableName() {
        return "Personal";
    }

    @Override
    public List<Personal> list(SQLiteDatabase db, Empresa entity) throws Exception {
        throw new Exception("No Implement Exception");
    }

    @Override
    public Personal buscar(SQLiteDatabase db, String f) throws Exception {
        sqlSelect = "SELECT * FROM Personal WHERE dni = '"+f+"'";
        Cursor c = db.rawQuery(sqlSelect, null);
        Personal result = new Personal();
        result.setNombreApellido("--");

        if (c.moveToFirst()) {
            do {
                result = read(c);
            } while (c.moveToNext());
        }
        c.close();
        return result;
    }
}
