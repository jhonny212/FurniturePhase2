package com.example.furniture.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Service
public class Utility {

    public static String saveFile(MultipartFile multiPart, String ruta) {
        // Obtenemos el nombre original del archivo.
        String nombreOriginal = multiPart.getOriginalFilename();
        System.out.println(nombreOriginal);
        try {
            // Formamos el nombre del archivo para guardarlo en el disco duro.
            File imageFile = new File(ruta+ nombreOriginal);
            System.out.println("Archivo: " + imageFile.getAbsolutePath());
            //Guardamos fisicamente el archivo en HD.
            multiPart.transferTo(imageFile);
            return nombreOriginal;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
}
