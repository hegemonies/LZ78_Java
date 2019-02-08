package LZ78;

public class Code {
    private int number;
    private char ch;

    public Code(int number, char ch) {
        this.number = number;
        this.ch = ch;
    }

    public int getNumber() {
        return number;
    }

    public char getCh() {
        return ch;
    }

    @Override
    public String toString() {
        return number + "," + ch;
    }
}
