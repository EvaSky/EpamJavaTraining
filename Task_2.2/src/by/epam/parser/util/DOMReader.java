package by.epam.parser.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Olga Shahray on 25.05.2016.
 */
public class DOMReader {
    private BufferedReader bufferedReader;
    private static DOMReader domReader;
    private static int countLines;


    private DOMReader(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static DOMReader getDOMReader(String fileName){
        if (domReader == null) {
            domReader = new DOMReader(fileName);
        }
        return domReader;
    }


    public char[] getNextReadLines() throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        int startLine = countLines;
        for (int i = startLine; i<startLine+5; i++) {
            line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line);
                countLines++;
            }
            else{ break; }
        }
        return sb.toString().toCharArray();
    }

    public void close() throws IOException {
        if(bufferedReader!=null)
            bufferedReader.close();
    }
}
