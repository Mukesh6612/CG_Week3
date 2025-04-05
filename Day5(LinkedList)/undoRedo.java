class TextState {
    String content;
    TextState prev, next;

    public TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

class TextEditorHistory {
    private TextState head = null;
    private TextState current = null;
    private int size = 0;
    private final int MAX_HISTORY = 10;
    public void type(String newText) {
        TextState newState = new TextState(newText);

        if (current != null) {
            current.next = null;
        }

        newState.prev = current;

        if (current != null) {
            current.next = newState;
        } else {
            head = newState;
        }

        current = newState;
        size++;
        if (size > MAX_HISTORY) {
            head = head.next;
            if (head != null) head.prev = null;
            size--;
        }
        System.out.println("New state added.");
    }
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo performed.");
        } else {
            System.out.println("Cannot undo further.");
        }
    }
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo performed.");
        } else {
            System.out.println("Cannot redo further.");
        }
    }
    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.content);
        } else {
            System.out.println("No text history.");
        }
    }
    public void printHistory() {
        TextState temp = head;
        System.out.println("All States in History:");
        while (temp != null) {
            System.out.println((temp == current ? "-> " : "   ") + temp.content);
            temp = temp.next;
        }
    }
}
public class undoRedo {
    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();

        editor.type("Hello");
        editor.type("Hello, World");
        editor.type("Hello, World!");
        editor.displayCurrentState();

        editor.undo();
        editor.displayCurrentState();

        editor.redo();
        editor.displayCurrentState();

        editor.undo();
        editor.undo();
        editor.displayCurrentState();

        // Exceed history limit
        for (int i = 1; i <= 10; i++) {
            editor.type("Edit " + i);
        }
        editor.printHistory(); // Only last 10 edits should remain
    }
}

