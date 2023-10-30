import java.util.Random;

public class book {
    private String book;
    private String author;
    private boolean status;
    private int copies;
    private int isbn;
    private Random rand;
    private int lowerBound = ;
    private int upperBound = ;

    public book(String book, String author, boolean status, int copies) {
        int temp;

        this.book = book;
        this.author = author;
        this.status = status;
        this.copies = copies;

        while (!checkISBN(temp)) {
            temp = rand.nextInt(lowerBound, upperBound);
        }

        this.isbn = temp;
    }

    private Boolean checkISBN(int isbn) {
        String numString = Integer.toString(isbn);
        int temp;
        int num;
        int sum = 0;
        int check;

        for (int i = 0; i < numString.length(); i++) {
            num = Integer.parseInt(numString.substring(i, i + 1));
            if (numString.substring(numString.length() - 1)) {
                check = num;
                break;
            }
            if (i % 2 == 0) {
                temp = num * 3;
                sum =+ temp;
            } else {
                temp = num * 1;
                sum =+ temp;
            }
        }

        if (sum % 10 != 0) {
            return false;
        } else {
            temp = sum % 10;
        }

        if((10 - temp) != check){
            return false;
        }

        return true;
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

    public int getISBN() {
        return isbn;
    }

    public void setISBN(int isbn) {
        this.isbn = isbn;
    }

    public String toString() {
        return String.format("%-15s %-15s %-8s %-8s", book, author, status, copies, '\n');
    }
}
