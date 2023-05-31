package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConnectionAPI{
    private final static String url = "https://api.api-ninjas.com/v1/nutrition?query=";

    public static String getData(String query) {
        try {
            String apiKey = "NiKoMIYePp969hwx/Esdhw==1oocBHa8GLRGQrag";
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String urlString = url + encodedQuery;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("X-Api-Key", apiKey);

            InputStream responseStream = connection.getInputStream();
            return readResponse(responseStream);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return "";
    }

    private static String readResponse(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}