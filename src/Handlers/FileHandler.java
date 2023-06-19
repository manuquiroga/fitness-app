package Handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileHandler {


    public static void rewriteFile(JSONObject object, String file) {
        try {
            FileWriter newFile = new FileWriter(file + ".json");
            newFile.write(object.toString());
            newFile.flush();
            newFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
                fileData.put("premium_users", new JSONArray());
                fileData.put("basic_users", new JSONArray());
                fileData.put("admin_users",new JSONArray());
            }

            JSONArray jsonArrayPremium = fileData.getJSONArray("premium_users");
            JSONArray jsonArrayBasic = fileData.getJSONArray("basic_users");
            JSONArray jsonArrayAdmin = fileData.getJSONArray("admin_users");

            if(jsonObject.has("diets_generated")){
                jsonArrayPremium.put(jsonObject);
                fileData.put("premium_users", jsonArrayPremium);
            }else if (jsonObject.has("position")) {
                jsonArrayAdmin.put(jsonObject);
                fileData.put("admin_users", jsonArrayAdmin);
            } else{
                jsonArrayBasic.put(jsonObject);
                fileData.put("basic_users",jsonArrayBasic);
            }

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
