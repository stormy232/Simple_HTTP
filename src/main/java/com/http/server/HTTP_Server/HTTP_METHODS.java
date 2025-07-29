package HTTP_Server;

import HTTP_Parser.Http_Reader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HTTP_METHODS{
    public static int[] GET(String filename) {
        try {
            File file;
            if(filename.equals(""))
            {
                file = new File("index.html");
            }
            else{
                file = new File(filename);
                if (file.exists() == false) {
                    throw new FileNotFoundException("File Doesn't Exist");
                }
            }
            BufferedInputStream Reader = new BufferedInputStream(new FileInputStream(filename));        
            long length = file.length();
            int data = 0;
            int[] Contents = new int[(int)length];
            for (int i = 0; i < length; i++){
                Contents[i] = (byte)Reader.read();
            }

            return Contents;
        } 
        
        catch (Exception e) {
            System.out.println(filename);
            return new int[0];
        }
    }

    public static String HEAD(String filename){ //Should Just Instantiate a HTTP response object (maybe)
        String Status_Code;
        String Content_Length;
        String Mime_Type;
        try{
            File file = new File(filename);
            if (file.isDirectory() == true){
                Mime_Type = "text/html";
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
