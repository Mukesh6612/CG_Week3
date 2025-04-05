class ItemNode {
    String itemName;
    int itemId;
    int quantity;
    double price;
    ItemNode next;

    public ItemNode(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}
class InventoryLinkedList {
    private ItemNode head;

    public void addAtBeginning(String name, int id, int qty, double price) {
        ItemNode newNode = new ItemNode(name, id, qty, price);
        newNode.next = head;
        head = newNode;
    }
    public void addAtEnd(String name, int id, int qty, double price) {
        ItemNode newNode = new ItemNode(name, id, qty, price);
        if (head == null) {
            head = newNode;
            return;
        }
        ItemNode current = head;
        while (current.next != null)
            current = current.next;
        current.next = newNode;
    }
    public void addAtPosition(String name, int id, int qty, double price, int pos) {
        if (pos <= 0) {
            addAtBeginning(name, id, qty, price);
            return;
        }
        ItemNode newNode = new ItemNode(name, id, qty, price);
        ItemNode current = head;
        for (int i = 0; current != null && i < pos - 1; i++) {
            current = current.next;
        }
        if (current == null) {
            addAtEnd(name, id, qty, price);
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    public void removeById(int id) {
        if (head == null) return;
        if (head.itemId == id) {
            head = head.next;
            return;
        }
        ItemNode current = head;
        while (current.next != null && current.next.itemId != id)
            current = current.next;
        if (current.next != null)
            current.next = current.next.next;
    }
    public void updateQuantity(int id, int newQty) {
        ItemNode current = head;
        while (current != null) {
            if (current.itemId == id) {
                current.quantity = newQty;
                return;
            }
            current = current.next;
        }
    }
    public void search(String keyword) {
        ItemNode current = head;
        boolean found = false;
        while (current != null) {
            if (String.valueOf(current.itemId).equals(keyword) || current.itemName.equalsIgnoreCase(keyword)) {
                System.out.println("Found: " + current.itemName + " (ID: " + current.itemId + ")");
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("Item not found.");
    }

    public double calculateTotalValue() {
        double total = 0;
        ItemNode current = head;
        while (current != null) {
            total += current.quantity * current.price;
            current = current.next;
        }
        return total;
    }
    
    public void displayInventory() {
        ItemNode current = head;
        System.out.println("Inventory List:");
        while (current != null) {
            System.out.println("Name: " + current.itemName + ", ID: " + current.itemId +
                    ", Qty: " + current.quantity + ", Price: " + current.price);
            current = current.next;
        }
    }
}
public class InventoryManagementSystem {
    public static void main(String[] args) {
        InventoryLinkedList inventory = new InventoryLinkedList();

        inventory.addAtEnd("Mouse", 101, 50, 299.99);
        inventory.addAtBeginning("Keyboard", 102, 30, 499.99);
        inventory.addAtPosition("Monitor", 103, 10, 7999.50, 1);
        inventory.displayInventory();

        inventory.updateQuantity(101, 45);
        inventory.removeById(102);
        inventory.search("103");

        System.out.println("Total Inventory Value: ₹" + inventory.calculateTotalValue());
        inventory.displayInventory();
    }
}
