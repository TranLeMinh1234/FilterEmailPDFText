/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
public class MetadataController {
    
    @PostMapping("/getmetadata")
    @ResponseBody
    public ArrayList<FieldMetadata> page(@RequestBody Map<String, String> json) {
        String textProcessed = json.get("str");
        String [] listFieldWillGet = json.get("listField").split(",");
        String [] blockInfo = textProcessed.split("\n");
        
        ArrayList<FieldMetadata> listResult = new ArrayList<FieldMetadata>();
        ArrayList<Integer> listResult1 = new ArrayList<Integer>();
        
//        for(String field: listFieldWillGet)
//        {
//            for(int i = 0 ;i < blockInfo.length;i++)
//            {
//                if(blockInfo[i] != null && blockInfo[i].indexOf(field) != -1 && i != blockInfo.length-1)
//                {
//                    listResult.add(new FieldMetadata(field,blockInfo[i+1]));
//                }
//            }
//        }
        
        
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(textProcessed);
        while (m.find()) {
            listResult.add(new FieldMetadata("email",m.group()));
        }
        return listResult;
    }
    
    public class FieldMetadata{
        public String nameField;
        public String valueField;
        
        public FieldMetadata(String nameField, String valueField)
        {
            this.nameField = nameField;
            this.valueField = valueField;
        }
    }
    
}
