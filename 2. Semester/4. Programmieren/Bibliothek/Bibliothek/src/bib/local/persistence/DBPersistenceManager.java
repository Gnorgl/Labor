package bib.local.persistence;

import java.io.IOException;

import bib.local.entities.Buch;

public class DBPersistenceManager implements PersistenceManager {

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Buch ladeBuch() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openForReading(String datenquelle) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void openForWriting(String datenquelle) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean speichereBuch(Buch b) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 *  Wenn später mal eine Kundenverwaltung ergänzt wird:

	@Override
	public Kunde ladeKunde() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean speichereKunde(Kunde k) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	*/
}
