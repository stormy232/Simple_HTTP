package com.http.server.HTTP_Parser;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Http_Reader{

    public static Map<String, String> MIME_TYPES = Map.of(".png","image/png",
                                            ".jpg", "image/jpeg",
                                            ".html", "text/html",
                                            ".txt", "text/plain",
                                            ".css", "text/css",
                                            ".js", "application/javascript",
                                            ".mp3", "audio/mpeg",
                                            ".mp4", "video/mp4",
                                            ".pdf", "application/pdf",
                                            "Default", "text/plain");

    private String Request_Line;
    private String Body;
    public HashMap<String,String> Headers = new HashMap<>();

    public Http_Reader(String Message) {
        
        String temp [] = Message.split("\n");
        Request_Line = temp[0];
        int BodyStart = Message.indexOf("\n\n")+2;

        for (int i = 1; i < temp.length; i++)  {

            String temp2[] = temp[i].split(" ");

            for (int j =1; j < temp2.length; j++) {
                
                Headers.put(temp2[0], temp2[j]);

            }

        }

        Body = Message.substring(BodyStart);

    }
    

    public String HTTP_REQUEST() {

        String temp[] = Request_Line.split(" ");
        return temp[0];

    }


    public String GET_URI() {

        String temp[] = Request_Line.split(" ");
        return temp[1].substring(1);

    }


    public Boolean VALID_HTTP_VERSION() {

        String temp[] = Request_Line.split(" ");
        System.out.println(temp[2]);
        return temp[2].equals("HTTP/1.1");

    }

    public int Content_Length() {

        if (Headers.containsKey("Content-Length:")) return Integer.parseInt(Headers.get("Content-Length:"));
        return -1;

    }

    public static String Response(String Status_Code, String Mime_Type, String Content_Length, int success){
        final String Server = "Server: DANI\n";
        final String Response = "HTTP/1.1 " + Status_Code +"\n";
        final String Date = "Date: " + ZonedDateTime.now(ZoneId.of("America/New_York")).format(DateTimeFormatter.RFC_1123_DATE_TIME) + "\n";
        final String Content = "Content-Type: " + Mime_Type + "\n";
        final String CL = "Content-Length: " + Content_Length + "\n";
        if (success != 1) {return Response+Content+CL+"\n";}
        return Response + Server + Date + Content + CL +"\n";
    }

    public static String Mime_Type_Check(String filename){
        int ext_start = filename.lastIndexOf(".");        
        if (ext_start == -1) {return MIME_TYPES.get("Default");}
        else{
            String extension = filename.substring(ext_start);
            return MIME_TYPES.getOrDefault(extension, "Default");
        }
    }
}   

