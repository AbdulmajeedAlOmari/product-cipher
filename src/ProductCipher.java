/**
 * This class will contain two main methods (Encrypt/Decrypt) to allow multi level (Encryption/Decryption)
 *
 * Note: We will create a product-cipher of Substitution and Transposition, so, a combination of the two types of ciphers
 */
public class ProductCipher {
    public static void main(String[] args) {
        // 97 to 122 (a-z)

        System.out.println("Rail-Fence encryption: " + RailFenceCipher.Encrypt("MAKE HASTE SLOWLY", 2));
    }

    public static String Encrypt(String plainText) {
        // TODO: Encrypt message using two different algorithms (Transposition + Diffusion)

        return "";
    }

    public static String Decrypt(String cipherText) {
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
}
