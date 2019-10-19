package com.moviePlateform;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class MovieInstance {
	
	UserInstance user = new UserInstance();
	

	// Name of the Movie
	String movieName;
	
	// year of movie release
	Integer year;
	
	//Genre of Movie
	String genre;
	
	// Map to store movie name to [Year, Viewer rating, Critic rating] 
	static HashMap<String, LinkedList<Integer>> movieRatings = new HashMap<>();
	
	// Map to store <Year of release, list of movies> 
	static HashMap<Integer, LinkedList<String>> yrMovieMap = new HashMap<>();
	
	// Map to store <Genre, list of movies>
	static HashMap<String, LinkedList<String>> genreMovieMap = new HashMap<>();
	
	
	public void addMovie(String movieName, String genre, int year) {
		if ( movieName != null && genre != null && year != 0 ) {
			
			// type and constraint checks for the variables
			
			this.movieName = movieName;
			this.year = year;
			this.genre = genre;
			
			updateYrMovieMap();
			updateGenreMovieMap();
			updateMovieRating();
		}
	}

	private void updateMovieRating() {
		// Add moviename and year but no ratings
		LinkedList<Integer> tempList = new LinkedList<>();
		tempList.add(year);
		tempList.add(0);
		tempList.add(0);
		movieRatings.put(movieName, tempList);
	}

	private void updateGenreMovieMap() {
		if( yrMovieMap.containsKey(year) == false ) {
			// Add new movie list if year is not in our hashmap 
			LinkedList<String> newList = new LinkedList<String>();
			newList.add(movieName);
			yrMovieMap.put(year, newList);
		} else if ( yrMovieMap.get(year).contains(movieName) == false) {
			// Add new movie if movie is not already registered in that year
			yrMovieMap.get(year).add(movieName);
		}
	}

	private void updateYrMovieMap() {
		if( genreMovieMap.containsKey(genre) == false ) {
			// Add new movie list if year is not in our hashmap 
			LinkedList<String> newList = new LinkedList<String>();
			newList.add(movieName);
			genreMovieMap.put(genre, newList);
		} else if ( genreMovieMap.get(genre).contains(movieName) == false) {
			// Add new movie if movie is not already registered in that year
			genreMovieMap.get(genre).add(movieName);
		}
	}
	
	public void addReview(String userName, String movieName, int rating) {
		
		
		if (movieRatings.containsKey(movieName) && movieRatings.get(movieName).get(0) <= 2018) {
			if (UserInstance.userMap.get(userName).equals("Critic")) {
				// If user is a critic, double the rating
				rating = rating * 2;
				// Add critic rating
				movieRatings.get(movieName).add(2, rating);
			} else {
				// Add viewer rating
				movieRatings.get(movieName).add(1, rating);
			}
		}
	}
}
