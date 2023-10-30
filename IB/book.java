import java.util.Random;

public class book {
    private String book;
    private String author;
    private boolean status;
    private int copies;
    private String isbn;
    private Random rand;

    public book(String book, String author, boolean status, int copies) {
        String isbnCheck = "";

        this.book = book;
        this.author = author;
        this.status = status;
        this.copies = copies;

        while (!checkISBN(isbnCheck)) {
            isbnCheck = genISBN();
        }

        this.isbn = isbnCheck;
    }

    private Boolean checkISBN(String isbn) {
        if (isbn.length() != 13) {
            return false;
        }

        int sum = 0;
        int lastDigit = 0;
        for (int i = 0; i < isbn.length() - 1; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            if (i == isbn.length() - 1) {
                lastDigit = digit;
            }
            if (i % 2 == 0) {
                sum += (digit * 3);
            } else {
                sum += digit;
            }
        }

        int checkSum = (10 - (sum % 10)) % 10;

        if (lastDigit == checkSum) {
            return true;
        } else {
            return false;
        }
    }

    private String genISBN() {
        int firstDigit = rand.nextInt(10);
        StringBuilder isbnBuilder = new StringBuilder(String.valueOf(firstDigit));
        
        for (int i = 0; i < 12; i++) {
            int digit = rand.nextInt(10);
            isbnBuilder.append(digit);
        }
        
        String isbn = isbnBuilder.toString();
        return isbn;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getISBN() {
        return isbn;
    }

    public String toString() {
        return String.format("%-15s %-15s %-8s %-8s", book, author, status, copies, '\n');
    }
}
