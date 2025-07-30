package com.http.server.HTTP_Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class Load_Dir_And_Files{
    
    public static void HTML(List<String> Files) throws FileNotFoundException{

      HashMap<String, Object> context = new HashMap();
      context.put("files",Files);

      FileOutputStream output = new FileOutputStream("Output.tmpl");

      MustacheFactory mf = new DefaultMustacheFactory();
      Mustache mus = mf.compile("/home/dani/Documents/HTTP_JAVA_SERVER/src/main/resources/index.tmpl");
      try{
      mus.execute(new OutputStreamWriter(output), context).flush();
      }
      catch(IOException e){
        System.err.println("Damn");
      }
    }

    public static List<String> List_Files(String Path){
        File Directory = new File(Path);
        return Arrays.asList(Directory.list());
    }

   /* public static void main(String[] args) {
        String Path = ".";
        String[] Files = List_Files(Path);
        System.out.println(HTML(Files, Path));
    } */
}
