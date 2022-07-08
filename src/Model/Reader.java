package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reader class contains methods to read integers from a specified file.
 *
 * @author aet9
 */
public class Reader {

    private File file;
    private BufferedReader Bf;
    private FileReader Fr;

    /**
     * Creates a new buffered reader using the specified file name;
     * 
     * @param fileName file to read
     */
    public Reader(File fileName) {
        try {
            file = fileName;
            Fr = new FileReader(file);
            Bf = new BufferedReader(Fr);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error no file found");
        }
    }

    /**
     * Reads the next intiger from the file and changes it to an int.
     *
     * @return intiger read from the file
     */
    public int read() {
        int num = 0;
        char c;
        try {
            do {
                c = (char)Bf.read();
                if (c==' ') {
                    c = '0';
                }
            }while(c!='0' && c!='1' && c!='2' && c!='3' && c!='4' && c!='5'
                    && c!='6' && c!='7' && c!='8' && c!='9');
            num = Integer.parseInt(c+"");
        }
        catch (IOException e) {
            System.out.println("Error no line found");
        }
        return num;
    }


    /**
     * Method returns current file
     *
     * @return current file
     */
    public File getFile() {
        return file;
    }
}

