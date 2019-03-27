import java.util.ArrayList;

public class MessageBuilder {

    /**
     * This method is currently used to convert a message for Rail-Fence encryption algorithm.
     *
     * @param key
     * @param firstCharacters: Can be encrypted/decrypted characters with spaces
     * @param secondCharacters: Can be encrypted/decrypted characters without spaces
     * @return a cipher text with spaces
     */
    public static String convertMessageToString(int key, char[] firstCharacters, char[][] secondCharacters) {
        StringBuilder cipherTextBuilder = new StringBuilder(); // Will contain the cipher text from encryptedCharacters
        int counter = 0;
        int secondaryCounter = 0;

        int numOfRows = secondCharacters.length;
        int numOfColumns = secondCharacters[0].length;

        char[] currentRow = null;
        int rowCounter = 0;

        while(counter < numOfColumns*numOfRows) {
            if(secondaryCounter < firstCharacters.length && firstCharacters[secondaryCounter] == ' ') {
                cipherTextBuilder.append(" ");
                secondaryCounter++;
                continue;
            }

            if(counter%numOfColumns == 0) {
                currentRow = secondCharacters[rowCounter++];
            }

            char currentChar = currentRow[counter%numOfColumns];

            cipherTextBuilder.append(currentRow[counter%numOfColumns]);

            counter++;

            // If not empty character, increment the secondaryCounter
            if(currentChar != 0)
                secondaryCounter++;
        }

        return cipherTextBuilder.toString();
    }
}
