package com.example.navana3s;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.Path;

public class database {


    public boolean search(String filename,String content){
        URL resourceUrl=getClass().getResource(filename);

        if (resourceUrl == null) {
            System.out.println("File not found: " + filename);
            return false;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(new File(resourceUrl.getPath())))) {
            System.out.println("Reading from file: " + filename);
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals(content)){
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());

            return false;
        }


    }

    public void readFile(String filename) {
        URL resourceUrl = getClass().getResource(  filename);

        if (resourceUrl == null) {
            System.out.println("File not found: " + filename);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(new File(resourceUrl.getPath())))) {
            System.out.println("Reading from file: " + filename);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }


    public void writeFile(String filename, String content) {

        Path filePath = Paths.get(System.getProperty("user.dir"), filename);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            bw.write(content);
            bw.newLine();
            System.out.println("Content written to file: " + filePath);
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        database db = new database();

        // Reading from input.txt
        db.readFile("input.txt");

        // Writing to output.txt
        db.writeFile("output.txt", "This line was added programmatically.");
        db.writeFile("output.txt", "Appending another line for testing.");

        // Reading from output.txt to verify
        db.readFile("output.txt");
    }
}
