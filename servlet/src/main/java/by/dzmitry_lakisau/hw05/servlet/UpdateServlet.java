/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package by.dzmitry_lakisau.hw05.servlet;

import com.googlecode.objectify.ObjectifyService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class UpdateServlet extends HttpServlet {


    static {
        // Typically you would register this inside an OfyService wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Update.class);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Update update = ofy().load().type(Update.class).id(0).now();

        String jsonObject = "{\"" + Constants.FORCE_UPDATE + "\": \"" +
                update.getVersion()+
                "\", \"" + Constants.VERSION + "\": \""+
                update.getForceUpdate()+
                "\"}";

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(jsonObject);
        out.flush();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean forceUpdate = Boolean.parseBoolean(req.getParameter(Constants.FORCE_UPDATE));
        int version = Integer.parseInt(req.getParameter(Constants.VERSION));

        Update update = new Update();
        update.setForceUpdate(forceUpdate);
        update.setVersion(version);

        ofy().save().entity(update).now();
    }
}
