class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    public Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}
class TaskScheduler {
    private Task head = null;
    private Task tail = null;
    private Task current = null;
    public void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }
    public void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            tail.next = newTask;
            newTask.next = head;
            tail = newTask;
        }
    }
    public void addAtPosition(int pos, int id, String name, int priority, String dueDate) {
        if (pos == 0 || head == null) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }

        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        int count = 0;

        while (count < pos - 1 && temp.next != head) {
            temp = temp.next;
            count++;
        }

        newTask.next = temp.next;
        temp.next = newTask;

        if (temp == tail) {
            tail = newTask;
        }
    }
    public void removeById(int id) {
        if (head == null) {
            System.out.println("No tasks to remove.");
            return;
        }

        Task temp = head;
        Task prev = tail;

        do {
            if (temp.id == id) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                System.out.println("Task with ID " + id + " removed.");
                if (temp == current) current = current.next;
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task with ID " + id + " not found.");
    }
    public void viewCurrentTaskAndMoveNext() {
        if (current == null) {
            if (head == null) {
                System.out.println("No tasks scheduled.");
                return;
            }
            current = head;
        }

        System.out.println("Current Task:");
        System.out.println("ID: " + current.id + ", Name: " + current.name + ", Priority: " + current.priority + ", Due: " + current.dueDate);
        current = current.next;
    }
    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }

        System.out.println("Task List:");
        Task temp = head;
        do {
            System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        boolean found = false;
        Task temp = head;
        do {
            if (temp.priority == priority) {
                System.out.println("Found Task: ID " + temp.id + ", Name: " + temp.name + ", Due: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tasks found with priority " + priority);
        }
    }
}
public class TaskSchedulerSystem {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.addAtEnd(101, "Task A", 1, "2025-04-20");
        scheduler.addAtEnd(102, "Task B", 2, "2025-04-21");
        scheduler.addAtBeginning(100, "Task C", 3, "2025-04-19");
        scheduler.addAtPosition(2, 103, "Task D", 2, "2025-04-22");

        scheduler.displayTasks();
        System.out.println();

        scheduler.viewCurrentTaskAndMoveNext();  
        scheduler.viewCurrentTaskAndMoveNext();  
        scheduler.viewCurrentTaskAndMoveNext();  

        System.out.println();
        scheduler.searchByPriority(2);
        System.out.println();

        scheduler.removeById(102);
        scheduler.displayTasks();
    }
}
