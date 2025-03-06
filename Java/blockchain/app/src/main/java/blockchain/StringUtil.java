package blockchain;

import java.security.*;
import java.util.ArrayList;
import java.util.Base64;

public class StringUtil {
    public static String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexStr = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexStr.append('0');
                }
                hexStr.append(hex);
            }
            return hexStr.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] applyECDSASig(PrivateKey privKey, String str) {
        Signature dsa;
        byte[] out = new byte[0];
        try {
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privKey);
            byte[] strByte = str.getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            out = realSig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    public static Boolean verifyECDSASig(PublicKey pubKey, String data, byte[] sig) {
        try {
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(pubKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(sig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStrFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static String getMerkleRoot(ArrayList<Transaction> transactions) {
        int count = transactions.size();
        ArrayList<String> prevTreeLayer = new ArrayList<>();
        for (Transaction t : transactions) {
            prevTreeLayer.add(t.transactionID);
        }
        
        ArrayList<String> treeLayer = prevTreeLayer;
        while (count > 1) {
            treeLayer = new ArrayList<>();
            for (int i = 1; i < prevTreeLayer.size(); i++) {
                treeLayer.add(applySHA256(prevTreeLayer.get(i - 1) + prevTreeLayer.get(i)));
            }
            count = treeLayer.size();
            prevTreeLayer = treeLayer;
        }
        return (treeLayer.size() == 1) ? treeLayer.get(0) : "";
    }
}
