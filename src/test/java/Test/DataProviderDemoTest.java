package Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class DataProviderDemoTest
{
    @DataProvider(name = "basicData")
    public Object[][] basicData()
    {
     return new Object[][] { {"DUDCHSN","Secure!@#"}, {"DUDCHSN1","Secure!@#"}, {"DUDCHSN2","Secure!@#"} };
    }

    @Test(dataProvider = "basicData")
    public void testFillForm(String username, String password)
    {
      System.out.println(username);
      System.out.println(password);

    }
    @DataProvider(name = "hashMapData")
    public Object[][] basicHashMapData()
    {
        HashMap<String, String> user1= new HashMap<>();
        user1.put("username","DUDCHSN");
        user1.put("password","Secure!@#");

        HashMap<String, String> user2= new HashMap<>();
        user2.put("username","DUDCHSN");
        user2.put("password","Secure!@#");

        return new Object[][] { {user1}, {user2}};
    }

    @Test(dataProvider = "hashMapData")
    public void testwithHashMap(HashMap<String, String> data)
    {
        System.out.println(data.get("username"));
        System.out.println(data.get("password"));
    }

    //Utility File
    @DataProvider(name = "jsonData")
    public Object[][] jsonData() throws IOException {
        String jsonContent = new String(Files.readAllBytes(
                Paths.get(System.getProperty("user.dir")
                        + "/src/test/resources/testData_TC1.json")));
        Type type = new TypeToken<List<HashMap<String, String>>>() {}.getType();
        List<HashMap<String, String>> list =
                new Gson().fromJson(jsonContent, type);
        Object[][] table = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            table[i][0] = list.get(i);
        }
        return table;
    }
    @Test(dataProvider = "jsonData")
    public void json_data(HashMap<String, String> data)
    {
        System.out.println(data.get("username"));
        System.out.println(data.get("password"));
    }
}