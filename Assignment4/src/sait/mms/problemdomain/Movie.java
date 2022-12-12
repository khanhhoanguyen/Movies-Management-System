package sait.mms.problemdomain;

/**
 * The master class of the movie object
 * 
 * @author Hoa Nguyen 
 * @author Miguel Mulingbayan
 *
 * @param id The movie's ID
 * @param duration The movie's duration
 * @param title The movie's title
 * @param year The year that the movie is released
 
 * 
 */
public class Movie {
	private int id;
	private int duration;
	private String title;
	private int year;

	/**
	 * The default constructor
	 * 
	 */
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * The movie constructor
	 * 
	 * @param id
	 * @param duration
	 * @param title
	 * @param year
	 */
	public Movie(int id, int duration, String title, int year) {
		super();
		this.id = id;
		this.duration = duration;
		this.title = title;
		this.year = year;
	}

	/**
	 * Gets the id of the movie
	 * 
	 * @return the id of the movie
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the movie
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the duration of the movie
	 * 
	 * @return the duration of the movie
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of the movie
	 * 
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Gets the title of the movie
	 * 
	 * @return the title of the movie
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the movie
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the year that the movie is released
	 * 
	 * @return the year that the movie is released
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year that the movie is released
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Format the output of the movie
	 */
	@Override
	public String toString() {
		return String.format("%-18s%d\n%-18s%d\n%-18s%s\n%-18s%d\n","ID:", id,"Duration:", duration, "Title:", title, "Year:", year);
	}

}
