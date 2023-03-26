/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #6
 * Problem 1 & 2
 * (TagProofer.java)
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-03-10
 * -------------------------------------------------------------------------------
 * Problem 1 - Array Queue
 * -------------------------------------------------------------------------------
 * If I were implementing a queue class using an array data structure the class
 * would use several attributes. First it would need an Object Array to store
 * the queue items. It would also need a default size for the array
 * and a factor to resize it by if it became full.
 * It would also need an occupancy counter to serve as an index for inserting new
 * items and then incrementing it.
 * It would also need to keep track of the head of the line,
 * so we are not always reshuffling the items in the queue.
 * Whenever we get to the end of the array just create a new array to hold
 * all the items, move them over and reset the head index, resizing by the size
 * factor if we are close full.
 * -------------------------------------------------------------------------------
 * Problem 2
 * -------------------------------------------------------------------------------
 * This program checks for properly closed tag pairs
 * -------------------------------------------------------------------------------
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* "CLASS": TagProofer
 *
 * The functional code for proofreading the tags in an HTML file
 *
 */
public class TagProofer {

    /*  Function: IsOpeningTag
     *
     *  Is this line of the file an opening HTML tag?
     *
     */
    public static Boolean IsOpeningTag(String line) {
        try {
            if (line.charAt(0) == '<' && line.charAt(1) != '/')
                return true;
        } catch (StringIndexOutOfBoundsException ignored) {}
        return false;
    }

    /*  Function: IsClosingTag
     *
     *  Is this line of the file a closing HTML tag?
     *
     */
    public static Boolean IsClosingTag(String line) {
        try {
            if (line.charAt(0) == '<' && line.charAt(1) == '/')
                return true;
        } catch (StringIndexOutOfBoundsException ignored) {}
        return false;
    }

    /*  Function: GetPairedTag
     *
     *  Takes a String that is assumed to be an HTML tag and returns
     *  the HTML tag that it should match with.
     *
     *     Examples: GetPairedTag("<body>")  :=  "</body>"
     *               GetPairedTag("</p>")    :=  "<p>"
     */
    public static String GetPairedTag (String tag) {
        if (tag.charAt(1) == '/')
            return "<" + tag.substring(2);
        return "</" + tag.substring(1);
    }


    /*  Function: OpenInputFile
     *
     *  Takes the name of a file and opens it (or reports an error if
     *  it can't open a file of that name).
     *
     */
    public static Scanner OpenInputFile (String filename) {

        Scanner ScanningFriend = null;

        try {
            ScanningFriend = new Scanner(new File(filename));
        } catch (FileNotFoundException fnfe) {
            System.out.println("\n  ERROR:  Failed to open input file " + filename + "\n");
            System.exit(1);
        }

        return ScanningFriend;

    }

    /* * * * * * * * * * * * * * * * * * * * *
     *
     *                 main
     *
     */
    public static void main (String[] Args) {

        // Assist the user if they look like they need help
        if (Args.length != 1) {
            System.out.println("\n  USAGE:  java TagProofer.java [file.html]\n");
            System.exit(1);
        }

        int errorCount = 0; // Keep track of errors
        Scanner myScanner = OpenInputFile(Args[0]);
        TagStack myTagStack = new TagStack();
        int lineNumber = 0; // Keep Tack of Line Number
        while(myScanner.hasNextLine()) {
            lineNumber++;
            String line = myScanner.nextLine();

            // If Opening tag push it into the stack
            if(IsOpeningTag(line)) {
                myTagStack.Push(line.trim(),lineNumber);
            }

            // If closing tag check for errors
            if(IsClosingTag(line)) {
                Tag aTag = myTagStack.Pop();
                String matchingTag = GetPairedTag(aTag.type);
                if( ! line.equals(matchingTag) ) {
                    errorCount++;
                    System.out.print("Mismatched tags: ");
                    System.out.print(aTag.type);
                    System.out.print(" (line ");
                    System.out.print(aTag.line_number);
                    System.out.print(") was closed with ");
                    System.out.print(line.trim());
                    System.out.print(" (line ");
                    System.out.print(lineNumber);
                    System.out.println(")");
                }
            }
        }

        // Go through unmatched tags
        while(! myTagStack.IsEmpty() ) {
            errorCount++;
            Tag aTag = myTagStack.Pop();
            System.out.print("Unclosed tag: ");
            System.out.print(aTag.type);
            System.out.print(" (line ");
            System.out.print(aTag.line_number);
            System.out.println(")");
        }

        if(errorCount == 0){
            System.out.println("Lookin’ great!");
        }

    }


}


/*  CLASS: Tag
 *
 *  A linked list element designed to hold an HTML tag,
 *  the line number it was found on in the file, and
 *  a reference to the next Tag.
 *
 */
class Tag {

    String type;
    int    line_number;
    Tag    Next;

    public Tag (String type, int line_number) {
        this.type = type;
        this.line_number = line_number;
        this.Next = null;
    }

}



/*  CLASS: TagStack
 *
 *  A class for maintaining a stack of Tags
 *
 */
class TagStack {

    Tag Top;

    /*  Method: IsEmpty
     *
     *  Returns 'true' if the stack is empty
     *
     */
    public Boolean IsEmpty () {
        return Top == null;
    }

    /*  Method: Pop
     *
     *  Removes the top Tag from the stack and returns it
     *
     */
    public Tag Pop () {
        if(IsEmpty()){ // Can't pop anything if we are empty
            return null;
        }
        Tag out = Top; // Item to return
        Top = out.Next; // Top is now the next item
        out.Next = null; // Remove reference to next
        return out;
    }


    /*  Method: Push
     *
     *  Takes the info. needed to make a new Tag, creates a Tag, and
     *  pushes it onto the top of the stack.
     *
     */
    public void Push (String type, int line_number) {
        Tag newTag = new Tag(type, line_number);
        newTag.Next = Top;
        Top = newTag;
    }


}



