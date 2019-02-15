package controller;

import model.Database;
import model.Student;
import model.StudentDatabaseManager;
import view.View;

public class App {
	
	// DEFAULT APP CONFIG
	public static String tableName 		= "students";
	public static String inputFilePath 	= "C:\\Users\\Nathaniel Saludes\\Desktop\\testInputFile.txt";
	public static String logFilePath	= "C:\\Users\\Nathaniel Saludes\\Desktop\\testLogFile.txt";
	
	private static String JDBC_DRIVER	= "com.mysql.jdbc.Driver";
	private static String DB_NAME		= "iacademy";
	private static String DB_URL		= "jdbc:mysql://localhost:3306/" + DB_NAME;
	
	
	public static void main(String[] args) {
				
		// MAIN ARGUMENTS
		if(args.length == 3) {
			tableName 		= args[0];
			inputFilePath 	= args[1];
			logFilePath 	= args[2];			
		} else {
			new View().printDefaultAppConfig(tableName, inputFilePath, logFilePath);
		}
		
		StudentDatabaseManager DatabaseManager = createDatabaseConnection();
		
		if(DatabaseManager.hasValidConnection()) {
			readCommandFile(DatabaseManager);			
		}
		
//		readCommandFile(null);
	}


	// ===========================================================================================================================================================
	// START STUDENT DATABASE OPERATIONS MANAGER
	public static StudentDatabaseManager createDatabaseConnection() {
		StudentDatabaseManager database = new StudentDatabaseManager(
				JDBC_DRIVER,
				DB_NAME, 
				DB_URL, 
				tableName
		);
		
		return database;
	}

	// READ INPUT COMMAND FILE
	public static void readCommandFile(StudentDatabaseManager DatabaseManager) {
		InputCommandFileReader inputReader = new InputCommandFileReader(inputFilePath, DatabaseManager);
	}
}
