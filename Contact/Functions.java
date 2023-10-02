import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Functions extends Contact {
    private static List<Contact> listContact = new ArrayList<>();
    private static boolean flag = true;
    private static String fileName = "phonebook.txt";

    public Functions() {
        super(null, null, null);
    }

    public void addNewContact(String name, String phoneNumber, String note) {
        readObject(Functions.fileName);
        for (int idx = 0; idx < Functions.listContact.size(); idx++) {
            if (Objects.equals(listContact.get(idx).getPhoneNumber(), phoneNumber)) {
                System.out.println("Phone number already exists!");
                return;
            }
        }
        Contact contact = new Contact(name, phoneNumber, note);
        writeObject(contact);
        Functions.listContact.clear();
    }

    public void editInformation(int choose, String phoneNumber, String newInformation) {
        readObject(Functions.fileName);
        int index = -1;
        for (int idx = 0; idx < Functions.listContact.size(); idx++) {
            if (Objects.equals(listContact.get(idx).getPhoneNumber(), phoneNumber)) {
                index = idx;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Phone number does not exist!");
            return;
        }
        switch (choose) {
            case 1:
                Functions.listContact.get(index).setName(newInformation);
                Functions.flag = false;
                for (int idx = 0; idx < Functions.listContact.size(); idx++) {
                    writeObject(Functions.listContact.get(idx));
                }
                Functions.flag = true;
                Functions.listContact.clear();
                break;
            case 2:
                Functions.listContact.get(index).setPhoneNumber(newInformation);
                Functions.flag = false;
                for (int idx = 0; idx < Functions.listContact.size(); idx++) {
                    writeObject(Functions.listContact.get(idx));
                }
                Functions.flag = true;
                Functions.listContact.clear();
                break;
            case 3:
                Functions.listContact.get(index).setNote(newInformation);
                Functions.flag = false;
                for (int idx = 0; idx < Functions.listContact.size(); idx++) {
                    writeObject(Functions.listContact.get(idx));
                }
                Functions.flag = true;
                Functions.listContact.clear();
                break;
            default:
                System.out.println("No information found that needs editing!");
                break;
        }
    }

    public void deleteContact(String phoneNumber) {
        readObject(Functions.fileName);
        int index = -1;
        for (int idx = 0; idx < Functions.listContact.size(); idx++) {
            if (Objects.equals(listContact.get(idx).getPhoneNumber(), phoneNumber)) {
                index = idx;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Phone number already exists!");
            return;
        }
        Functions.listContact.remove(index);
        Functions.flag = false;
        for (int idx = 0; idx < Functions.listContact.size(); idx++) {
            writeObject(Functions.listContact.get(idx));
        }
        Functions.flag = true;
        Functions.listContact.clear();

    }

    public void viewContact() {
        readObject(Functions.fileName);
        for (int idx = 0; idx < Functions.listContact.size(); idx++) {
            String contact = "STT: " + String.valueOf(idx) + "\n" + "Name: " + Functions.listContact.get(idx).getName()
                    + "\n" + "Number: " + Functions.listContact.get(idx).getPhoneNumber() + "\n" +
                    "Note: " + Functions.listContact.get(idx).getNote() + "\n";
            System.out.println(contact);
        }
        Functions.listContact.clear();
    }

    public void writeObject(Contact contact) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Functions.fileName, Functions.flag);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(contact);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readObject(String fileName) throws RuntimeException {
        Functions.listContact.clear();
        int count = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Contact> list = (List<Contact>) objectInputStream.readObject();
            list.forEach(item -> System.out.println(item));


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
