import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Poligon {
    public static void main() {
        try {
            Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
            //doc.select("div[id='cont']").replaceAll();
        }
        catch (IOException e) {
            System.out.println("hujnia happened");
        }

        try (Stream<Path> paths = Files.walk(Paths.get("/home/you/Desktop"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        }
        catch (IOException e) {
            System.out.println("hujnia happened");
        }
    }
}
