package controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import akka.actor.WriteBuffer;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        System.out.println("\n[INFO] Hello Server\n");
        return redirect("assets/index.html");
    }

    public static Result CheackFromHtml() {
        System.out.println("\n[INFO] From Html Status: Ok\n");
        return ok();
    }

    public static Result Register() {
        return redirect("assets/index.html");
    }

    public static Result Main() {
        return redirect("assets/index.html");
    }


}
