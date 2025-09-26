/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */

package com.http.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.http.server.HTTP_Parser.Load_Dir_And_Files;

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
        } 
        catch (Exception e) {
            
        }

  }

    @Test
    public void HTTP_RESPONSE(){
        String Message = "POST /users HTTP/1.1\nHost: example.com\nContent-Type: application/x-www-form-urlencoded\nContent-Length: 49\n\nname=FirstName+LastName&email=bsmth%40example.com";



    }
   

}
