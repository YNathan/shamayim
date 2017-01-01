package controllers;


import play.mvc.*;

import views.html.*;
import File.FileGetter;

public class Application extends Controller {

    public static String szDbPassword = "";

    public static Result index() {
        System.out.println("\n[INFO] Server: new session\n");
        FileGetter fileGetter = new FileGetter();
        szDbPassword = fileGetter.getDataBasePassword();
        return redirect("assets/index.html");
    }

    public static Result CheackFromHtml() {
        System.out.println("\n[INFO] From Html Status: Ok\n");
        return ok("test");
    }

    public static Result Register() {
        return redirect("assets/index.html");
    }

    public static Result Main() {
        return redirect("assets/index.html");
    }


}
