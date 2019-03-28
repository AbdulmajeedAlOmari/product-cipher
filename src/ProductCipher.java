/**
 * This class will contain two main methods (Encrypt/Decrypt) to allow multi level (Encryption/Decryption)
 *
 * Note: We will create a product-cipher of Substitution and Transposition, so, a combination of the two types of ciphers
 */
public class ProductCipher {
    public static void main(String[] args) {
        // 97 to 122 (a-z)

        String text = "MAKE HASTE SLOWLY";
        int railKey = 4;
        String bookKey = "AWeSoMeKEYAWeSoMeKEYAWeSoMeKEYAWeSoMeKEY";

        bookKey = bookKey.toUpperCase();

        String encryptedText = Encrypt(text, railKey, bookKey);
        String decryptedCipher = Decrypt(encryptedText, railKey, bookKey);


        System.out.println("Original Text: (" + text + ")");
        System.out.println("ProductCipher Encryption: (" + encryptedText + ")");
        System.out.println("ProductCipher Decryption: (" + decryptedCipher + ")");
    }

    /***
     *
     * @param plainText: text to be encrypted.
     * @param railKey: requires a key belongs to [2, 1/2 of text length].
     * @param bookKey: requires a key as a string of at least 1 character.
     * @return returns encrypted text or Cipher Text.
     */
    public static String Encrypt(String plainText, int railKey, String bookKey) throws IllegalArgumentException {
        checkInput(plainText, railKey, bookKey);

        String firstCipher = RailFenceCipher.Encrypt(plainText, railKey);
        return BookCipher.Encrypt(firstCipher, bookKey);
    }


    /***
     *
     * @param cipherText: text to be decrypted
     * @param railKey: a key used in rail-fence to decrypt cipherText
     * @param bookKey: a key of characters used in book-cipher to decrypt cipherText
     * @return returns decrypted text or Plain Text.
     * @throws IllegalArgumentException when given inputs are incorrect
     */
    public static String Decrypt(String cipherText, int railKey, String bookKey) throws IllegalArgumentException {
        checkInput(cipherText, railKey, bookKey);

        String firstPlain = BookCipher.Decrypt(cipherText, bookKey);
        return RailFenceCipher.Decrypt(firstPlain, railKey);
    }

    /***
     * Substitution Algorithm:
     *  Book Ciphers (Vigenère Tableau) [This?]
     *  Hill Cipher [or this?]
     *
     * Transposition Algorithm:
     *  Rail-Fence [FINISHED Encryption only]
     */

    private static void checkInput(String text, int railKey, String bookKey) throws IllegalArgumentException {
        if(text == null || text.length() < 1) {
            throw new IllegalArgumentException("Text's length must be >= 1.");
        } else if(railKey <= 1 || railKey >= text.length()) {
            throw new IllegalArgumentException("Rail-Fence: key must be greater than 1 and less than 1/2 of the message length.");
        } else if(text.length()/railKey == 0) {
            throw new IllegalArgumentException("Rail-Fence: text can not be encrypted using the key (" + railKey + ").");
        } else if(bookKey == null || bookKey.length() < 1) {
            throw new IllegalArgumentException("Book Cipher: key's length must be >= 1.");
        } else if(bookKey.contains(" ")) {
            throw new IllegalArgumentException("Book Cipher: key must not contain a space.");
        }

        bookKey = bookKey.toUpperCase();

        for (char c : bookKey.toCharArray()) {
            if(c < 'A' || c > 'Z')
                throw new IllegalArgumentException("Book Cipher: key can contain letters from A-Z only.");
        }
    }
}
