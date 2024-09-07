package regex.adv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Html {
    public static Map<String, Double> tagStatistik(Path htmlDatei) throws IOException {
        if (!htmlDatei.toString().endsWith(".html")) {
            throw new IllegalArgumentException("Datei muss eine HTML-Datei sein");
        }
        BufferedReader br = Files.newBufferedReader(htmlDatei);
        String all = br.lines().reduce("", String::concat);
        String htmlall = all.toLowerCase();
        Pattern tagPattern = Pattern.compile("<\\s*([a-z0-9]+)(\\s|>)");
        Matcher matcher = tagPattern.matcher(htmlall);

        Map<String, Integer> tagCount = new HashMap<>();
        int totalTags = 0;

        while (matcher.find()) {
            String tag = matcher.group(1);
            tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
            totalTags++;
        }

        Map<String, Double> tagFrequency = new HashMap<>();
        for (Map.Entry<String, Integer> entry : tagCount.entrySet()) {
            tagFrequency.put(entry.getKey(), entry.getValue() / (double) totalTags);
        }

        br.close();
        return tagFrequency;
    }

    public static Set<String> domains(Path htmlDatei) throws IOException {
        Set<String> links = links(htmlDatei);
        Set<String> domains = new HashSet<>();
        Pattern domainPattern = Pattern.compile("([\\w\\-]+\\.[\\w\\-]+)(?=/)");
        links.forEach(link -> {
            Matcher matcher = domainPattern.matcher(link);
            if (matcher.find()) {
                domains.add(matcher.group());
            }
        });
        return domains;

    }

    public static Set<String> links(Path htmlDatei) throws IOException {
        if (!htmlDatei.toString().endsWith(".html")) {
            throw new IllegalArgumentException("Datei muss eine HTML-Datei sein");
        }

        BufferedReader br = Files.newBufferedReader(htmlDatei);
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line);
        }

        String htmlContent = content.toString();
        Pattern linkPattern = Pattern.compile("https?://[\\w\\-.]+(:\\d+)?(/[\\w\\-./?%&=]*)?");
        Matcher matcher = linkPattern.matcher(htmlContent);

        Set<String> links = new HashSet<>();
        while (matcher.find()) {
            links.add(matcher.group());
        }

        br.close();
        return links;
    }

    public static Set<String> klassen(Path cssDatei) throws IOException {
        if (!cssDatei.toString().endsWith(".css")) {
            throw new IllegalArgumentException("Datei muss eine CSS-Datei sein");
        }

        BufferedReader br = Files.newBufferedReader(cssDatei);
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line);
        }

        String cssContent = content.toString();
        Pattern classPattern = Pattern.compile("\\.([a-zA-Z0-9_-]+)(?=\\s*\\{)");
        Matcher matcher = classPattern.matcher(cssContent);

        Set<String> classes = new HashSet<>();
        while (matcher.find()) {
            classes.add(matcher.group(1));
        }

        br.close();
        return classes;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        //URL url = new URI("https://www.htlrennweg.at/").toURL();
        URL url = new URI("https://example.com").toURL();
        Path tempFile = downloadUrlToTempFile(url, ".html");
        Path tempCss = downloadUrlToTempFile(new URI("https://www.baeldung.com/wp-content/cache/autoptimize/1/css/autoptimize_5233fd04e10202ef724e3ec987b20bb0.css").toURL(), ".css");
        System.out.println(tagStatistik(tempFile));
        System.out.println(links(tempFile));
        System.out.println(domains(tempFile));
        System.out.println(klassen(tempCss));
        Files.delete(tempFile);
        Files.delete(tempCss);
    }

    private static Path downloadUrlToTempFile(URL url, String suffix) throws IOException {
        Path tempFile = Files.createTempFile("temp", suffix);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())); BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardOpenOption.WRITE)) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
        return tempFile;
    }
}
