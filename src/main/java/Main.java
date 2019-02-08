import LZ78.LZ78;

public class Main {
    public static void main(String[] args) {
//        String to_fileName = "eng.txt";
//        String to_fileName = "crsto10.txt";
//        String to_fileName = "alice30.txt";
        String to_fileName = "alice_cut.txt";
        String to_fileName_mod = to_fileName.substring(0, to_fileName.length() - 4);

        LZ78 lz78 = new LZ78();
        try {
//            lz78.compress("input_test.txt", "output_compressed_test.txt");
            lz78.compress(to_fileName, to_fileName_mod + "_compressed.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Compressed");

        lz78.decompress(to_fileName_mod + "_compressed.txt", to_fileName_mod + "_decompressed.txt");

        System.out.println("Decompressed");
    }
}
