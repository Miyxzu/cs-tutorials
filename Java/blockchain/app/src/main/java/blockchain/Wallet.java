package blockchain;

import java.security.*;
import java.security.spec.*;
import java.util.*;

public class Wallet {
    public PublicKey pubKey;
    public PrivateKey privKey;
    public HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();
    
    public Wallet() {
        genKeyPair();
    }

    public void genKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

            keyGen.initialize(ecSpec, rand);
            KeyPair keyPair = keyGen.generateKeyPair();

            pubKey = keyPair.getPublic();
            privKey = keyPair.getPrivate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Float getBalance() {
        float total = 0;
        for (Map.Entry<String, TransactionOutput> item: App.UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();
            if (UTXO.isMine(pubKey)) {
                UTXOs.put(UTXO.id, UTXO);
                total += UTXO.val;
            }
        }
        return total;
    }

    public Transaction sendFunds(PublicKey _recipient, Float val) {
        if(getBalance() < val) {
            System.out.println("// Not enough funds to send transaction. Transaction Discarded.");
            return null;
        }

        ArrayList<TransactionInput> inputs = new ArrayList<>();

        float total = 0;
        for (Map.Entry<String, TransactionOutput> item: UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();
            total += UTXO.val;
            inputs.add(new TransactionInput(UTXO.id));
            if (total > val) break;
        }

        Transaction newTransaction = new Transaction(pubKey, _recipient, val, inputs);
        newTransaction.genSig(privKey);

        for (TransactionInput i : inputs) {
            UTXOs.remove(i.transactionOutputID);
        }

        return newTransaction;
    }
}

