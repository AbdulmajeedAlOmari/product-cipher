public class BookCipher {

    static String Encrypt(String plainText, String bookKey) {
        StringBuilder cipherBuilder = new StringBuilder();
        plainText = plainText.toUpperCase();

        int j = 0;
        for(int i=0; i<plainText.length(); i++) {
            char plainCharacter = plainText.charAt(i);

            // If not a letter, do not encrypt and leave it as is.
            if(plainCharacter < 'A' || plainCharacter > 'Z') {
                cipherBuilder.append(plainCharacter);
                continue;
            }

            // Convert plainCharacter to cipherCharacter using Vigenere Cipher algorithm
            char cipherCharacter = (char)((plainCharacter + bookKey.charAt(j) - 2 * 'A') % 26 + 'A');
            cipherBuilder.append(cipherCharacter); // add it to the string

            j++; // Increment the key index
            j = j % bookKey.length(); // Check if out of bounds, reset to proper index
        }

        return cipherBuilder.toString();
    }



    static String Decrypt(String cipherText, String bookKey) {
        StringBuilder plainBuilder = new StringBuilder();
        cipherText = cipherText.toUpperCase();

        int j=0;
        for(int i=0; i<cipherText.length(); i++) {
            char cipherCharacter = cipherText.charAt(i);

            if(cipherCharacter < 'A' || cipherCharacter > 'Z') {
                plainBuilder.append(cipherCharacter);
                continue;
            }

            char plainCharacter = (char)((cipherCharacter - bookKey.charAt(j) + 26) % 26 + 'A');
            plainBuilder.append(plainCharacter);

            j = ++j % bookKey.length();
        }

        return plainBuilder.toString();
    }
}
