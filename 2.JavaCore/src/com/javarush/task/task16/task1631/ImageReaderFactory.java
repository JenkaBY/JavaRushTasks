package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

/**
 * Created by JenkaBY on 08.02.2017.
 * GOF Pattern ... Factory
 */

public class ImageReaderFactory {

    public static ImageReader getImageReader(ImageTypes type) throws IllegalArgumentException {

        if (type == ImageTypes.BMP){
            return new BmpReader();
        } else if (type == ImageTypes.PNG){
            return new PngReader();
        } else if (type == ImageTypes.JPG){
            return new JpgReader();
        } else {
            throw new IllegalArgumentException("Неизвестный тип картинки");
        }
    }
}
