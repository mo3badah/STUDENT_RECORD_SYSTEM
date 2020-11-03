package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class SchoolSystem extends Application {

    @Override
    public void start(Stage primaryStage){
        //initialize our text fields and cotrols
        TextField name =new TextField();
        TextField mail =new TextField();
        TextField tel =new TextField();
        TextField id =new TextField();
        Button Add=new Button("Add");
        Button Search=new Button("search");
        Button Modify=new Button("Modify");
        Button Delete=new Button("Delete");
        Button ShowStudent=new Button("ShowStudents");
        Text welcome = new Text(40,60,"Welcome manager to your  system \n to ADD: fill all fields matching\nto search | Delete | Modify: Enter Name only ");
        Text studentFiles = new Text(40,200,"");
        welcome.setTextAlignment(TextAlignment.CENTER);
        //pane
        GridPane pane=new GridPane(); //our pane is grid
        pane.setPadding(new Insets(12));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.TOP_CENTER);
        //add them to our pane
        pane.add(welcome,0,0);
        pane.add(Add,0,1);
        pane.add(Search,1,1);
        pane.add(Modify,2,1);
        pane.add(Delete,3,1);
        //labels
        pane.add(new Label("Name"),0,2);
        pane.add(new Label("Mail"),0,3);
        pane.add(new Label("Telephone"),0,4);
        pane.add(new Label("ID"),0,5);
        //text fields
        pane.add(name,2,2);
        pane.add(mail,2,3);
        pane.add(tel,2,4);
        pane.add(id,2,5);
        //student mark sheet
        pane.add(ShowStudent,1,6);
        pane.add(studentFiles,0,7);
        welcome.setFill(Color.RED);//font color
        Font font=new Font(16);
        welcome.setFont(font);
        studentFiles.setFont(font);
        studentFiles.setFill(Color.BLUE);//font color


        //events of the buttons
        //Add button
        Add.setOnAction(event -> {//not to stob from any error
            try { //to override errors
                //store data from textFields to variables
                String nam=name.getText();
                String ml= mail.getText();
                long tl= Long.parseLong(tel.getText());
                long d= Long.parseLong(id.getText());
                //initialize a data store file
                PrintWriter p1=new PrintWriter(new FileOutputStream(new File("StudentData.txt"),true));
                p1.println(nam+","+ml+","+tl+","+d);//store data
                //show confirm
                studentFiles.setText("name :"+nam+"    mail :"+ml+"  Telephone :"+tl+"   ID :"+d+"\n            ...Aded Successfuly");
                p1.close();//close file to protect data
                // Delete fields inputs
                name.setText("");
                mail.setText("");
                tel.setText("");
                id.setText("");



            }
            catch (Exception e){ studentFiles.setText("please enter a valid inputs");}



        });
        //search button
        Search.setOnAction(event -> {//search procedure
            boolean found= false;
            String nam="";
            String ml="";
            String tl="";
            String d="";
            try {//not to stob from any error
                Scanner S = new Scanner(new File("StudentData.txt"));// to read the  file
                S.useDelimiter("[,\n]");// to exact store
                while (S.hasNextLine() && !found) {// to read whole file
                    // our parameters
                    nam = S.next();
                    ml = S.next();
                    tl = S.next();
                    d = S.next();
                    if (nam.equals(name.getText())) {// if exist
                        found = true;
                    }
                }
                if (found) { studentFiles.setText("name :" + nam + "    mail :" + ml + "  Telephone :" + tl + "   ID :" + d + "\n            ...founded Successfuly"); }
                else {studentFiles.setText("...........NOT FOUNDED..............\n .................try again................."); }
            }catch (Exception e){
                studentFiles.setText(">>>>>>>>>>ERROR<<<<<<<<<<<<<<");

            }
            // Delete fields inputs
            name.setText("");
            mail.setText("");
            tel.setText("");
            id.setText("");
        });
        Delete.setOnAction(event -> {
            // our parameters we  will use
            String nam="";
            String ml="";
            String tl="";
            String d="";
            // to create files if not founded
            String origin="StudentData.txt";
            String temp="newone.txt";
            File newone = new File(temp);
            File oldone = new File(origin);



            try {//not to stob from any error
                PrintWriter p1 = new PrintWriter(new FileOutputStream(temp), true); //to write overiding founded data
                Scanner s1 = new Scanner(new File(origin));// to read the old file
                s1.useDelimiter("[,\n]");
                while (s1.hasNext()) {
                    //save data from file to be checked
                    nam = s1.next();
                    ml = s1.next();
                    tl = s1.next();
                    d = s1.next();
                    if (!nam.equals(name.getText())) {
                        p1.println(nam + "," + ml + "," + tl + "," + d);//store data if not matched to new file
                        System.out.println("hey");
                    } else {

                        try {
                            studentFiles.setText("name :" + nam + "    mail :" + ml + "  Telephone :" + tl + "   ID :" + d + "\n  ... Deleted Successfuly...");// confirmation alert

                        }catch (Exception e){
                            studentFiles.setText(".....IN VALID ENTER........");

                        }

                    }
                }
                // close and delete old file and rename the new as old
                s1.close();
                p1.flush();
                p1.close();
                oldone.delete();
                File dump = new File(origin);
                newone.renameTo(dump);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // Delete fields inputs
            name.setText("");
            mail.setText("");
            tel.setText("");
            id.setText("");


        });
        ShowStudent.setOnAction(event -> {
            try {//not to stob from any error

                Scanner S = new Scanner(new File("StudentData.txt"));// to read the old file

                String all="";
                String nam="";
                String ml="";
                String tl="";
                String d="";
                String twx="";
                String af=studentFiles.getText();
                S.useDelimiter("[,\n]");
                while (S.hasNext()){
                    nam = S.next();
                    ml = S.next();
                    tl = S.next();
                    d = S.next();

                    twx+="name :" + nam + "    mail :" + ml + "  Telephone :" + tl + "   ID :" + d + "\n ";

                    studentFiles.setText(twx);
                }
//                studentFiles.setText(af+"\n"+twx);
            }
            catch (Exception e){studentFiles.setText(">>>>>>>>>>>>>>>>>EROR<<<<<<<<<<<<<<<<<<<<");}
            // Delete fields inputs
            name.setText("");
            mail.setText("");
            tel.setText("");
            id.setText("");
        });
        Modify.setOnAction(event -> {
            // our parameters we  will use
            String nam="";
            String ml="";
            String tl="";
            String d="";
            // to create files if not founded
            String origin="StudentData.txt";
            String temp="newone.txt";
            File newone = new File(temp);
            File oldone = new File(origin);



            try {//not to stob from any error
                PrintWriter p1 = new PrintWriter(new FileOutputStream(temp), true); //to write overiding founded data
                Scanner s1 = new Scanner(new File(origin));// to read the old file
                s1.useDelimiter("[,\n]");
                while (s1.hasNext()) {
                    //save data from file to be checked
                    nam = s1.next();
                    ml = s1.next();
                    tl = s1.next();
                    d = s1.next();
                    if (!nam.equals(name.getText())) {
                        p1.println(nam + "," + ml + "," + tl + "," + d);//store data if not matched to new file
                        System.out.println("hey");
                    } else {

                       try {

                           p1.println(name.getText() + "," + mail.getText() + "," + tel.getText() + "," + id.getText());//store data of fields to new location if matched
                           System.out.println("hey2");
                       }catch (Exception e){
                           studentFiles.setText(".....IN VALID ENTER........");

                       }
                       // confirmation alert
                        studentFiles.setText("name :" + nam + "    mail :" + ml + "  Telephone :" + tl + "   ID :" + d + "\n  ...MODIFIED Successfuly...");
                    }
                }
                // close and delete old file and rename the new as old
                s1.close();
                p1.flush();
                p1.close();
                oldone.delete();
                File dump = new File(origin);
                newone.renameTo(dump);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // Delete fields inputs
            name.setText("");
            mail.setText("");
            tel.setText("");
            id.setText("");
        });
        Scene s =new Scene(pane,800,700);//initial Scene
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Student Record System");//primary tittle
        primaryStage.setScene(s);//add scene to primary
        primaryStage.show();//to make project is shown
    }


    public static void main(String[] args) {
        launch(args);
    }
}
