package Lesson12.server.File;

import java.io.*;

public class FileHandlers {



    public static void writeMessageLog(String message) throws IOException {
        File dir = new File("FileDir");
        File file = new File(dir,"MessageLog.txt");
        fileCheck(file, dir);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
            out.append(message);
            out.append("\n");
        }
    }

    public static void writeClientMessageLog(String message) throws IOException {
        File dir = new File("FileDir");
        File file = new File(dir,"ClientMessageLog.txt" );
        fileCheck(file, dir);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file ,true))) {
            out.append(message);
            out.append("\n");
        }
    }

    /**
     * последние 100 сообщений чата
     * @return
     * @throws IOException
     */
    public static String storyChat() throws IOException {
/*        File dir = new File("FileDir");
        File file = new File(dir, "ClientMessageLog.txt");
        fileCheck(file, dir);
        String storyChat = "";


        //try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        RandomAccessFile reader = new RandomAccessFile(file, "r");

        for (int j = 0; reader.readLine() != null; j++) {
            reader.seek(j);
        }
        for (int i = 0; i != 100; i++) {
            int n = 0;
            while (true) {
                reader.seek(n);

                char c = (char) reader.read();
                System.out.println(reader.readLine());
                if (reader.read() == '\n') {
                    break;
                }
                n++;
            }
            System.out.println(reader.readLine());
        }

        String readStr = reader.readLine();
        if (readStr != null && !readStr.startsWith("/")) {
            storyChat = storyChat + readStr + "\n";
        }


        return storyChat;*/
        return null;
    }

    private static void fileCheck(File file, File dir) throws IOException {
        if (!dir.exists()){
            dir.mkdirs();
        }
        if (!file.exists()){
            file.createNewFile();
        }
    }
    
}
