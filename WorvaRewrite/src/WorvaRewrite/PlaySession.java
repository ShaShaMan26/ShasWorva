package WorvaRewrite;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class PlaySession {
    private Boolean unicode;
    private String keyWord;

    public PlaySession() {
        unicode = false;
        keyWord = null;
    }

    public void setUnicode(Boolean u) {
        unicode = u;
    }
    public void setKeyWord(String k) {
        keyWord = k;
    }

    public Boolean getUnicode() {
        return unicode;
    }
    public String getKeyWord() {
        return keyWord;
    }

    public void generateKeyWord() throws IOException {
        Map<String, String> env = System.getenv();
        String path = env.get("WORD_LIST");
        if (path == null) throw new NullPointerException();

        File keyWordList = new File(path);

        Scanner keyWordListLengthReader = new Scanner(keyWordList);
        int keyWordListLength = -1;
        while (keyWordListLengthReader.hasNextLine()) {
            keyWordListLengthReader.nextLine();
            keyWordListLength++;
        }
        keyWordListLengthReader.close();

        int keyWordIndexNum = (int)(Math.random() * keyWordListLength);
        Scanner keyWordListReader = new Scanner(keyWordList);
        for (int i = 0; i != keyWordIndexNum; i++) {
            keyWordListReader.nextLine();
        }

        setKeyWord(keyWordListReader.next().toUpperCase());

        keyWordListReader.close();
    }
}
