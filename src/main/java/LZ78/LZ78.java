package LZ78;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LZ78 {
    private Dictionary dictionary;
    private Codes codes;

    public void compress(String from_fileName, String to_fileName) {
        dictionary = new Dictionary();
        codes = new Codes();

        String raw = null;
        try {
            raw = fromFile(from_fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int indexOfCh = 0;
        StringBuilder buffer = new StringBuilder();
        int count = 0;
        int lenght = raw.length();

        for (char ch : raw.toCharArray()) {
            count++;
            buffer.append(ch);

            if (!dictionary.contains(buffer.toString())) {
                dictionary.add(buffer.toString());
                indexOfCh = dictionary.searchIndexSubString(buffer.toString());
                char lastChar;

                if (buffer.toString().length() < 2) {
                    lastChar = buffer.toString().charAt(0);
                } else {
                    lastChar = buffer.toString().charAt(buffer.length() - 1);
                }

                codes.add(new Code(indexOfCh, lastChar));

                buffer = new StringBuilder();
            } else if (count == lenght) {
                dictionary.add(buffer.toString());
                codes.add(new Code(0, buffer.toString().charAt(0)));
            }
        }

        codesToFile(to_fileName);
    }

    public void decompress(String from_fileName, String to_fileName) {
        dictionary = new Dictionary();
        codes = new Codes();

        String raw = "";
        try {
            raw = fromFile(from_fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] subraws = raw.split(";");

        for (String str : subraws) {
            int num = 0;
            char ch = 0;

            if (str.length() > 2 && str.toCharArray()[str.length() - 1] == str.toCharArray()[str.length() - 2]) {
                num = Integer.valueOf(str.substring(0, str.length() - 2));
                ch = ',';
            } else {
                String[] numberAndChar = str.split(",");

                try {
                    num = Integer.valueOf(numberAndChar[0]);
                    ch = numberAndChar[1].charAt(0);
                } catch (Exception exc) {
                    System.out.println("Error (" + str + "): " + exc.getMessage());
//                    return;
                }
            }

            codes.add(new Code(num, ch));

            if (num == 0) {
                dictionary.add(String.valueOf(ch));
            } else {
                String substr = dictionary.get(num);
                dictionary.add(substr + ch);
            }
        }

        dictionaryToFile(to_fileName);
    }

    private String fromFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
    }

    private void codesToFile(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < codes.getSize(); i++) {
            stringBuilder.append(codes.get(i).toString() + ";");
        }

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(stringBuilder.toString());
        } catch (Exception exc) {
            System.out.println("Error: " + exc.getMessage());
        }
    }

    private void dictionaryToFile(String fileName) {
        String total = dictionary.getAll();

        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
