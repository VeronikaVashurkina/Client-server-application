import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Functions {



    public String getDES() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String randomSymbols="sfrergr";
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
         Cipher ecipher = Cipher.getInstance("DES");

        ecipher.init(Cipher.ENCRYPT_MODE, key);


        byte[] str1 = randomSymbols.getBytes();
        byte[] enc = ecipher.doFinal(str1);

       return (enc.toString());


    }
    public   String encryptPassword(String password)
    {
        String md2 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("MD2");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            md2 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return md2;
    }

    public   String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
