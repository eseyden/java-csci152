import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/************
 * FILE I/O
 * ----------
 * Notes
 * 2023-02-13
 ************/
public class FileIO {
    public static void main(String[] args) throws IOException {
        Scanner FiveScan = openFile(args[0]);
        int lineCount = 0;
        while(FiveScan.hasNextLine()) {
            FiveScan.nextLine();
            lineCount++;
        }
        FiveScan.close();
        System.out.println(args[0] + " has " + lineCount + " lines.");
    }

    public static Scanner openFile(String filename) throws FileNotFoundException{
        File FileHandle = new File(filename);
        Scanner FileScanner;
        try {
            FileScanner = new Scanner(FileHandle);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
            throw exception;
        }
        return FileScanner;
    }
    public static void lecture(String[] args) {
        String filename = "my-name.txt"; // file name
        Scanner ScanObj;
        File FileObj = new File(filename); // instance file object
        try {
            ScanObj = new Scanner(FileObj); // make reading file easier
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        while(ScanObj.hasNextLine()) {
            String myString = ScanObj.nextLine(); // read a line
            System.out.println(myString);
        }
        ScanObj.close();
    }
}
