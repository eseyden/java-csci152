public class NameFormatter {
    public static void main(String[] args) {

        for(String name: args) {
            printFormattedString(name);
            System.out.print(' ');
        }

        System.out.println();
    }

    public static void printFormattedString(String name)
    {
        boolean first = true;
        char last_char = '?';
        for(char letter: name.toCharArray()) {
            if(first || last_char == '-') {
                System.out.print(capitalizeChar(convertChar(letter)));
                first = false;
            } else {
                System.out.print(convertChar(letter));
            }
            last_char = letter;
        }
    }

    public static char convertChar(char character) {

        switch (character) {
            case 48:
                return 'o';
            case 49:
                return 'l';
            case 50:
                return 'z';
            case 51:
                return 'e';
            case 53:
                return 's';
            case 54:
                return 'g';
            case 55:
                return 't';
            case 56:
                return 'b';
        }
        if(character > 64 && character < 91) {
            character = (char)(character + 32);
        }
        return character;
    }

    public static char capitalizeChar(char character) {
        if(character > 96 && character < 123) {
            character = (char)(character - 32);
        }
        return character;
    }
}
