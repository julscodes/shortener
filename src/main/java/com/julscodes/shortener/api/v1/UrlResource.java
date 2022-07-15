package com.julscodes.shortener.api.v1;

import com.julscodes.shortener.api.v1.pojo.UrlCreatedResponse;
import com.julscodes.shortener.api.v1.pojo.UrlRequest;
import com.julscodes.shortener.api.v1.pojo.UrlResponse;
import com.julscodes.shortener.dao.entity.ShortUrl;
import com.julscodes.shortener.service.UrlService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/urls")
public class UrlResource {

    //replaced externally on a load balancer
    private static final String SHORT_DOMAIN = "http://localhost:8080/shortener/v1/redirect/";

    @Inject
    private UrlService urlService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUrls() {
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
        //only for testing
//        return Response.status(Response.Status.OK)
//                .entity(new ListResponse(urlService.listUrls())).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUrl(UrlRequest urlRequest) {
        ShortUrl shortUrlEntity = urlService.shortenUrl(urlRequest.getUrl());

        String shortenedURL = SHORT_DOMAIN + shortUrlEntity.getId();

        return Response.status(Response.Status.CREATED)
                .entity(new UrlCreatedResponse(shortUrlEntity, shortenedURL))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getUrl(@PathParam("id") String id) {
        ShortUrl shortUrl = urlService.getShortUrl(id);

        if (shortUrl == null)
            return Response.status(Response.Status.NO_CONTENT).entity(new UrlResponse(shortUrl)).build();

        return Response.status(Response.Status.OK)
                .entity(new UrlResponse(shortUrl))
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateUrl(@PathParam("id") String id, UrlRequest urlRequest) {

        ShortUrl shortUrl = urlService.getShortUrl(id);

        if (shortUrl == null)
            return Response.status(Response.Status.NO_CONTENT).entity(new UrlResponse(shortUrl)).build();


        shortUrl.setLongUrl(urlRequest.getUrl());
        urlService.updateUrl(id, shortUrl);

        return Response.status(Response.Status.OK)
                .entity(new UrlResponse(shortUrl))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteUrl(@PathParam("id") String id) {
        ShortUrl shortUrl = urlService.getShortUrl(id);

        if (shortUrl == null)
            return Response.status(Response.Status.NO_CONTENT).entity(new UrlResponse(shortUrl)).build();

        urlService.deleteUrl(id);

        return Response.status(Response.Status.OK)
                .entity(new UrlResponse(shortUrl))
                .build();
    }


}
