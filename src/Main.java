import API.ConnectionAPI;

public class Main {
    public static void main(String[] args) {
        String query = "apple";
        String response = ConnectionAPI.getData(query);
        System.out.println(response);
    }
}