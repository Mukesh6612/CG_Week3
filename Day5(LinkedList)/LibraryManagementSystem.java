class BookNode {
    String title, author, genre;
    int bookId;
    boolean isAvailable;
    BookNode next, prev;

    BookNode(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
    }
}

class LibraryDoublyLinkedList {
    private BookNode head;

    public void addAtBeginning(String title, String author, String genre, int id, boolean status) {
        BookNode newNode = new BookNode(title, author, genre, id, status);
        newNode.next = head;
        if (head != null) head.prev = newNode;
        head = newNode;
    }

    public void addAtEnd(String title, String author, String genre, int id, boolean status) {
        BookNode newNode = new BookNode(title, author, genre, id, status);
        if (head == null) {
            head = newNode;
            return;
        }
        BookNode current = head;
        while (current.next != null) current = current.next;
        current.next = newNode;
        newNode.prev = current;
    }

    public void addAtPosition(String title, String author, String genre, int id, boolean status, int pos) {
        if (pos <= 0) {
            addAtBeginning(title, author, genre, id, status);
            return;
        }
        BookNode newNode = new BookNode(title, author, genre, id, status);
        BookNode current = head;
        for (int i = 0; current != null && i < pos - 1; i++) current = current.next;
        if (current == null) {
            addAtEnd(title, author, genre, id, status);
            return;
        }
        newNode.next = current.next;
        if (current.next != null) current.next.prev = newNode;
        current.next = newNode;
        newNode.prev = current;
    }

    public void removeById(int id) {
        if (head == null) return;
        if (head.bookId == id) {
            head = head.next;
            if (head != null) head.prev = null;
            return;
        }
        BookNode current = head;
        while (current != null && current.bookId != id) current = current.next;
        if (current == null) return;
        if (current.prev != null) current.prev.next = current.next;
        if (current.next != null) current.next.prev = current.prev;
    }

    public void search(String keyword) {
        BookNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.title.equalsIgnoreCase(keyword) || current.author.equalsIgnoreCase(keyword)) {
                System.out.println("Found: " + current.title + " by " + current.author);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("Book not found.");
    }

    public void updateAvailability(int id, boolean status) {
        BookNode current = head;
        while (current != null) {
            if (current.bookId == id) {
                current.isAvailable = status;
                return;
            }
            current = current.next;
        }
    }

    public void displayForward() {
        BookNode current = head;
        System.out.println("Library Catalog (Forward):");
        while (current != null) {
            System.out.println(current.title + " | " + current.author + " | " + current.genre + " | ID: " + current.bookId + " | " + (current.isAvailable ? "Available" : "Checked Out"));
            current = current.next;
        }
    }

    public void displayReverse() {
        if (head == null) return;
        BookNode current = head;
        while (current.next != null) current = current.next;
        System.out.println("Library Catalog (Reverse):");
        while (current != null) {
            System.out.println(current.title + " | " + current.author + " | " + current.genre + " | ID: " + current.bookId + " | " + (current.isAvailable ? "Available" : "Checked Out"));
            current = current.prev;
        }
    }

    public int countBooks() {
        int count = 0;
        BookNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryDoublyLinkedList library = new LibraryDoublyLinkedList();

        library.addAtEnd("The Hobbit", "J.R.R. Tolkien", "Fantasy", 101, true);
        library.addAtBeginning("1984", "George Orwell", "Dystopian", 102, true);
        library.addAtPosition("To Kill a Mockingbird", "Harper Lee", "Classic", 103, false, 1);
        library.addAtEnd("Pride and Prejudice", "Jane Austen", "Romance", 104, true);
        library.addAtPosition("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 105, true, 2);

        library.displayForward();

        System.out.println("\nSearching for 'Harper Lee':");
        library.search("Harper Lee");

        System.out.println("\nUpdating availability of book ID 103:");
        library.updateAvailability(103, true);
        library.displayForward();

        System.out.println("\nRemoving book ID 102:");
        library.removeById(102);
        library.displayForward();

        System.out.println("\nLibrary in reverse order:");
        library.displayReverse();

        System.out.println("\nTotal number of books: " + library.countBooks());
    }
}
