package personal.rathercynicalbadger.BoredGameChap.service.impementation;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;
import personal.rathercynicalbadger.BoredGameChap.service.BGGAPIService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

@Service
public class BGGAPIServiceImp implements BGGAPIService {
    private final String BGG_URL = "https://boardgamegeek.com/xmlapi/";
    private final String BGG_SEARCH = "search?search=";
    private final String BGG_BOARDGAME = "boardgame/";

    @Override
    public Map<Integer, String> searchByTitle(String title) {
        try {
            Map<Integer, String> result = new HashMap<>();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = builder.parse(BGG_URL + BGG_SEARCH + title);
            doc.getDocumentElement().normalize();
            Element element = doc.getDocumentElement();
            NodeList list = element.getElementsByTagName("boardgame");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    result.put(
                            Integer.parseInt(elem.getAttribute("objectid")),
                            ((Element) node).getElementsByTagName("name").item(0).getTextContent()
                    );
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Couldn't connect to API. Message" + e.getMessage());
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Game fetchGame(Integer bggId) {
        try {
            Game game = new Game();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(BGG_URL + BGG_BOARDGAME + bggId);
            doc.getDocumentElement().normalize();
            Element element = doc.getDocumentElement();
            game.setTitle(element.getElementsByTagName("name").item(0).getTextContent());
            game.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
            game.setPlaytime(Integer.parseInt(element.getElementsByTagName("playingtime").item(0).getTextContent()));
            game.setMinPlayers(Byte.parseByte(element.getElementsByTagName("minplayers").item(0).getTextContent()));
            game.setMaxPlayers(Byte.parseByte(element.getElementsByTagName("maxplayers").item(0).getTextContent()));
            game.setReleased(Year.parse(element.getElementsByTagName("yearpublished").item(0).getTextContent()));
            return game;
        } catch (IOException e) {
            throw new RuntimeException("Couldn't connect to API. Message" + e.getMessage());
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
