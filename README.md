# Movie Database App

## Overview

The Movie Database App is an Android application that displays a list of movies with their details such as title, year, genre, and poster. The app allows users to toggle the visibility of movies with missing information.

## Features

- Display a list of movies with title, year, genre, and poster.
- Toggle visibility of movies with missing information.
- Load movie data from a JSON file stored in the assets folder.
- Gracefully handles missing or mis-formatted data in the json file.

## Project Structure

- `app/src/main/java/com/main/moviedatabase/`
  - `MainActivity.java`: The main activity that initializes the UI and handles user interactions.
  - `Movie.java`: A model class representing a movie.
  - `MovieAdapter.java`: An adapter class for binding movie data to the RecyclerView.
  - `MovieLoading.java`: A class for loading and parsing movie data from a JSON file.

- `app/src/main/res/layout/`
  - `activity_main.xml`: Layout file for the main activity.
  - `movie_item.xml`: Layout file for individual movie items in the RecyclerView.

- `app/src/main/res/values/`
  - `colors.xml`: Defines color resources used in the app.
  - `themes.xml`: Defines the app's theme.

- `app/src/main/res/drawable/`
  - `ic_launcher_background.xml`: Background vector drawable for the app icon.
  - `ic_launcher_foreground_alt.xml`: Foreground vector drawable for the app icon.

- `app/src/main/assets/`
  - `movies.json`: JSON file containing movie data.

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/hamidurrk/movie-database-app.git
   ```
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

## Usage
1. Launch the app.
2. The main screen displays a list of movies.
3. Use the "Hide Empty" switch to toggle the visibility of movies with missing information.

## JSON Data Format
The `movies.json` file in the `assets` directory should follow this format:
```sh
[
  {
    "title": "Movie Title",
    "year": 2021,
    "genre": "Genre",
    "poster": "poster_resource_name"
  },
  ...
]
```
## Code Explanation
### MainActivity.java
- Initializes the UI components and sets up the RecyclerView.
- Handles the "Hide Empty" switch to reload movies based on the toggle state.
### Movie.java
- Represents a movie with attributes: title, year, genre, and poster.
- Provides methods to get default values for missing information.
### MovieAdapter.java
- Binds movie data to the RecyclerView.
- Handles the visibility of movie details based on the "Hide Empty" switch.
### MovieLoading.java
- Loads and parses movie data from the movies.json file.
- Provides a method to hide movies with missing information.
