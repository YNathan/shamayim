package controllers;

import com.sun.javafx.image.impl.ByteIndexed;
import play.api.Configuration;
import play.api.GlobalSettings;
import play.api.Play;
import play.api.Plugin;
import play.api.mvc.*;
import play.api.mvc.SimpleResult;
import play.core.Router;
import play.core.SourceMapper;
import play.mvc.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import BL.getterBL;
import akka.event.Logging;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import scala.Enumeration;
import scala.Option;
import scala.collection.Seq;
import scala.concurrent.Future;
import scala.reflect.ClassTag;

import static play.mvc.Http.Context.Implicit.request;

/**
 * @author Yaacov
 */
public class getter extends Controller {
    private static final Lock lock = new ReentrantLock();
    private static getterBL getterBL = new getterBL();

    public static Result isLoginPermited(String szUserName, String szPassword) {
        Logger.info("<GETTER> Clien in IP : " + request().remoteAddress() + " Trying to connect");
        System.out.println("<GETTER> Clien in IP : " + request().remoteAddress() + " Trying to connect");

        if ((szUserName != null) && (szPassword != null)) {
            String szPermission = getterBL.isLoginPermited(szUserName, szPassword);
            if (szPermission != "-1") {
                play.Logger.info("<GETTER> " + szUserName + " is login from IP: " + request().remoteAddress());
                System.out.println("<GETTER> " + szUserName + " is login from IP: " + request().remoteAddress());
                return play.mvc.Results.ok(szPermission);
            }else{

                System.out.println("[INFO] Error when trying to connect with wrong user-name or password.\nUSER_NAME : '"
                        + szUserName + "'\nPASSWORD : '" + szPassword + "'");
                return play.mvc.Results.badRequest("The user-name or the password is incorrect");
            }
        } else {
            return play.mvc.Results.badRequest(
                    "Null pointer screw you! \nyou send your request with an empty user-name or an empty password!");
        }
    }


    public static Result isUserNameAlreadyExist(String szUserName) {
        if (szUserName != null) {
            if (getterBL.isUserNameExist(szUserName)) {
                return play.mvc.Results.badRequest("user name already exist");
            }
            return play.mvc.Results.ok("true");
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }

    public static Result isEmailAlreadyExist(String szEmail) {
        if (szEmail != null) {
            if (getterBL.isEmailAlreadyExist(szEmail)) {
                return play.mvc.Results.badRequest("email already exist");
            }
            return play.mvc.Results.ok("true");
        } else {
            return play.mvc.Results.badRequest("Null pointer screw you! \nyou send your request with an empty email!");
        }

    }


    public static Result getUsers(String szUserName) {
        if (szUserName != null) {
            return play.mvc.Results.ok(Json.toJson(getterBL.getUsers(szUserName)));
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }

    public static Result getUserInformation(String szUserName) {
        play.Logger.info("<GETTER> " + szUserName + " ask information on the user");
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER> " + szUserName + " in IP : " + request().remoteAddress() + " : ask information on user name : " + szUserName);
        if (szUserName != null) {
            String szResponce = getterBL.getUserInformation(szUserName).toString();
            play.Logger.info("<GETTER> <DATA>" + szResponce);
            return play.mvc.Results.ok(szResponce);
        } else {
            return play.mvc.Results
                    .badRequest("Null pointer screw you! \nyou send your request with an empty user-name!");
        }
    }


    public static Result getListOfHouses() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get List Of Houses");
        String szResponce = getterBL.getListOfHouse().toString();
        System.out.println(szResponce);
        return ok(szResponce);
    }

    public static Result getHouseById(String szHouseId) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get House by id : " + szHouseId);
        String szResponce = getterBL.getHouseById(szHouseId).toString();
        System.out.println(szResponce);
        return ok(szResponce);
    }

    public static Result getListOfExistingLanguages() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get List Of Existing Language");
        String szResponce = getterBL.getListOFExistingLanguage().toString();
        System.out.println(szResponce);
        return ok(szResponce);
    }

    public static Result getLanguage(String szLanguage) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get Language : " + szLanguage);
        String szResponce = getterBL.getHouseLanguageByLanguage(szLanguage).toJson();
        return ok(szResponce);
    }

    /***
     * Get Files Of An House
     * @param szHouseId - the id of the house az registred in the system
     * @return - json who contain path of the files
     * @throws IOException
     */
    public static Result getHouseFiles(String szHouseId) throws IOException {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get Files Pathes For House : " + szHouseId);
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get Files Pathes For House : " + szHouseId);
        String szResponce = getterBL.getFilePaths(szHouseId).toString();
        return ok(szResponce);
    }

    /***
     * Get a specific file from server consserning a house
     * @return
     */
    public static Result getSpecificFile(String szFolderName, String szFileName) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get File For House : " + szFolderName + ": File Name:" + szFileName);
        Logger.debug(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " <GETTER>  in IP : " + request().remoteAddress() + " : Get File For House : " + szFolderName + ": File Name:" + szFileName);
        File fileToReturn = getterBL.getAspecificFile(szFolderName, szFileName);
        return ok(fileToReturn);
    }
}
