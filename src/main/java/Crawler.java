import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Crawler {

    private static final Logger LOGGER = Logger.getLogger(Crawler.class.getName());

    private static String TARGET_ELEMENT_ID = "make-everything-ok-button";

    public static void main(String[] args) {

        List<String> arguments = Arrays.stream(args).collect(Collectors.toList());

        if (arguments.isEmpty()) {
            System.out.println("Sorry, Bye!");
            return;
        }

        if (! (new File(arguments.get(0)).exists())) {
            TARGET_ELEMENT_ID = arguments.get(0);
            arguments.remove(0);
        }

        arguments.forEach(Crawler::printResult);
    }

    private static void printResult(String filePath) {
        Optional<Element> buttonOpt = findElementById(new File(filePath));

        Element element = buttonOpt.orElseThrow(() -> {
            String message = String.format("Element with id: %s not found", TARGET_ELEMENT_ID);
            LOGGER.warning(message);
            return new RuntimeException(message);
        });

        Deque<String> parents = new LinkedList<>();
        Element parent = element.parent();
        while (parent != null && !parent.tagName().contains("root")) {
            parents.addFirst(parent.tagName());
            parent = parent.parent();
        }
        parents.addLast(element.tagName());

        LOGGER.info(String.format("Target element path: [%s]", String.join(" > ", parents)));
    }

    private static Optional<Element> findElementById(File htmlFile) {
        try {
            String CHARSET_NAME = "utf8";
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            return Optional.ofNullable(doc.getElementById(TARGET_ELEMENT_ID));

        } catch (IOException e) {
            LOGGER.warning(String.format("Error reading [%s] file as %s", htmlFile.getAbsolutePath(), e.getMessage()));
            return Optional.empty();
        }
    }
}
