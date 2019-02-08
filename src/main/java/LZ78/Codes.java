package LZ78;

import java.util.ArrayList;

public class Codes {
    private ArrayList<Code> codes = new ArrayList<>();

    public void add(Code code) {
        try {
            codes.add(code);
        } catch (Exception exc) {
            System.out.println("Error: " + exc.getMessage());
        }
    }

    public Code get(int index) {
        Code code = null;

        code = codes.get(index);

        return code;
    }

    public int getSize() {
        return codes.size();
    }

    public void showall() {
        for (Code code : codes) {
            System.out.println(code.toString());
        }
    }
}
