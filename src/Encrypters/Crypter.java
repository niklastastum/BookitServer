package Encrypters;

/**
 * Created by Tastum on 17/10/2016.
 */

import java.util.Random;

public class Crypter {

//  XOR-krypteringsmetoden. Lånt fra andet sted
//    Copyright: https://github.com/KyleBanks/XOREncryption/blob/master/Java%20(Android%20compatible)/XOREncryption.java
    public static String encryptDecryptXOR(String input) {
        char[] key = {'T', 'C', 'P'}; //Dette kan være alle andre bogstaver end a,b og c.
        StringBuilder output = new StringBuilder();

        //For-loop der scrambler den String, der bliver indtastet
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }

        return output.toString();
    }

//  Metoden til at oprette en token. Kriterier til denne står i TokenController.java
    public static String buildToken(String chars, int length) {
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
    }

}
