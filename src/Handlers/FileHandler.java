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

/**
 * This class handles the files of the project
 */
public class FileHandler {


    /**
     * Rewrites the file that's specified by the String passed as a parameter.
     * @param fileName is a String that indicates which file the method will rewrite.
     * @param jsonObject is the object that will be added to the file.
     */
    public static void rewriteFile(JSONObject jsonObject, String fileName) {
        try {
            FileWriter newFile = new FileWriter(fileName + ".json");
            newFile.write(jsonObject.toString());
            newFile.flush();
            newFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a JSONObject in the specified file. The object is saved as the last one in the file.
     * @param fileName is a String that indicates which file the method will be adding the object to.
     * @param jsonObject is the object that will be added to the file.
     */
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

    /**
     * Retrieves the content of the specified file.
     * @param fileName is a String that indicates which file the method will retrieve the information from.
     * @return returns the information as a String to be used in other operations.
     */
    public static String read(String fileName)
    {
        String content = "";
        try
        {
            content = new String(Files.readAllBytes(Paths.get(fileName+".json")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * Checks if a file exists.
     * @param fileName is a String that indicates which file the method will check.
     * @return a boolean indicating whether the specified file exists or not.
     */
    public static boolean existsFile(String fileName) throws IOException {
        File file = new File(fileName+".json");
        return file.exists();
    }



}
