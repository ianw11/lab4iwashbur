package edu.calpoly.android.lab4;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Class that provides helpful database table accessor variables and manages
 * basic required database functionality.  
 */
public class JokeTable {
	
	/** Joke table in the database. */
	public static final String DATABASE_TABLE_JOKE = "joke_table";
	
	/** Joke table column names and IDs for database access. */
	public static final String JOKE_KEY_ID = "_id";
	public static final int JOKE_COL_ID = 0;
	
	public static final String JOKE_KEY_TEXT = "joke_text";
	public static final int JOKE_COL_TEXT = JOKE_COL_ID + 1;
	
	public static final String JOKE_KEY_RATING = "rating";
	public static final int JOKE_COL_RATING = JOKE_COL_ID + 2;
	
	public static final String JOKE_KEY_AUTHOR = "author";
	public static final int JOKE_COL_AUTHOR = JOKE_COL_ID + 3;
	
	/** SQLite database creation statement. Auto-increments IDs of inserted jokes.
	 * Joke IDs are set after insertion into the database. */
	public static final String DATABASE_CREATE = "create table " + DATABASE_TABLE_JOKE + " (" + 
			JOKE_KEY_ID + " integer primary key autoincrement, " + 
			JOKE_KEY_TEXT	+ " text not null, " + 
			JOKE_KEY_RATING	+ " integer not null, " + 
			JOKE_KEY_AUTHOR + " text not null);";
	
	/** SQLite database table removal statement. Only used if upgrading database. */
	public static final String DATABASE_DROP = "drop table if exists " + DATABASE_TABLE_JOKE;
	
	/**
	 * Initializes the database.
	 * 
	 * @param database
	 * 				The database to initialize.	
	 */
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	
	/**
	 * Upgrades the database to a new version.
	 * 
	 * @param database
	 * 					The database to upgrade.
	 * @param oldVersion
	 * 					The old version of the database.
	 * @param newVersion
	 * 					The new version of the database.
	 */
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(JokeTable.class.getName(), "Database being upgraded from " + oldVersion + " to " + newVersion);
		database.execSQL(DATABASE_DROP);
		onCreate(database);
	}
}
