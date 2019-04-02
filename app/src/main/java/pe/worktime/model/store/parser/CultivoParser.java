package pe.worktime.model.store.parser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import pe.worktime.model.entity.Cultivo;
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class CultivoParser extends AbstractParserRS<Cultivo> {
    public CultivoParser(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void insert(SQLiteDatabase db, Cultivo entity) throws Exception {
        ContentValues registro = new ContentValues();

        registro.put("codCultivo", entity.getCodCultivo());
        registro.put("cultivo", entity.getCultivo());
        registro.put("fcodCultivo", entity.getFcodCultivo());

        db.insert(getTableName(), null, registro);
    }

    @Override
    public void insertWithParent(SQLiteDatabase db, Cultivo entity) throws Exception {
        throw new Exception("No Implement Exception");
    }

    @Override
    public void update(SQLiteDatabase db, Cultivo entity) throws Exception {
        ContentValues registro = new ContentValues();

        registro.put("cultivo", entity.getCultivo());


        String[] whereArgs = {
                entity.getCodCultivo()};

        db.update(getTableName(), registro, " codCultivo = ? ", whereArgs);
    }

    @Override
    public void delete(SQLiteDatabase db, Cultivo entity) throws Exception {
        String[] whereArgs = {
                entity.getCodCultivo()};
        db.delete(getTableName(), " codCultivo = ? ", whereArgs);
    }

    @Override
    public Cultivo read(Cursor c) throws Exception {
        Cultivo obj = new Cultivo();

        //obj.setCodActividad(c.getString(c.getColumnIndex("codEmpresa")));
        obj.setCodCultivo(c.getString(c.getColumnIndex("codCultivo")));
        obj.setCultivo(c.getString(c.getColumnIndex("cultivo")));
        obj.setFcodCultivo(c.getString(c.getColumnIndex("fcodCultivo")));

        return obj;
    }

    @Override
    protected String getSqlCreate() {
        StringBuffer bufer = new StringBuffer();
        bufer.append("CREATE TABLE ");
        bufer.append(this.getTableName());
        bufer.append("( ");
        {
            bufer.append("codCultivo TEXT, ");
            bufer.append("cultivo TEXT, ");
            bufer.append("fcodCultivo TEXT ");

        }
        bufer.append(" );");
        return bufer.toString();
    }

    @Override
    protected String getTableName() {
        return "Cultivo";
    }

    @Override
    public List<Cultivo> list(SQLiteDatabase db, Empresa entity) throws Exception {
        throw new Exception("No Implement Exception");
    }

    @Override
    public Cultivo buscar(SQLiteDatabase db, String f) throws Exception {
        return null;
    }
}
