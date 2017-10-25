/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package by.dzmitry_lakisau.hw05.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.googlecode.objectify.ObjectifyService;

import java.util.List;

import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "updateApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.hw05.dzmitry_lakisau.by",
                ownerName = "backend.hw05.dzmitry_lakisau.by",
                packagePath = ""
        )
)
public class UpdateEndpoint {


    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Update.class);
    }

    @ApiMethod(name = "getUpdate",
            path = "update",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Update getUpdate() {

        List<Update> s =  ofy().load().type(Update.class).list();
        int listSize = s.size();
        Update update = s.get(listSize-1);
        return update;
    }

    /**
     * This inserts a new <code>Update</code> object.
     *
     * @param version, forceUpdate The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "setUpdate",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void setUpdate(@Named("version") long version, @Named("forceUpdate") boolean forceUpdate) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that user.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.

        Update update = new Update();
        update.setForceUpdate(forceUpdate);
        update.setVersion(version);
        ofy().save().entity(update).now();
    }
}
