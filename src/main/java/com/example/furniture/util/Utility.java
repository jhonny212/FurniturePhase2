package com.example.furniture.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class Utility {

    UUID uuid = UUID.randomUUID();
    private static final Path root = Paths.get("config");

    public static String saveFile(MultipartFile multiPart, String ruta) {
        // Obtenemos el nombre original del archivo.
        System.out.println("Raiz: "+ root.toString());

        String nombreOriginal1 = multiPart.getOriginalFilename();
        String nombreOriginal = UUID.randomUUID().toString();
        System.out.println(nombreOriginal);
        try {
            String arr [] = nombreOriginal1.split("\\.");
            // Formamos el nombre del archivo para guardarlo en el disco duro.
            File imageFile = new File(ruta+ nombreOriginal+"."+arr[arr.length-1]);
            System.out.println("Archivo: " + imageFile.getAbsolutePath());
            //Guardamos fisicamente el archivo en HD.
            multiPart.transferTo(imageFile);
            return nombreOriginal+"."+arr[arr.length-1];
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
}
