

class Movie{
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    public Movie(String title,String director,int yearOfRelease,double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieList{
    Movie head = null;
    Movie tail = null;

    void addAtBeginning(String title, String director, int year, double rating){
        Movie newMovie = new Movie(title,director,year,rating);
        if (head == null){
            head = tail = newMovie;
        }else{
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos == 0) {
            addAtBeginning(title, director, year, rating);
            return;
        }

        Movie newMovie = new Movie(title, director, year, rating);
        Movie temp = head;
        int count = 0;

        while (temp != null && count < pos - 1) {
            temp = temp.next;
            count++;
        }

        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
        } else {
            newMovie.next = temp.next;
            newMovie.prev = temp;
            temp.next.prev = newMovie;
            temp.next = newMovie;
        }
    }
    void removeByTitle(String title) {
        Movie temp = head;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                if (temp == head && temp == tail) {
                    head = tail = null;
                } else if (temp == head) {
                    head = head.next;
                    head.prev = null;
                } else if (temp == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                System.out.println("Movie \"" + title + "\" removed.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie \"" + title + "\" not found.");
    }
    void searchByDirector(String director) {
        boolean found = false;
        Movie temp = head;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println("Found: " + temp.title + " (" + temp.year + ") - Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found directed by \"" + director + "\".");
    }

    void searchByRating(double rating) {
        boolean found = false;
        Movie temp = head;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println("Found: " + temp.title + " directed by " + temp.director + " (" + temp.year + ")");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found with rating " + rating + ".");
    }

    void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated for \"" + title + "\" to " + newRating);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie \"" + title + "\" not found.");
    }

    void displayForward() {
        Movie temp = head;
        if (temp == null) {
            System.out.println("No movies to display.");
            return;
        }
        System.out.println("Movies (Forward):");
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Movie temp = tail;
        if (temp == null) {
            System.out.println("No movies to display.");
            return;
        }
        System.out.println("Movies (Reverse):");
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | Rating: " + temp.rating);
            temp = temp.prev;
        }
    }
    

}

class MovieManagementSystem{
    public static void main(String[] args) {
        MovieList ml = new MovieList();
        ml.addAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        ml.addAtEnd("Interstellar", "Christopher Nolan", 2014, 8.6);
        ml.addAtBeginning("The Godfather", "Francis Ford Coppola", 1972, 9.2);
        ml.addAtPosition(1, "Parasite", "Bong Joon-ho", 2019, 8.6);
        ml.displayForward();
        System.out.println();
        ml.displayReverse();
        System.out.println();
        ml.searchByDirector("Christopher Nolan");
        ml.searchByRating(8.6);
        ml.updateRating("Parasite", 9.0);
        ml.removeByTitle("Inception");
        ml.displayForward();
    }
}