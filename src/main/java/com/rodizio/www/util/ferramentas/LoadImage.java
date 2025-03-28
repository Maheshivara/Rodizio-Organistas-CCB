package com.rodizio.www.util.ferramentas;

import java.awt.Image;
import java.io.InputStream;

import javax.swing.ImageIcon;

public class LoadImage {
  public static Image loadImage(String path) {
    InputStream imageStream = LoadImage.class.getResourceAsStream(path);
    if (imageStream == null) {
      throw new RuntimeException("Image not found: " + path);
    }
    return new ImageIcon(LoadImage.class.getResource(path)).getImage();
  }
}