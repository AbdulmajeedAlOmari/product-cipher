import java.text.ParseException;
import java.util.Scanner;

/**
 * This class will contain two main methods (Encrypt/Decrypt) to allow multi level (Encryption/Decryption)
 *
 * Note: We will create a product-cipher of Substitution and Transposition, so, a combination of the two types of ciphers
 */
public class ProductCipher {
    public static void main(String[] args) {
        // 97 to 122 (a-z)

//        String text = "MAKE HASTE SLOWLY";
//        int railKey = 4;
//        String bookKey = "AWeSoMeKEYAWeSoMeKEYAWeSoMeKEYAWeSoMeKEY";
//
//        bookKey = bookKey.toUpperCase();
//
//        String encryptedText = Encrypt(text, railKey, bookKey);
//        String decryptedCipher = Decrypt(encryptedText, railKey, bookKey);

        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println("_---=== Welcome to our ProductCipher ===---_");
        do {
            System.out.println("----------------- Choose -----------------");
            System.out.println("- Type (1) to -> Encrypt a message");
            System.out.println("- Type (2) to -> Decrypt a cipher text");
            System.out.println("- Or (-1) to -> Exit");
            System.out.print("- Choice > ");
            input = scanner.nextLine();

            if(input.equals("1")) {
                System.out.print("Message: ");
                String message = scanner.nextLine();

                while (message.length() < 1) {
                    System.out.println("Please fill in a valid text to be encrypted.");
                    System.out.print("Message: ");
                    message = scanner.nextLine();
                }

                boolean hasKeyError; // To retry input if failed
                int railKey = 0;
                do {
                    System.out.print("Key for rail-fence (Integer): ");
                    String railKeyAsString = scanner.nextLine();

                    try {
                        railKey = Integer.parseInt(railKeyAsString);
                        hasKeyError = false;
                    } catch(Exception e) {
                        System.out.println("Please fill in an Integer, not a String.");
                        hasKeyError = true;
                    }
                } while(hasKeyError);
                System.out.print("Key for book-cipher (String): ");
                String bookKey = scanner.nextLine();

                try {
                    checkInput(message, railKey, bookKey);
                } catch (IllegalArgumentException e) {
                    System.out.println("\n"+e.getMessage()+"\n");
                    continue;
                }

                String encryption = ProductCipher.Encrypt(message, railKey, bookKey);
                System.out.println("\nYour encrypted message is: ("+ encryption +")\n");
            } else if(input.equals("2")) {
                System.out.print("Cipher text: ");
                String cipherText = scanner.nextLine();

                while (cipherText.length() < 1) {
                    System.out.println("Please fill in a valid text to be encrypted.");
                    System.out.print("Message: ");
                    cipherText = scanner.nextLine();
                }

                boolean hasKeyError; // To retry input if failed
                int railKey = 0;
                do {
                    System.out.print("Key for rail-fence (Integer): ");
                    String railKeyAsString = scanner.nextLine();

                    try {
                        railKey = Integer.parseInt(railKeyAsString);
                        hasKeyError = false;
                    } catch(Exception e) {
                        System.out.println("Please fill in an Integer, not a String.");
                        hasKeyError = true;
                    }
                } while(hasKeyError);
                System.out.print("Key for book-cipher (String): ");
                String bookKey = scanner.nextLine();

                try {
                    checkInput(cipherText, railKey, bookKey);
                } catch (IllegalArgumentException e) {
                    System.out.println("\n"+e.getMessage()+"\n");
                    continue;
                }

                String decryption = ProductCipher.Decrypt(cipherText, railKey, bookKey);
                System.out.println("\nYour decrypted cipher text is: ("+ decryption +")\n");
            } else if(!input.equals("-1")) {
                System.out.println("Please choose either (1) or (2) [Encrypt/Decrypt] a message.");
            }
        } while(!input.equals("-1"));

        System.out.println("Thank you for using our Product Cipher, bye!");
    }

    /***
     *
     * @param plainText: text to be encrypted.
     * @param railKey: requires a key belongs to [2, 1/2 of text length].
     * @param bookKey: requires a key as a string of at least 1 character.
     * @return returns encrypted text or Cipher Text.
     */
    public static String Encrypt(String plainText, int railKey, String bookKey) {
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
    public static String Decrypt(String cipherText, int railKey, String bookKey) {

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
        } else if(railKey < 1) {
            throw new IllegalArgumentException("Rail-Fence: key must be greater than or equal to 1.");
//        } else if(text.length()/railKey == 0) {
//            throw new IllegalArgumentException("Rail-Fence: text can not be encrypted using the key (" + railKey + ").");
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
