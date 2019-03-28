/**
 * This class will contain two main methods (Encrypt/Decrypt) to allow multi level (Encryption/Decryption)
 *
 * Note: We will create a product-cipher of Substitution and Transposition, so, a combination of the two types of ciphers
 */
public class ProductCipher {
    public static void main(String[] args) {
        // 97 to 122 (a-z)

        String text = "MAKE HASTE SLOWLY";
        int key = 4;

        String encryptedText = RailFenceCipher.Encrypt(text, key);
        String decryptedCipher = RailFenceCipher.Decrypt(encryptedText, key);


        System.out.println("Original Text: (" + text + ")");
        System.out.println("Rail-Fence Encryption: (" + encryptedText + ")");
        System.out.println("Rail-Fence Decryption: (" + decryptedCipher + ")");
    }

    /***
     *
     * @param plainText: text to be encrypted.
     * @param railKey: requires a key belongs to [2, 1/2 of text length].
     * @param bookKey: requires a key as a string of at least 1 character.
     * @return returns encrypted text or Cipher Text.
     */
    public static String Encrypt(String plainText, int railKey, String bookKey) throws IllegalArgumentException {
//        checkInput()
        String railFenceCipher = RailFenceCipher.Encrypt(plainText, railKey);
        return "";
    }


    public static String Decrypt(String cipherText, int railKey, String bookKey) throws IllegalArgumentException {
        // TODO: Decrypt message using two different algorithms (Transposition + Diffusion)

        return "";
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
    }
}
