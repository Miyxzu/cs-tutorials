package blockchain;

public class TransactionInput {
    public String transactionOutputID;
    public TransactionOutput UTXO;

    public TransactionInput(String transactionOutputID) {
        this.transactionOutputID = transactionOutputID;
    }
}