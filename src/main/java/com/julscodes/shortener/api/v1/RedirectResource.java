package com.julscodes.shortener.api.v1;

import com.julscodes.shortener.Logger;
import com.julscodes.shortener.api.v1.pojo.UrlResponse;
import com.julscodes.shortener.dao.entity.ShortUrl;
import com.julscodes.shortener.service.UrlService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("v1/redirect")
public class RedirectResource {

    @Inject
    UrlService urlService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response redirectUrl(@PathParam("id") String id) {
        ShortUrl shortUrl = urlService.getShortUrl(id);

        if (shortUrl == null)
            return Response.status(Response.Status.NO_CONTENT).entity(new UrlResponse(shortUrl)).build();

        URI location = null;
        try {
            location = new URI(shortUrl.getLongUrl());
        } catch (Exception e) {
            Logger.debug(getClass().getSimpleName(), "invalid url stored");
        }
        Logger.info(getClass().getSimpleName(), "redirecting to: " + location);
        return Response.seeOther(location).build();
    }
}
