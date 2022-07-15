package com.julscodes.shortener.service;

import com.julscodes.shortener.Logger;
import com.julscodes.shortener.dao.ShortUrlDAO;
import com.julscodes.shortener.dao.entity.ShortUrl;
import org.apache.commons.validator.routines.UrlValidator;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.util.List;

@ManagedBean
public class UrlServiceImpl implements UrlService {

    @Inject
    private ShortUrlDAO shortUrlDAO;

    @Override
    public ShortUrl shortenUrl(String longUrl) {

        UrlValidator validator = new UrlValidator();

        if (!validator.isValid(longUrl))
            throw new BadRequestException("invalid URL!");

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setId(generateId());
        shortUrl.setLongUrl(longUrl);

        Logger.debug(getClass().getSimpleName(), "will save: " + shortUrl);
        return shortUrlDAO.saveUrl(shortUrl);


    }

    @Override
    public ShortUrl getShortUrl(String id) {
        return shortUrlDAO.getUrl(id);
    }

    @Override
    public ShortUrl updateUrl(String id, ShortUrl shortUrl) {
        UrlValidator validator = new UrlValidator();

        if (!validator.isValid(shortUrl.getLongUrl()))
            throw new BadRequestException("invalid URL!");

        return shortUrlDAO.updateUrl(id, shortUrl);
    }

    @Override
    public int deleteUrl(String id) {
        return shortUrlDAO.deleteUrl(id);
    }

    @Override
    public List<ShortUrl> listUrls() {
        return shortUrlDAO.listUrls();
    }


    @Override
    public String generateId() {

        //should be a property of course
        int maxRetries = 10;

        //generate another code in case of collision
        String code = null;
        for (int i = 0; i < maxRetries; i++) {

            code = generateCode(7);

            if (shortUrlDAO.getUrl(code) == null)
                break;
        }

        return code;
    }

    private String generateCode(int size) {

        StringBuilder idCode = new StringBuilder();
        for (int i = 1; i <= size; i++) {

            switch ((int) (Math.random() * 3) + 1) {
                case 1:
                    idCode.append(String.valueOf(generateNumbers()));
                    break;
                case 3:
                    idCode.append(String.valueOf(generateLowerCase()));
                    break;
                default:
                    idCode.append(String.valueOf(generateCapitalLetters()));
                    break;
            }
        }

        return idCode.toString();
    }

    private char generateLowerCase() {
        int min = 97;
        int max = 122;

        int numRando = (int) Math.floor(Math.random() * (max - min) + min);

        return (char) numRando;
    }

    private char generateCapitalLetters() {

        int min = 65;
        int max = 90;
        int numRando = (int) Math.floor(Math.random() * (max - min) + min);


        return (char) numRando;
    }

    private char generateNumbers() {

        int min = 48;
        int max = 57;
        int numRando = (int) Math.floor(Math.random() * (max - min) + min);

        return (char) numRando;
    }
}
