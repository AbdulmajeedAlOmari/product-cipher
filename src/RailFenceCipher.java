import java.util.ArrayList;

public class RailFenceCipher {

    public static String Encrypt(String plainText, int key) throws IllegalArgumentException {
        if(plainText == null || plainText.length() <= 1) {
            throw new IllegalArgumentException("plainText's length must be >= 2.");
        } if(key <= 1 || key >= plainText.length()) {
            throw new IllegalArgumentException("Key for RailFence greater than 1 and less than 1/2 of the message length.");
        }

        int lengthOfText = plainText.length();

        if(lengthOfText/key == 0) {
            throw new IllegalArgumentException("Text can not be encrypted using the key (" + key + ").");
        }

        // create a matrix for rail-fence (to cipher text)
        // key = rows && textLength = columns
        char[][] railFence = new char[key][lengthOfText];

        // Construct railFence matrix with special character to distinguish later.
        for(int i=0; i < key; i++)
            for(int j=0; j<lengthOfText; j++)
                railFence[i][j] = '*';

        fillWithCipherText(plainText, key, lengthOfText, railFence);

        return extractCipherText(key, lengthOfText, railFence);
    }

    public static String Decrypt(String cipherText, int key) {

        return "";
    }

    private static void fillWithCipherText(String plainText, int key, int lengthOfText, char[][] railFence) {
        // To determine which direction to go
        boolean directionDown = false;

        // Row and Column indexes to traverse in the rail:
        int rowIndex = 0;
        int columnIndex = 0;

        char[] plainChars = plainText.toCharArray();

        for(int i=0; i<lengthOfText; i++) {
            if(rowIndex == 0 || rowIndex == key-1)
                directionDown = !directionDown; // Invert the direction

            // Fill in railFence
            railFence[rowIndex][columnIndex] = plainChars[i];

            // Go to next column
            columnIndex++;

            // Increment row if we are going down, decrement otherwise.
            if(directionDown)
                rowIndex++;
            else
                rowIndex--;
        }
    }

    private static String extractCipherText(int key, int lengthOfText, char[][] railFence) {
        // Create a StringBuilder to construct cipherText
        StringBuilder cipherBuilder = new StringBuilder();

        // Extract cipher text from the railFence array above
        for(int i=0; i<key; i++)
            for(int j=0; j<lengthOfText; j++)
                if(railFence[i][j] != '*')
                    cipherBuilder.append(railFence[i][j]);

        return cipherBuilder.toString();
    }
}
