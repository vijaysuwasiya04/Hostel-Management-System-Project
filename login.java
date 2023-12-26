import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.ArrayList;






class HostelManagementSystem extends Frame implements ActionListener
 {
   
    
   
    private ArrayList<Student> students = new ArrayList<Student>();
    private Hostel hostel = new Hostel(100, "Single", 50);
    private Label nameLabel, ageLabel, genderLabel, phoneLabel, roomLabel;
    private TextField nameField, ageField, genderField, phoneField, roomField;
    private Button addButton, removeButton, updateButton, displayButton, searchButton,clearButton;
    private TextArea textarea;

  int count =0;


    public HostelManagementSystem() 
    {
         setLayout(null);

        nameLabel = new Label("Name:");
        add(nameLabel);
        nameLabel.setBounds(600,200,100,20);
        nameField = new TextField(20);
        add(nameField);
        nameField.setBounds(700,200,150,25);

        ageLabel = new Label("Age:");
        add(ageLabel);
        ageLabel.setBounds(600,250,100,20);
        ageField = new TextField(20);
        add(ageField);
        ageField.setBounds(700,250,150,25);

        genderLabel = new Label("Gender:");
        add(genderLabel);
        genderLabel.setBounds(600,300,100,20);
        genderField = new TextField(20);
        add(genderField);
        genderField.setBounds(700,300,150,25);

        phoneLabel = new Label("Phone:");
        add(phoneLabel);
        phoneLabel.setBounds(600,350,100,20);
        phoneField = new TextField(20);
        add(phoneField);
        phoneField.setBounds(700,350,150,25);        

        roomLabel = new Label("Room No.:");
        add(roomLabel);
        roomLabel.setBounds(600,400,100,20);
        roomField = new TextField(20);
        add(roomField);
        roomField.setBounds(700,400,150,25);
        

        addButton = new Button("Add Student");
        addButton.addActionListener(this);
        addButton.setBounds(900,200,120,40);
        add(addButton);

        removeButton = new Button("Remove Student");
        removeButton.addActionListener(this);
        removeButton.setBounds(1050,200,120,40);
        add(removeButton);

        updateButton = new Button("Update Student");
        updateButton.addActionListener(this);
        updateButton.setBounds(900,280,120,40);
        add(updateButton);

        displayButton = new Button("Display Details");
        displayButton.addActionListener(this);
        displayButton.setBounds(1050,280,120,40);
        add(displayButton);

        searchButton = new Button("Search Student");
        searchButton.addActionListener(this);
        searchButton.setBounds(900,360,120,40);
        add(searchButton);

        clearButton = new Button("Clear");
        clearButton.addActionListener(this);
        clearButton.setBounds(1050,360,120,40);
        add(clearButton);

        textarea=new TextArea("");
        textarea.setBounds(600,450,600,300);
        add(textarea);

        addWindowListener(new MyWindowAdapter());
        setTitle("Hostel Management System");
        setSize(800, 800);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
             count++;
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            String phone = phoneField.getText();
            int roomNo = count;
            Student student = new Student(name, age, gender, phone, roomNo);
            students.add(student);
            hostel.addOccupancy(roomNo);
            System.out.println("Student added successfully!");
        } else if (e.getSource() == removeButton) {
            int roomNo = Integer.parseInt(roomField.getText());
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getRoomNo() == roomNo) {
                    students.remove(i);
                    hostel.removeOccupancy(roomNo);
                    textarea.setText("");
                    textarea.append("Student removed successfully!");
                    break;
                }
            }
        } else if (e.getSource() == updateButton) {
            textarea.setText(" ");
            int roomNo = Integer.parseInt(roomField.getText());
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getRoomNo() == roomNo) {
                    students.get(i).setName(nameField.getText());
                    students.get(i).setAge(Integer.parseInt(ageField.getText()));
                    students.get(i).setGender(genderField.getText());
                    students.get(i).setPhone(phoneField.getText());
                    textarea.append("Student details updated successfully!");
                    break;
                }
            }
        } else if (e.getSource() == displayButton) {
            textarea.setText(" ");
            textarea.append("Hostel Details:"+"\n"+"No. of rooms: " + hostel.getNoOfRooms()+"\n"+"Room type: " + hostel.getRoomType()+"\n"+"Occupancy: " + hostel.getOccupancy()+"\n"+"Available rooms: " +             hostel.getAvailableRooms()+"\n\n"+"Student Details:\n");
            for (Student student : students) 
            {
                 textarea.append("Name: " + student.getName()+"\n"+"Age: " + student.getAge()+"\n"+"Gender: " + student.getGender()+"\n"+"Phone: " + student.getPhone()+"\n"+"Room No.: " + student.getRoomNo()+"\n\n");
            }
        }  else if (e.getSource() == clearButton) {
            nameField.setText("");
            genderField.setText("");
            ageField.setText("");
            phoneField.setText("");
            roomField.setText("");
            textarea.setText("");
            
        } else if (e.getSource() == searchButton) {
            textarea.setText(" ");
            int roomNo = Integer.parseInt(roomField.getText());
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getRoomNo() == roomNo)
               {
        nameField.setText(students.get(i).getName());
            genderField.setText(students.get(i).getGender());
            ageField.setText(Integer.toString(students.get(i).getAge()));
            phoneField.setText(students.get(i).getPhone());
            
                    textarea.append(students.get(i).getName()+"\n"+Integer.toString(students.get(i).getAge())+"\n"+students.get(i).getGender()+"\n"+students.get(i).getPhone());
                    break;
                }
else
{
textarea.setText("room number does not exist");
}
            }
        }
    }
}



class Hostel {
    private int noOfRooms;
    private String roomType;
    private int occupancy;

    public Hostel(int noOfRooms, String roomType, int occupancy) {
        this.noOfRooms = noOfRooms;
        this.roomType = roomType;
        this.occupancy = occupancy;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public int getAvailableRooms() {
        return noOfRooms - occupancy;
    }

    public void addOccupancy(int roomNo) {
        occupancy++;
    }

    public void removeOccupancy(int roomNo) {
        occupancy--;
    }
}

class Student {
    private String name;
    private int age;
    private String gender;
    private String phone;
    private int roomNo;

    public Student(String name, int age, String gender, String phone, int roomNo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.roomNo = roomNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoomNo() {
        return roomNo;
    }
}
class login extends Frame implements ActionListener
{
    TextField userName, password;
    Button btn1;
	Label error;

   public login()
   {
        setLayout(null);

        Label u_name = new Label("User Name:",Label.CENTER);
        Label pass = new Label("Password:",Label.CENTER);
       error=new Label(" ",Label.CENTER);
        //username declaration
        userName = new TextField(20);
        error.setForeground(Color.red);
        add(u_name);
        add(userName);
		
 
        //Password declaration
        password = new TextField(20);
        add(pass);
        add(password);
        
        password.setEchoChar('*');
        
        //Button Declaration
        btn1 =new Button("Login");
     
        add(btn1);
        add(error);
      
        //Setting the display Bounds
        userName.setBounds(200,100,90,20);
        u_name.setBounds(70,90,90,40);
        
 
        password.setBounds(200,140,90,20);
        pass.setBounds(70,130,90,40);
 
        btn1.setBounds(100,260,70,40);
        error.setBounds(200,50,120,20);
     
	 
btn1.addActionListener(this);
addWindowListener(new MyWindowAdapter());
}

public void actionPerformed(ActionEvent ae)
{
String str=ae.getActionCommand();
if(userName.getText().equals("admin") && password.getText().equals("1234"))
{
  HostelManagementSystem o=new HostelManagementSystem();
   o.setSize(800,800);
   o.setVisible(true);
}
else{

error.setText("Invalid Credential");
}
}
public static void main(String args[])
{
login l=new login();
l.setSize(800,800);
l.setVisible(true);

}
}

class MyWindowAdapter extends WindowAdapter
{
public void windowClosing(WindowEvent we)
{
System.exit(0);
}
}




