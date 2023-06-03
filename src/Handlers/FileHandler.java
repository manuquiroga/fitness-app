package Handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileHandler {


    public static void saveInFile(JSONObject obj, String fileName) {
        try {
            FileWriter file = new FileWriter(fileName+".json");
            file.write(obj.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String read(String file)
    {
        String content = "";
        try
        {
            content = new String(Files.readAllBytes(Paths.get(file+".json")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    public static boolean existsFile(String fileName) throws IOException {
        File file = new File(fileName+".json");

        if(file.exists())
        {
            return true;
        }
        return false;
    }
}
