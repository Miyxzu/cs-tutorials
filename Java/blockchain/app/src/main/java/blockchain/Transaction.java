package blockchain;

import java.security.*;
import java.util.*;

public class Transaction {
    public String transactionID;
    public PublicKey sender;
    public PublicKey reciepient;
    public float val;
    public byte[] sig;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int seq = 0;

    public Transaction(PublicKey sender, PublicKey reciepient, float val, ArrayList<TransactionInput> inputs) {
        this.sender = sender;
        this.reciepient = reciepient;
        this.val = val;
        this.inputs = inputs;
    }

    private String calcHashString() {
        seq++;
        return StringUtil.applySHA256(
                StringUtil.getStrFromKey(sender) + 
                StringUtil.getStrFromKey(reciepient) + 
                Float.toString(val) + seq
                );
    }

    public void genSig(PrivateKey privateKey) {
        String data = StringUtil.getStrFromKey(sender) + StringUtil.getStrFromKey(reciepient) + Float.toString(val);
        sig = StringUtil.applyECDSASig(privateKey, data);
    }

    public Boolean verifySig() {
        String data = StringUtil.getStrFromKey(sender) + StringUtil.getStrFromKey(reciepient) + Float.toString(val);
        return StringUtil.verifyECDSASig(sender, data, sig);
    }

    public Boolean processTransaction() {
        if (!verifySig()) {
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }

        for (TransactionInput i : inputs) {
            i.UTXO = App.UTXOs.get(i.transactionOutputID);
        }

        if (getInputValue() < App.minTransaction) {
            System.out.println("#Transaction Inputs too small: " + getInputValue());
            return false;
        }

        float leftOver = getInputValue() - val;
        transactionID = calcHashString();
        outputs.add(new TransactionOutput(this.reciepient, val, transactionID));
        outputs.add(new TransactionOutput(this.sender, leftOver, transactionID));

        for (TransactionOutput o : outputs) {
            App.UTXOs.put(o.id, o);
        }

        for (TransactionInput i : inputs) {
            if (i.UTXO == null) continue;
            App.UTXOs.remove(i.UTXO.id);
        }
        
        return true;
    }

    public Float getInputValue() {
        float total = 0;
        for (TransactionInput i : inputs) {
            if (i.UTXO == null) continue;
            total += i.UTXO.val;
        }
        return total;
    }

    public Float getOutputValue() {
        float total = 0;
        for (TransactionOutput o : outputs) {
            total += o.val;
        }
        return total;
    }
}
