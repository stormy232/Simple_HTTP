package com.http.server;

import com.http.server.HTTP_Parser.*;
import com.http.server.HTTP_Server.*; 

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String Message = "POST /users HTTP/1.1\nHost: example.com\nContent-Type: application/x-www-form-urlencoded\nContent-Length: 49\n\nname=FirstName+LastName&email=bsmth%40example.com";
/*      
        long start = System.nanoTime();

        Http_Reader test = new Http_Reader(Message);
        System.out.println(test.HTTP_STATUS_CODE());

        long end = System.nanoTime();

        long duration = end - start;

        System.out.println("The Program Took " + duration + " long to run");
  */
        Networking server = new Networking();
        server.start(9000);
        server.stop();


    }



}
