package org.example;

import org.example.model.ModelAttendance;
import org.example.model.ModelClass;
import org.example.model.ModelUser;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Connection connect = DBcontroller.connect();
Scanner sc = new Scanner(System.in);
        int ch ;
do {
    System.out.println("1. Insert User");
    System.out.println("2. Display User");
    System.out.println("3. Insert Class");
    System.out.println("4. Display Class");
    System.out.println("5. Do Attendance");
    System.out.println("Enter any other number to close program");
    System.out.print("Enter your choice:");
    ch = sc.nextInt();
    switch (ch) {
        case 1 :
            System.out.print("Enter username: ");
            String username = sc.next();
            System.out.print("Enter Password: ");
            String password = sc.next();
            ModelUser user = new ModelUser(0, username  , password);
        DBcontroller.addUsers(user, connect);
            System.out.println("---------------Added Succesfully!-------------- \n");
        break;
        case 2:
            List<ModelUser> user_list = DBcontroller.getUser(connect);
            System.out.println("------------------------Users---------------------");
            System.out.println("ID\t\tUsername");
            for (ModelUser data : user_list) {
                System.out.println(data.getId() + "\t\t" + data.getUsername() );
            }
            System.out.println("------------------------Users---------------------");
            break;
        case 3:
            System.out.print("Enter Class name");
            String class_name = sc.next();
            ModelClass class_new = new ModelClass(0, class_name);
            DBcontroller.addClass(class_new, connect);
            System.out.println("---------------Added Succesfully!-------------- \n");
            break;

        case 4 :
            List<ModelClass> class_list = DBcontroller.getClasses(connect);
            System.out.println("------------------------Classes---------------------");

            System.out.println("ID\t\tClassname");
            for (ModelClass data : class_list) {
                System.out.println( data.getId() + "\t\t " + data.getClassname() );
            }
            System.out.println("------------------------Classes---------------------");
            break ;
        case 5:
            System.out.print("Enter your Username");
             String userName = sc.next() ;
            System.out.print("Enter your the name of your class");
             String className = sc.next() ;
            int class_id = DBcontroller.getClassIdFromClass(connect,className);
            int user_id = DBcontroller.getUserIdFromUser(connect,userName);
            ModelAttendance attendance = new ModelAttendance(0, class_id,user_id);
            DBcontroller.addAttendance(attendance, connect);
            System.out.println("--------------------Attendance added--------------------");
            break;
    }
}while (ch!=0);

    }
}