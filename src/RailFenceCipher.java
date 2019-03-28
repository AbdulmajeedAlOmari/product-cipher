public class RailFenceCipher {

    static String Encrypt(String plainText, int key) throws IllegalArgumentException {
        // Validate input, throw error if exist
        checkInput(plainText, key);

        int lengthOfText = plainText.length();

        // create a matrix for rail-fence (to cipher text)
        // key = rows && textLength = columns
        char[][] railFence = new char[key][lengthOfText];

        // Fill the matrix with the cipherText using rail-fence algorithm
        fillUsingPlainText(plainText, key, railFence);

        return extractCipherText(key, lengthOfText, railFence);
    }

    static String Decrypt(String cipherText, int key) throws IllegalArgumentException {
        checkInput(cipherText, key);

        int lengthOfText = cipherText.length();

        char[][] railFence = new char[key][lengthOfText];

        // Mark the rail-fence with '+', to fill it later with cipherText
        markRailFence(key, lengthOfText, railFence);

        // Fill rail matrix with cipherText to read it later
        fillUsingCipherText(cipherText, key, railFence);

        return extractPlainText(key, lengthOfText, railFence);
    }



    /*
                -- HELPER METHODS --
     */

    private static void checkInput(String text, int key) throws IllegalArgumentException {
        if(text == null || text.length() <= 1) {
            throw new IllegalArgumentException("plainText's length must be >= 2.");
        } if(key <= 1 || key >= text.length()) {
            throw new IllegalArgumentException("Key for RailFence greater than 1 and less than 1/2 of the message length.");
        }

        int lengthOfText = text.length();

        if(lengthOfText/key == 0) {
            throw new IllegalArgumentException("Text can not be encrypted using the key (" + key + ").");
        }
    }

    private static void fillUsingPlainText(String plainText, int key, char[][] railFence) {
        int lengthOfText = plainText.length();

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
                if(railFence[i][j] != 0) // If cell is NOT empty
                    cipherBuilder.append(railFence[i][j]);

        return cipherBuilder.toString();
    }

    private static void markRailFence(int key, int lengthOfText, char[][] railFence) {
        boolean directionDown = true;

        int rowIndex = 0;
        int columnIndex = 0;

        for(int i=0; i<lengthOfText; i++) {

            if(rowIndex == 0)
                directionDown = true;
            else if(rowIndex == key-1)
                directionDown = false;

            railFence[rowIndex][columnIndex] = '+';
            columnIndex++;

            if(directionDown)
                rowIndex++;
            else
                rowIndex--;
        }
    }

    private static void fillUsingCipherText(String cipherText, int key, char[][] railFence) {
        int lengthOfText = cipherText.length();
        char[] cipherCharacters = cipherText.toCharArray();

        int index = 0;

        for(int i=0; i<key; i++)
            for(int j=0; j<lengthOfText; j++)
                if(railFence[i][j] == '+'/* && index < lengthOfText*/)
                    railFence[i][j] = cipherCharacters[index++];
    }

    private static String extractPlainText(int key, int lengthOfText, char[][] railFence) {
        boolean directionDown = true;

        int rowIndex = 0;
        int columnIndex = 0;

        StringBuilder plainBuilder = new StringBuilder();

        for(int i=0; i<lengthOfText; i++) {
            if(rowIndex == 0)
                directionDown = true;
            else if(rowIndex == key-1)
                directionDown = false;

            plainBuilder.append(railFence[rowIndex][columnIndex++]);

            if(directionDown)
                rowIndex++;
            else
                rowIndex--;
        }

        return plainBuilder.toString();
    }
}
