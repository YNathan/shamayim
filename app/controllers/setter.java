package controllers;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

import BL.setterBL;
import Entity.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.ning.http.multipart.FilePart;
import play.api.mvc.MultipartFormData;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;

import static play.mvc.Controller.flash;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

/**
 * @author Yaacov
 */
public class setter {
    private static WebResponce webResponce = new WebResponce();
    private static setterBL setterBL = new setterBL();

    /**
     * Inserting new house.
     *
     * @return
     */

    public static Result setNewHouse() {
        webResponce = new WebResponce();
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            House houseToRegistre = new House();
            try {
                System.out.println(json.toString());
                houseToRegistre.setState(json.findPath("state").textValue());
                houseToRegistre.setCity(json.findPath("city").textValue());
                houseToRegistre.setStreet(json.findPath("street").textValue());
                houseToRegistre.setHouseNumber(json.findPath("house_number").asInt());
                EHouseKind eHouseKind = EHouseKind.ALONE;
                eHouseKind.setValue(json.findPath("house_kind").asInt());
                houseToRegistre.setHouseKind(eHouseKind);
                houseToRegistre.setNumberOfRooms(json.findPath("number_of_rooms").asInt());
                houseToRegistre.setNumberOfLivingRooms(json.findPath("number_of_living_rooms").asInt());
                houseToRegistre.setNumberOfKitchens(json.findPath("number_of_kitchens").asInt());
                houseToRegistre.setNumberOfBedrooms(json.findPath("number_of_bedrooms").intValue());
                houseToRegistre.setNumberOfBathrooms(json.findPath("number_of_bathrooms").intValue());
                ELocationKind eLocationKind = ELocationKind.WHITE;
                eLocationKind.setValue(json.findPath("location_kind").asInt());
                houseToRegistre.setLocationKind(eLocationKind);
                houseToRegistre.setComments(json.findPath("comments").textValue());
            } catch (Exception e) {
                webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
                webResponce.setReason("Missing parameter the system did'nt save the details ,חסר פרטים המערכת לא שמרה ת הנתונים" + houseToRegistre.toString());
                e.printStackTrace();
                return badRequest(webResponce.toJson());
            }
            webResponce = setterBL.insertHouseDetails(houseToRegistre);
            if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
                System.out.println(webResponce.toString());
                return badRequest(webResponce.toJson());
            }
            System.out.println("The House was Register correctly" + houseToRegistre.toString());
            return ok(webResponce.getReason());
        }
    }

    /**
     * Register new user into the system
     *
     * @param szUserName  - user name
     * @param szFirstName - first name
     * @param szLastName  - last name
     * @param szTelephone - telephone
     * @param szEmail     - email
     * @param szPassword  - password
     * @param szBirthdate - birthdate
     * @return
     * @throws Exception
     */
    public static Result registerNewUser(String szUserName, String szFirstName, String szLastName, String szTelephone,
                                         String szEmail, String szPassword, String szBirthdate) throws Exception {
        // updateProfilePicture();
        // INFO
        play.Logger.info("<SETTER> Register new user : \n============================\nFor : =>>\nUser name : "
                + szUserName + "\nFirst name : " + szFirstName + "\nLast name : " + szLastName + "\nTelephone : "
                + szTelephone + "\nEmail : " + szEmail + "\nPassword : " + szPassword + "\nBirthdate : " + szBirthdate
                + "\n============================\n");

        System.out.println("[INFO] " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Register new user : ");
        System.out.println("============================");
        System.out.println("For : =>>");
        System.out.println("User name : " + szUserName);
        System.out.println("First name : " + szFirstName);
        System.out.println("Last name : " + szLastName);
        System.out.println("Telephone : " + szTelephone);
        System.out.println("Email : " + szEmail);
        System.out.println("Password : " + szPassword);
        System.out.println("Birthdate : " + szBirthdate);
        System.out.println("============================");

        if ((szUserName != null) && (szFirstName != null) && (szLastName != null) && (szTelephone != null)
                && (szEmail != null) && (szPassword != null) && (szBirthdate != null)) {
            if (setterBL.registerNewUser(szUserName, szFirstName, szLastName, szTelephone, szEmail, szPassword,
                    szBirthdate)) {
                return ok("true");
            } else {
                return badRequest("An internal error as ocurred when trying to register");
            }

        } else {
            return badRequest(
                    "Null pointer screw you! \nyou send your request with an empty user-name or an empty first-name or an last-name or an telephone or an email or an password or an birthdate!");
        }
    }

    /**
     * Get file(*can be a profile picture) from client and save in the server
     *
     * @return
     * @throws IOException
     */
    public static play.mvc.Result uploadFile() throws IOException {
        if (updateProfilePicture()) {
            return redirect("assets/index.html");
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

    public static boolean updateProfilePicture(String szUserName) {
        play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
        play.mvc.Http.MultipartFormData.FilePart picture = body.getFile("file");
        play.mvc.Http.MultipartFormData.FilePart picture1 = body.getFile("lfFile");
        if (picture != null) {
            java.io.File sourceFile = picture.getFile();
            File dest = new File(System.getProperty("user.dir") + "\\profilsPicture\\" + szUserName + ".jpg");
            try {
                play.Logger.info("<SETTER> save profile picture on file");
                setterBL.copyFile(sourceFile, dest);
            } catch (IOException e) {
                e.printStackTrace();
                play.Logger.info(e.getMessage());
            }
            return true;
        } else {
            flash("error", "Missing file");
            return false;
        }
    }


    /**
     * Get file(*can be a profile picture) from client and save in the server
     *
     * @return
     * @throws IOException
     */
    public static play.mvc.Result setHousePictures(String szHouseName) throws IOException {
        play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> pictures = body.getFiles();
        webResponce = setterBL.setFiles(szHouseName, pictures);
        if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
            return badRequest(webResponce.getReason());
        } else {
            return ok(webResponce.getReason());
        }

    }

    public static boolean updateProfilePicture() {
        play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
        play.mvc.Http.MultipartFormData.FilePart picture = body.getFile("file");
        if (picture != null) {
            java.io.File sourceFile = picture.getFile();
            File dest = new File(System.getProperty("user.dir") + "\\profilsPicture\\" + picture.getFilename());
            try {
                play.Logger.info("<SETTER> save profile picture on file");
                setterBL.copyFile(sourceFile, dest);
            } catch (IOException e) {
                e.printStackTrace();
                play.Logger.info(e.getMessage());
            }
            return true;
        } else {
            flash("error", "Missing file");
            return false;
        }
    }

    /**
     * Get file(*can be a profile picture) from client and save in the server
     *
     * @return
     * @throws IOException
     */
    public static play.mvc.Result uploadFileWithName(String szUserName) throws IOException {
        if (updateProfilePicture(szUserName)) {
            return redirect("assets/index.html");
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

    public static boolean updateHousePicture(String szUserName) {
        play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
        play.mvc.Http.MultipartFormData.FilePart picture = body.getFile("file");
        if (picture != null) {
            java.io.File sourceFile = picture.getFile();
            File dest = new File(System.getProperty("user.dir") + "\\profilsPicture\\" + szUserName + ".jpg");
            try {
                play.Logger.info("<SETTER> save profile picture on file");
                setterBL.copyFile(sourceFile, dest);
            } catch (IOException e) {
                e.printStackTrace();
                play.Logger.info(e.getMessage());
            }
            return true;
        } else {
            flash("error", "Missing file");
            return false;
        }
    }

    public static Result sendHouseMail(String szUserName, String szHouseId) {
        System.out.println("Send House Nail Function");
        webResponce = new WebResponce();
        if ((szUserName == null) || (szHouseId == null)) {
            return badRequest("user name or house id is empty");
        } else {
            webResponce = setterBL.sendHouseMail(szUserName, szHouseId);
            if (webResponce.getSuccessFailed() == ESuccessFailed.FAILED) {
                return badRequest(webResponce.getReason());
            }
        }

        return ok(webResponce.getReason());
    }


}
