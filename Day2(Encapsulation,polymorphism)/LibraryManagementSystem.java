interface Reservable {
    void reserveItem();
    boolean checkAvailability();
}

abstract class LibraryItem {
    private String itemId;
    String title;
    private String author;

    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }

    public String getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }

    public abstract int getLoanDuration();

    public void getItemDetails() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
    }
}

class Book extends LibraryItem implements Reservable {
    private boolean isAvailable;

    public Book(String itemId, String title, String author) {
        super(itemId, title, author);
        this.isAvailable = true;
    }

    public int getLoanDuration() {
        return 14; 
    }

    public void reserveItem() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been reserved.");
        } else {
            System.out.println(title + " is not available for reservation.");
        }
    }

    public boolean checkAvailability() {
        return isAvailable;
    }
}

class Magazine extends LibraryItem implements Reservable {
    private boolean isAvailable;

    public Magazine(String itemId, String title, String author) {
        super(itemId, title, author);
        this.isAvailable = true;
    }

    public int getLoanDuration() {
        return 7;
    }

    public void reserveItem() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been reserved.");
        } else {
            System.out.println(title + " is not available for reservation.");
        }
    }

    public boolean checkAvailability() {
        return isAvailable;
    }
}

class DVD extends LibraryItem implements Reservable {
    private boolean isAvailable;

    public DVD(String itemId, String title, String author) {
        super(itemId, title, author);
        this.isAvailable = true;
    }

    public int getLoanDuration() {
        return 3;
    }

    public void reserveItem() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been reserved.");
        } else {
            System.out.println(title + " is not available for reservation.");
        }
    }

    public boolean checkAvailability() {
        return isAvailable;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryItem book = new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald");
        LibraryItem magazine = new Magazine("M001", "National Geographic", "Various Authors");
        LibraryItem dvd = new DVD("D001", "Inception", "Christopher Nolan");

        LibraryItem[] items = {book, magazine, dvd};

        for (LibraryItem item : items) {
            item.getItemDetails();
            int loanDuration = item.getLoanDuration();
            System.out.println("Loan Duration: " + loanDuration + " days");

            if (item instanceof Reservable) {
                Reservable r = (Reservable) item;
                if (r.checkAvailability()) {
                    r.reserveItem();
                } else {
                    System.out.println(item.getTitle() + " is already reserved.");
                }
            }

            System.out.println("-------------------------");
        }
    }
}
