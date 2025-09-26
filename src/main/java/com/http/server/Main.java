package com.http.server;

import com.http.server.HTTP_Server.*; 

public class Main {

    public static void main(String[] args) {
        Networking server = new Networking();
        server.start(9000);
        server.stop();


    }



}
