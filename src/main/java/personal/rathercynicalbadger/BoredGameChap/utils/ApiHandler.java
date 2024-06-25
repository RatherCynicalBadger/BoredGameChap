package personal.rathercynicalbadger.BoredGameChap.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class ApiHandler {

    private final String URI_BASE = "https://boardgamegeek.com/xmlapi/";
    private final String URI_SEARCH = "search?search=";
    private final DocumentBuilder docBuilder;

    public ApiHandler() throws ParserConfigurationException {
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    public List<Game> apiSearchByTitle(String title) throws URISyntaxException, IOException, SAXException {
        URI uri = new URI(URI_BASE + URI_SEARCH + title);
        Document dom = docBuilder.parse(uri.toURL().openStream());
        return null;
    }
}
