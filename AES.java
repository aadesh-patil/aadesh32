import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AES {

    private static final String ALGORITHAM="AES";
    private static final byte[] keyValue="4528654354332688".getBytes();

    public static Key generateKey(){
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHAM);
        return key;
    }

    public static String encypt(String originalStr,Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher=Cipher.getInstance(ALGORITHAM);
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] aFinal = cipher.doFinal(originalStr.getBytes());
        byte[] encode = new Base64().encode(aFinal);
        return new String(encode);
    }

    public static String decode(String encryptStr,Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher=Cipher.getInstance(ALGORITHAM);
        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] decode = new Base64().decode(encryptStr.getBytes());
        byte[] doFinal = cipher.doFinal(decode);

        return new String(doFinal);
    }

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        String str = encypt("my love", generateKey());
        System.out.println(str);
        String s = decode(str, generateKey());
        System.out.println(s);
    }
}
