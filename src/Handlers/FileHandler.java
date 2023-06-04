package Handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileHandler {


    /*public static void saveInFile(JSONObject jsonObject, String fileName) {
        try {
            JSONArray jsonArray;
            Path pathFile = Path.of(fileName + ".json");
            if (Files.exists(pathFile)) {
                String existingContent = new String(Files.readAllBytes(pathFile));
                jsonArray = new JSONArray(existingContent);
            } else {
                jsonArray = new JSONArray();
            }
            jsonArray.put(jsonObject);

            try (FileWriter fileWriter = new FileWriter(fileName + ".json")) {
                fileWriter.write(jsonArray.toString());
                fileWriter.flush();
                System.out.println("JSONObject added to the file successfully.");
            } catch (IOException e) {
                System.err.println("IO 1: "+e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("IO 2: "+e.getMessage());
        } catch (JSONException e) {
            System.err.println("JSON 1: "+e.getMessage());
        }
    } */

    public static void saveInFile(JSONObject jsonObject, String fileName) {
        try {
            JSONObject fileData;
            fileName = fileName.concat(".json");
            final Path filePath = Paths.get(fileName);
            if (Files.exists(filePath)) {
                String existingContent = new String(Files.readAllBytes(filePath));
                fileData = new JSONObject(existingContent);
            } else {
                fileData = new JSONObject();
                fileData.put("data", new JSONArray());
            }

            JSONArray jsonArray = fileData.getJSONArray("data");
            jsonArray.put(jsonObject);
            fileData.put("data", jsonArray);

            try (FileWriter fileWriter = new FileWriter(fileName)) {
                fileWriter.write(fileData.toString());
                fileWriter.flush();
                System.out.println("JSONObject added to the file successfully.");
            } catch (IOException e) {
                System.err.println("IO Save in file error 1: "+e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("IO Save in file error 2: "+e.getMessage());
        } catch (JSONException e) {
            System.err.println("JSON Save in file error: "+e.getMessage());
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
        return file.exists();
    }



}
