package com.http.server.HTTP_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.http.server.HTTP_Parser.Http_Reader;

public class Networking{

    private ServerSocket listener;

    public void start(int port) {
      try {
        this.listener = new ServerSocket(port);
        while (true) {
          new ClientSocket(listener.accept()).start();
        }
      }

      catch(IOException e) {throw new RuntimeException("Issue Listening on Socket",e);}

    }

    public void stop() {

      try {
       listener.close();
      }

      catch(IOException e) {
        throw new RuntimeException("Issue Closing Listening Socket",e);
      }

    }
  
private static class ClientSocket extends Thread {

    private final Socket Client;
    private BufferedReader input;
    private OutputStream out;

    ClientSocket(Socket socket) {
      this.Client = socket;
    }

    @Override
    public void run() {
  
      try {
      out = Client.getOutputStream();
      input = new BufferedReader(new InputStreamReader(Client.getInputStream()));
      StringBuilder request = new StringBuilder();
      while (input.ready()) {
        request.append(input.readLine());
      }
      
      Handle_Request(request.toString());
      out.close();
      input.close();
      Client.close();

      }

      catch(IOException e) {throw new RuntimeException("Issue Handling Client reading",e);}
    }

    public void send(int[] Message) {
       for(int i = 0; i < Message.length; i++){
        try {out.write(Message[i]);}
        catch(IOException e) {System.err.println("Issue Sending Message To Client");}       
       }
    }

    public void send(String Message){
      try {out.write(Message.getBytes());}
      catch(IOException e) {System.err.println("Issue Sending Message To Client");}
    }

    public void Handle_Request(String request){
      Http_Reader reader = new Http_Reader(request);
      String Filename = reader.GET_URI();
      switch (reader.HTTP_REQUEST()) {
          case "GET":
              // int[] output = HTTP_METHODS.GET(Filename);
              System.out.println(Filename);
              Filename = Filename.equals("") ? "." : Filename; //For This we can add a mapping that GET has to search potentially
              System.out.println(HTTP_METHODS.HEAD(Filename));
              send(HTTP_METHODS.HEAD(Filename));
              // int [] data = HTTP_METHODS.GET(Filename);   
              send(data);
              break;
          case "HEAD":
              send(HTTP_METHODS.HEAD(Filename));
              break;
          case "OPTIONS":
              send(HTTP_METHODS.OPTIONS());
              break;
          default:
              throw new AssertionError();
      }
    }


  }
}
