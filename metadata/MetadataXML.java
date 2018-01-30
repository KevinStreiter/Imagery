package metadata;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class MetadataXML implements Metadata {
    private File file;
    private String description;
    private String imageFile;
    private String resolutionNumber;
    private String resolutionUnit;

    MetadataXML(File file) throws IOException, ParserConfigurationException, SAXException {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public String getImageFile() {
        return imageFile;
    }

    public String getResolutionNumber() {
        return resolutionNumber;
    }

    public String getResolutionUnit() {
        return resolutionUnit;
    }

    public void searchContent() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("image");
            Node nNode = nList.item(0);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                description = element.getElementsByTagName("description").item(0).getTextContent();
                imageFile = element.getElementsByTagName("image-file").item(0).getTextContent();
                resolutionNumber = element.getElementsByTagName("resolution").item(0).getTextContent();
            }
            nList = document.getElementsByTagName("resolution");
            nNode = nList.item(0);
            Element element = (Element) nNode;
            resolutionUnit = element.getAttribute("unit");
        } catch (Exception e) {
            resolutionNumber = null;
            resolutionUnit = null;
        }
    }
}