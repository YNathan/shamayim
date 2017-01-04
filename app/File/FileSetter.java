package File;

import BL.setterBL;
import Entity.ESuccessFailed;
import Entity.WebResponce;
import play.mvc.Http;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jacky on 28/11/2016.
 */
public class FileSetter {
    private WebResponce webResponce = new WebResponce();
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
    public WebResponce setFiles(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        Iterator<Http.MultipartFormData.FilePart> filePartIterator = pictures.iterator();
        while (filePartIterator.hasNext()) {
            Http.MultipartFormData.FilePart picture = filePartIterator.next();
            if (picture != null) {
                File sourceFile = picture.getFile();
                System.out.println(sourceFile);
                File dest = new File(System.getProperty("user.dir") + "\\HousesDocuments\\" + szHouseName + "\\" + picture.getFilename());
                try {
                    play.Logger.info("<SETTER> save profile picture on file");
                    setterBL.copyFile(sourceFile, dest);
                    webResponce.setReason("Success Upload File To the Sever");
                } catch (IOException e) {
                    e.printStackTrace();
                    webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                    webResponce.setReason("Error When Upload File To Server");
                    play.Logger.info(e.getMessage());
                }
            } else {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Error When Try To Upload A Null File To The Server");
            }
        }
        return webResponce;
    }

    public WebResponce setHouseDocuments(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        Iterator<Http.MultipartFormData.FilePart> filePartIterator = pictures.iterator();
        while (filePartIterator.hasNext()) {
            Http.MultipartFormData.FilePart picture = filePartIterator.next();
            if (picture != null) {
                File sourceFile = picture.getFile();
                System.out.println(sourceFile);
                File dest = new File(System.getProperty("user.dir") + "\\HousesDocuments\\" + szHouseName + "\\Docs\\" + picture.getFilename());
                try {
                    play.Logger.info("<SETTER> save profile picture on file");
                    setterBL.copyFile(sourceFile, dest);
                    webResponce.setReason("Success Upload File To the Sever");
                } catch (IOException e) {
                    e.printStackTrace();
                    webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                    webResponce.setReason("Error When Upload File To Server");
                    play.Logger.info(e.getMessage());
                }
            } else {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Error When Try To Upload A Null File To The Server");
            }
        }
        return webResponce;
    }

    public WebResponce setHouseProfilePicture(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        Iterator<Http.MultipartFormData.FilePart> filePartIterator =  pictures.iterator();
        while (filePartIterator.hasNext()) {
            Http.MultipartFormData.FilePart picture = filePartIterator.next();
            if (picture != null) {
                File sourceFile = picture.getFile();
                System.out.println(sourceFile);
                File dest = new File(System.getProperty("user.dir") + "\\HousesDocuments\\" + szHouseName + "\\Profile\\" + szHouseName +"_profile.jpg");
                try {
                    play.Logger.info("<SETTER> save profile picture on file");
                    setterBL.copyFile(sourceFile, dest);
                    webResponce.setReason("Success Upload File To the Sever");
                } catch (IOException e) {
                    e.printStackTrace();
                    webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                    webResponce.setReason("Error When Upload File To Server");
                    play.Logger.info(e.getMessage());
                }
            } else {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Error When Try To Upload A Null File To The Server");
            }
        }
        return webResponce;
    }
}
