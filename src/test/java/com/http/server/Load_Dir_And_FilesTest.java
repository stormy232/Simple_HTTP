/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */

package com.http.server;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.http.server.HTTP_Parser.Load_Dir_And_Files;

import org.junit.jupiter.api.Test;

/**
 *
 * @author dani
 */
public class Load_Dir_And_FilesTest {

    @Test 
    public void Resource_Directory(){
        try {

            List<String> SampleFileList = new ArrayList(Arrays.asList("Hello","Therefore"));
            Load_Dir_And_Files.HTML(SampleFileList);
            String HTML = "<!Doctype Html>\n<html>\n<head>\n<title> \nWebserver \n</title>\n</head>\n <body>\n<ul>\n<li> <a href = Hello> Hello </a> </li>\n<li> <a href = Therefore> Therefore </a> </li>\n<ul>\n</body>\n</html>";
            assertEquals ()
        } 
        catch (Exception e) {
            
        }
    }

}