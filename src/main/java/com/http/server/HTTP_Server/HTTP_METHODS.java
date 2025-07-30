package com.http.server.HTTP_Server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.http.server.HTTP_Parser.Http_Reader;
import com.http.server.HTTP_Parser.Load_Dir_And_Files;

public class HTTP_METHODS{
    public static BufferedInputStream GET(String filename) throws FileNotFoundException{

        //TODO - Contents Should Return a smaller buffer maybe 4kib buffer
        //Add RootPath Environment Variable as an Option - Also start to utilize RootPath variable

        String RootPath = "/home/dani/Documents/HTTP_JAVA_SERVER";
        File file = new File(filename);
        file = file.isDirectory() ? new File("Ouput.tmpl") : file;    

  
        BufferedInputStream Reader = new BufferedInputStream(new FileInputStream(filename));        
        return Reader;
        
  
/*
        catch(FileNotFoundException e){
            System.err.println("");
            return new BufferedInputStream();
        }

        catch(IOException e ){
            System.err.println("");
            return new BufferedInputStream();
        }
*/
        
    }

    public static String HEAD(String filename){ //Should Just Instantiate a HTTP response object (maybe)
        String Status_Code;
        String Content_Length;
        String Mime_Type;
        try{
            File file = new File(filename);
            if (file.isDirectory() == true){
                Mime_Type = "text/html";
            try {
                Load_Dir_And_Files.HTML(Load_Dir_And_Files.List_Files(filename));
                }
              catch(FileNotFoundException e){
                System.err.println("Error Writing to Output Template File");
                }
                file = new File("Output.tmpl");
            }
            else if (file.exists() == false) {
                throw new FileNotFoundException("File Doesn't Exist");
            }
            else{
                Mime_Type = Http_Reader.Mime_Type_Check(filename);
            }
            Content_Length = Long.toString(file.length());
            Status_Code = "200";
            return Http_Reader.Response(Status_Code, Mime_Type, Content_Length, 1);
        }
        catch(Exception e){
            Status_Code = "404";
            Content_Length = "0";
            return Http_Reader.Response(Status_Code, "NULL", Content_Length, -1);
        }

    }

    public static String OPTIONS() {
        return "Allow: OPTIONS, GET, HEAD";
    }

}
