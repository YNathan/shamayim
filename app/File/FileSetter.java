package File;

import java.io.*;

/**
 * Created by jacky on 28/11/2016.
 */
public class FileSetter {

    public void CreateFolder(String szUserAddress) {
        File theDir;
        theDir = new File(System.getProperty("user.dir") + "\\HousesDocuments\\" + szUserAddress);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + szUserAddress);
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
    }
}
