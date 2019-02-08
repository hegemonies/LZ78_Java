package LZ78;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<String> dictionary = new ArrayList<>(65535);

    public Dictionary() {
        this.add("");
    }

    public void add(String str) {
        dictionary.add(str);
    }

    public int searchIndexSubString(String fullStr) {
//        if (dictionary.size() == 0) {
//            return -1;
//        }

        if (fullStr.length() < 2) {
            return 0;
        }

        String subStr = fullStr.substring(0, fullStr.length() - 1);

        for (String str : dictionary) {
            if (subStr.equals(str)) {
               return dictionary.indexOf(str);
            }
        }

        return 0;
    }

    public boolean contains(String str) {
        if (str == null) {
            return false;
        }

        return dictionary.contains(str);
    }

    public String get(int index) {
        return dictionary.get(index);
    }

    public void showAll() {
        for (String str : dictionary) {
            System.out.println(str);
        }
    }

    public String getAll() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : dictionary) {
            stringBuilder.append(string);
        }

        return stringBuilder.toString();
    }
}
