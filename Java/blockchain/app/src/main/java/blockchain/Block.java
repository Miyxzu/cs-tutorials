package blockchain;

import java.util.*;

public class Block {
    public String hash;
    public String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    private long timeStamp;
    private int nonce;

    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calcHashString();
    }

    public String calcHashString() {
        String calculatedHash = StringUtil.applySHA256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + merkleRoot);
        return calculatedHash;
    }
    
    public void mineBlock(int diff) {
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        // String target = StringUtil.getDiffString(diff);
        String target = new String(new char[diff]).replace('\0', '0');
        while (!hash.substring(0, diff).equals(target)) {
            nonce++;
            hash = calcHashString();
        }
        
        System.out.println("Block mined: " + hash);
    }

    public Boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        if (previousHash != "0") {
            if (!transaction.processTransaction()) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction added to block.");
        return true;
    }
}
