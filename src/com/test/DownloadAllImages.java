package com.test;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownloadAllImages {
    private static final String PATH = "images";
    public static void main(String[] args) throws IOException {

        File directory = new File(PATH);
        if (!directory.exists())
            directory.mkdirs();

        String siteToDownloadAllImages = "https://www.gazeta.ru/";

        Elements link = Jsoup.connect(siteToDownloadAllImages).get().select("img");
        link.forEach(e ->
                {
                    String src = e.absUrl("src");
                    String nameOfImage = src.substring(src.lastIndexOf('/') + 1);
                    try {
                        InputStream in = new URL(src).openStream();
                        Files.copy(in, Paths.get(PATH + File.separator + nameOfImage));
                        System.out.println(nameOfImage);
                    } catch (Exception ignored) {
                    }
                }
        );
    }
}
