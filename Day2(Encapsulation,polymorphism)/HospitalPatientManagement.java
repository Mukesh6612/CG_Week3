interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

abstract class Patient {
    private String patientId;
    private String name;
    private int age;

    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    public abstract double calculateBill();

    public void getPatientDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class InPatient extends Patient implements MedicalRecord {
    private double roomCharge;
    private String medicalHistory;

    public InPatient(String patientId, String name, int age, double roomCharge) {
        super(patientId, name, age);
        this.roomCharge = roomCharge;
    }

    public double calculateBill() {
        return roomCharge + 1500;
    }

    public void addRecord(String record) {
        medicalHistory = record;
    }

    public void viewRecords() {
        System.out.println("Medical History: " + medicalHistory);
    }
}

class OutPatient extends Patient implements MedicalRecord {
    private double consultationFee;

    public OutPatient(String patientId, String name, int age, double consultationFee) {
        super(patientId, name, age);
        this.consultationFee = consultationFee;
    }

    public double calculateBill() {
        return consultationFee + 500;
    }

    public void addRecord(String record) {
    }

    public void viewRecords() {
        System.out.println("Outpatient has no medical history.");
    }
}

public class HospitalPatientManagement {
    public static void main(String[] args) {
        Patient p1 = new InPatient("IP101", "John Doe", 45, 2000);
        Patient p2 = new OutPatient("OP102", "Jane Smith", 34, 100);

        Patient[] patients = {p1, p2};

        for (Patient p : patients) {
            p.getPatientDetails();
            double bill = p.calculateBill();
            System.out.println("Bill Amount: $" + bill);

            if (p instanceof MedicalRecord) {
                MedicalRecord m = (MedicalRecord) p;
                m.addRecord("Diagnosed with fever and cold.");
                m.viewRecords();
            }
        }
    }
}
