package utils;

import java.io.IOException;
import java.util.Properties;

public class SiteUrl {
    private static Properties properties = new Properties();
    static{
        try {
            // 如果在src路径下       指定路径即可  resources前不加 /
            properties.load(SiteUrl.class.getClassLoader().getResourceAsStream("resources/siteurl.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readUrl(String key){
        return (String)properties.get(key);
    }


    public static void main(String[] args) {
        String url = SiteUrl.readUrl("control.brand.list");
        System.out.println(url);
    }
}
