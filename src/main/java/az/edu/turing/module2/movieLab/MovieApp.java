package az.edu.turing.module2.movieLab;

import java.util.Scanner;

public class MovieApp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Movie[] movies = new Movie[0];
        while (true) {
            System.out.println("Press 1 to create movie : ");
            System.out.println("Press 2 to print movie : ");
            System.out.println("Press 3 to input more movies : ");
            System.out.println("Press 4 to find statistics : ");
            System.out.println("Press 5 to search for a movie :");
            System.out.println("Press 6 to update movie ratings :");
            System.out.println("Press 7 to delete movie :");
            System.out.println("Press 8 to sort movies : ");
            System.out.println("Press 0 to exit : ");
            int input = scan.nextInt();
            switch (input) {
                case 1:
                    movies = createMovieArray(movies, 3, scan);
                    break;
                case 2:
                    printMovie(movies);
                    break;
                case 3:
                    movies = createMovieArray(movies, scan.nextInt(), scan);
                    break;
                case 4:
                    System.out.println("Average rating : " + averageMovieRating(movies));
                    System.out.println("Max rating : " + maxMovieRating(movies));
                    System.out.println("Min rating : " + minMovieRating(movies));
                    break;
                case 5:
                    System.out.println("Enter the movie you are search for");
                    int index5 = searchMovie(movies, scan);
                    if (index5 != -1) System.out.println(movies[index5].getName() + "  " + movies[index5].getRating());
                    else System.out.println("Movie not found.");
                    break;
                case 6:
                    int index6 = searchMovie(movies, scan);
                    movies[index6].setRating(scan.nextDouble());
                    break;
                case 7:
                    movies = deleteMovie(movies, searchMovie(movies, scan));
                    break;
                case 8:
                    sortMovie(movies);
                    break;
                case 0:
                    return;
            }
        }
    }

    private static Movie[] deleteMovie(Movie[] movies, int index) {
        Movie[] newArray = new Movie[movies.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i < index) {
                newArray[i] = movies[i];
            } else {
                newArray[i] = movies[i + 1];
            }
        }
        return newArray;
    }

    private static void sortMovie(Movie[] movies) {
        for (int i = 0; i < movies.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < movies.length; j++) {
                if (movies[maxIndex].getRating() < movies[j].getRating()) {
                    maxIndex = j;
                }
            }
            Movie temp = movies[maxIndex];
            movies[maxIndex] = movies[i];
            movies[i] = temp;
        }
    }

    private static int searchMovie(Movie[] movies, Scanner scan) {
        String name = scan.next();
        int index = -1;
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }

    private static double minMovieRating(Movie[] movies) {
        double min = movies[0].getRating();
        for (Movie movie : movies) {
            if (min > movie.getRating()) {
                min = movie.getRating();
            }
        }
        return min;
    }

    private static double maxMovieRating(Movie[] movies) {
        double max = movies[0].getRating();
        for (Movie movie : movies) {
            if (max < movie.getRating()) {
                max = movie.getRating();
            }
        }
        return max;
    }

    private static double averageMovieRating(Movie[] movies) {
        double average = 0;
        for (Movie rating : movies) {
            average += rating.getRating();
        }
        return average / movies.length;
    }

    private static Movie[] createMovieArray(Movie[] movies, int n, Scanner scan) {
        Movie[] newArray = new Movie[movies.length + n];
        for (int i = 0; i < newArray.length; i++) {
            if (i < movies.length) {
                newArray[i] = movies[i];
            } else {
                System.out.println("Movie name : ");
                String name = scan.next();
                System.out.println("Movie rating : ");
                double rating = scan.nextDouble();
                newArray[i] = new Movie(name, rating);
            }
        }
        return newArray;
    }

    private static void printMovie(Movie[] movies) {
        for (int i = 0; i < movies.length; i++) {
            System.out.println("Movie-" + (i + 1) + " : " + movies[i].getName() + "  " + movies[i].getRating());
        }
    }
}
