package metadata;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class SearchMetadata {
    private File file;
    private Metadata metadata;
    private String[] metadataNames = new String[4];

    public SearchMetadata(File file) {
        this.file = file;
    }

    public String[] getMetadata() throws ParserConfigurationException, SAXException, IOException {
        String fileAsString = file.getName();
        if(fileAsString.endsWith(".txt")) {
            metadata = new MetadataTXT(file);
            saveMetadata();
        } else if(fileAsString.endsWith(".xml")) {
            metadata = new MetadataXML(file);
            saveMetadata();
        }
        return metadataNames;
    }
    private void saveMetadata(){
        metadata.searchContent();
        metadataNames[0] = metadata.getDescription();
        metadataNames[1] = metadata.getImageFile();
        metadataNames[2] = metadata.getResolutionNumber();
        metadataNames[3] = metadata.getResolutionUnit();
    }
}