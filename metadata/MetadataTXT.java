package metadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MetadataTXT implements Metadata {
    private File file;
    private String description;
    private String imageFile;
    private String resolution;
    private String[] resolutionArray = new String[2];

    MetadataTXT(File file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public String getImageFile() {
        return imageFile;
    }

    public String getResolutionUnit() {
        if (resolution != null) {
            resolutionArray = resolution.split("(?i)(?=[a-z])", 2);
            return resolutionArray[1];
        } else {
            return resolutionArray[1] = null;
        }
    }

    public String getResolutionNumber() {
        if (resolution != null) {
            resolutionArray = resolution.split("(?i)(?=[a-z])", 2);
            return resolutionArray[0];
        } else {
            return resolutionArray[0] = null;
        }
    }

    public void searchContent() {
        List<String> content = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                content.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.print("File not found" + e.getMessage());
        }
        for (String contents : content) {
            if (contents.contains(":")) {
                if (contents.startsWith("description")) {
                    String string = contents.substring(("description".length() + 1));
                    string = string.trim();
                    description = string;
                } else if (contents.startsWith("image-file")) {
                    String string = contents.substring(("image-file".length() + 1));
                    string = string.trim();
                    imageFile = string;
                } else if(contents.startsWith("resolution")) {
                    String string = contents.substring(("resolution".length() + 1));
                    string = string.trim();
                    resolution = string;
                }
            }
        }
    }
}