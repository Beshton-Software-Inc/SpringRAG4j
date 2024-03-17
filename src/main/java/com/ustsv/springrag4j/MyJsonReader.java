package com.ustsv.springrag4j;

import org.json.JSONObject;
import org.rag4j.util.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MyJsonReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(org.rag4j.util.resource.JsonlReader.class);
    private List<Map<String, String>> lines;

    public MyJsonReader(List<String> properties, MultipartFile file) {
        this.initLinesFromProvidedFile(properties, file);
    }

    private void initLinesFromProvidedFile(List<String> properties, MultipartFile file) {
        this.lines = new ArrayList();

        try {
//            InputStream inputStream = this.getClass().getResourceAsStream("/data/" + filename);
//            InputStream inputStream = (InputStream) file;
            System.out.println("MyJsonReader initLinesFromProvidedFile started");
            //save the inputStream to the temporary file
            InputStream inputStream = file.getInputStream();
//            InputStream inputStream = file.getResource();
            if (inputStream == null) {
                LOGGER.error("Error reading file: {}", file.getOriginalFilename());
                throw new ResourceException("Check the file name and try again.");
            } else {
                System.out.println("MyJsonReader started");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                System.out.println("MyJsonReader before line = reader.readLine()");
                String line;
                while((line = reader.readLine()) != null) {
                    JSONObject obj = new JSONObject(line);
                    Map<String, String> lineMap = new HashMap();
                    Iterator var8 = properties.iterator();

                    System.out.println("MyJsonReader before var8.hasNext()");
                    while(var8.hasNext()) {
                        String property = (String)var8.next();
                        lineMap.put(property, obj.getString(property));
//                        lineMap.put(property, obj.optString(property));
                    }

                    this.lines.add(lineMap);
                    System.out.println("MyJsonReader finished");
                }

            }
        } catch (IOException var10) {
            LOGGER.error("Error reading file: {}", var10.getMessage());
            throw new ResourceException(var10.getMessage());
        }
    }

    public List<Map<String, String>> getLines() {
        return this.lines;
    }
}
