package Handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileHandler {


    public static void saveInFile(JSONObject obj, String fileName) {
        try {
            File file = new File(fileName + ".json");
            JSONArray jsonArray;

            if (file.exists()) {
                String content = read(fileName);
                jsonArray = new JSONArray(content);
            } else {
                jsonArray = new JSONArray();
            }

            jsonArray.put(obj);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonArray.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
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
