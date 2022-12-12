package sait.mms.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import sait.mms.contracts.DatabaseDriver;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;
/**
 * Assignment 4 - Movie Management System
 * 
 * @author Hoa Nguyen 
 * @author Miguel Mulingbayan
 *
 *	The MovieManagementSystem Class contains all the methods that will work with the movies.sql data
 */
public class MovieManagementSystem {
	
	

	private DatabaseDriver db;
	private Scanner in;
	/**
	 * The MovieManagementSystem method will connect to MariaDBDriver and shows the display menu
	 * 
	 * @throws SQLException
	 */
	public MovieManagementSystem() throws SQLException
	{
		in = new Scanner(System.in);
		db = new MariaDBDriver();
		db.connect();
		displayMenu();
	}
	/**
	 * This method shows the menu of the possible actions that can be done in the program
	 * 
	 * @throws SQLException
	 */
	public void displayMenu() throws SQLException{
		/**
		 * The option is based on which action we want to do in the program
		 * 
		 * @param option Our choice of action
		 * 
		 */
		int option = 0;
		
		/**
		 * This statement will base our option in which action to execute
		 * 
		 * 1 - Creates a new record representing a movie
		 * 2 - Retrieves records with movies released in a specific year
		 * 3 - Retrieves records with a list of random movies
		 * 4 - Delete a movie using its id
		 * 5 - Exit
		 * 
		 */
		while (option != 5) {
			
			System.out.println("Jim's Movie Manager");
			System.out.println("1\tCreates a new record representing a movie");
			System.out.println("2\tRetrieves records with movies released in a specific year");
			System.out.println("3\tRetrieves records with a list of random movies");
			System.out.println("4\tDelete a movie using its id");
			System.out.println("5\tExit the program");
			

			System.out.print("\nEnter option: ");
			option = in.nextInt();
			System.out.println();

			switch (option) {
				case 1: {
					addMovie();
					break;
				}
				case 2: {
					printMoviesInYear();
					break;
				}
				case 3: {
					printRandomMovies();
					break;
				}
				case 4: {
					deleteMovie();
					break;
				}
				case 5: {
					System.out.println("Goodbye!");
					System.exit(0);
				}
				default: {
					System.out.println("Invalid option!");
					break;
				}
			}
		}
	}
	
	/**
	 * This method will add a movie into movies.sql
	 * 
	 * @throws SQLException
	 */
	public void addMovie() throws SQLException {
		
		//This statement will ask the user to enter the title of the movie
		System.out.print("Please enter the title of the movie: ");
		in.nextLine();
		String title = in.nextLine();
		
		//This statement will ask the user to enter the duration of the movie
		System.out.print("Please enter the duration of the movie: ");
		int duration= in.nextInt();
		
		//This statement will ask the user to enter the year of the movie
		System.out.print("Please enter the year of the movie: ");
		int year= in.nextInt();
		
		//This statement will insert the information of the movie into movies.sql
		String queryString = "insert into movies ( duration, title, year) values("+duration+",'"+title+"',"+year+")";
		int rows = db.update(queryString);
		
		//This statement will notify that the movie is added
		System.out.println("Added movie to database.");
		System.out.println();
	
		
	}
	
	/**
	 * This method will print movies in years from movies.sql
	 * 
	 * @throws SQLException
	 */
	public void printMoviesInYear() throws SQLException {
		
		int sum = 0;
		
		//This statement will ask the user to enter the year of the movie he/she wants to print
		System.out.print("Please enter the year of the movie you want to print: ");
		int year= in.nextInt();
		
		System.out.println("Movie List");
		System.out.printf("%-15s %-10s %-40s \n","Duration", "Year", "Title");
		
		//This statement will find the movies by year from movies.sql
		String queryString = "Select * from movies where year = "+year+"";
		ResultSet rSet=db.get(queryString);
		
		
		//This statement will display the movies 
		while(rSet.next())
		{
			//Movie movie = new Movie(rSet.getInt(1), rSet.getInt(2),rSet.getString(3), rSet.getInt(4));
			//System.out.println(movie);
			
			System.out.printf("%-15d %-10d %-40s \n",rSet.getInt(2), 
					rSet.getInt(4),rSet.getString(3));
			
			//This statement will calculate the sum of duration
			sum+=rSet.getInt(2);
			
		}
	
		System.out.println();
		//This statement will print the sum of duration
		System.out.println("Total duration: "+sum+" minutes");
		
		rSet.close();
		System.out.println();
		
	}
	
	/**
	 * This method will print random movies from movies.sql
	 * 
	 * @throws SQLException
	 */
	public void printRandomMovies() throws SQLException {
		
		int sum = 0;
		
		Random random = new Random();
		
		//This statement will ask the user to enter the number of the random movies he/she wants to print
		System.out.print("Enter number of random movies: ");
		int numRanMovies = in.nextInt();
		
		System.out.println("Movie List");
		System.out.printf("%-15s %-10s %-40s \n","Duration", "Year", "Title");
		
		//This statement will create random indexes and print out the movies
		for (int k = 0; k < numRanMovies; k++) {
			int ranIndex = random.nextInt(500);
			String queryString = "Select * from movies where id = "+ranIndex+"";
			
			ResultSet rSet=db.get(queryString);
			while(rSet.next())
			{
				//Movie movie = new Movie(rSet.getInt(1), rSet.getInt(2),rSet.getString(3), rSet.getInt(4));
				//System.out.println(movie);
				
				System.out.printf("%-15d %-10d %-40s \n",rSet.getInt(2), 
						rSet.getInt(4),rSet.getString(3));
				
				//This statement will calculate the sum of duration
				sum+=rSet.getInt(2);
			}
			
			rSet.close();
		}
		System.out.println();
		//This statement will print the sum of duration
		System.out.println("Total duration: "+sum+" minutes");
		System.out.println();
		
	}
	
	/**
	 * This method will delete a movie from movies.sql
	 * 
	 * @throws SQLException
	 */
	public void deleteMovie() throws SQLException {
		
		//This statement will ask the user to enter the id of the movie he/she wants to delete
		System.out.print("Please enter the movie ID that you want to delete: ");
		int id = in.nextInt();
		
		//This statement will delete the movies by id from movies.sql
		String queryString  ="Delete from movies where id= "+id+"";
		int rows = db.update(queryString);
		
		//This statement will notify the movie is deleted
		System.out.println();
		System.out.println(" Movie " +id+" is deleted.");
		System.out.println();
	}
}
