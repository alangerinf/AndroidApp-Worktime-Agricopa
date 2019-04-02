package pe.worktime.model.store;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import pe.worktime.model.entity.Empresa;
import pe.worktime.model.store.parser.util.AbstractParserRS;
import pe.worktime.model.store.parser.util.IFilterRS;

public abstract class RSAbstract<E> {

	protected SQLiteDatabase db;
	protected AbstractParserRS<E> parser;

	public RSAbstract() {
		super();
	}

	public void insert(E entity) throws Exception {
		parser.insert(db, entity);
	}
	
	public void insertWithParent(E entity) throws Exception {
		parser.insertWithParent(db, entity);
	}
		
	public void update(E entity) throws Exception {
		parser.update(db, entity);
	}

	public void delete(E entity) throws Exception {
		parser.delete(db, entity);
	}

	public List<E> list() throws Exception {
		return parser.list(db);
	}
	
	public List<E> listDeEmpresa(Empresa entity) throws Exception{
		return parser.list(db, entity);
	}
	

	public List<E> filter(IFilterRS sqlFilter) throws Exception {
		return parser.filter(db, sqlFilter);
	}
	
	public void cleanData() throws Exception {
		parser.cleanData(db);
	}
	
	public void closeDB(){
		parser.close();
		db.close();
	}

	public E find(String f) throws Exception {
		return parser.buscar(db,f);
	}

}