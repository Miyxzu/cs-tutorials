package blockchain;

import java.security.*;

public class TransactionOutput {
    public String id;
    public PublicKey reciepient;
    public float val;
    public String parentTransactionID;

    public TransactionOutput(PublicKey reciepient, float val, String parentTransactionID) {
        this.reciepient = reciepient;
        this.val = val;
        this.parentTransactionID = parentTransactionID;
        this.id = StringUtil.applySHA256(StringUtil.getStrFromKey(reciepient) + Float.toString(val) + parentTransactionID);
    }

    public Boolean isMine(PublicKey key) {
        return (key == reciepient);
    }
}
