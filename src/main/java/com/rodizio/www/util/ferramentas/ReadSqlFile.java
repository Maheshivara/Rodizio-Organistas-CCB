package com.rodizio.www.util.ferramentas;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.rodizio.www.dao.InsertsIniciaisBD;

public class ReadSqlFile {
  public static String[] readSqlFile(String filePath) {
    InputStream inputStream = InsertsIniciaisBD.class.getResourceAsStream(filePath);
    if (inputStream == null) {
      throw new RuntimeException("File not found: /com/rodizio/www/dao/sql/seed.sql");
    }
    byte[] bytes = new byte[0];
    try {
      bytes = inputStream.readAllBytes();
    } catch (Exception e) {
      e.printStackTrace();
    }
    String fileData = new String(bytes, StandardCharsets.UTF_8);
    String withoutComments = fileData.replaceAll("(?m)^--.*$", "");

    String[] commands = withoutComments.replace("\r\n", "\n").split(";");
    for (int i = 0; i < commands.length; i++) {
      commands[i] = commands[i].replace("\n", " ").trim();
    }
    commands = Arrays.stream(commands)
        .filter(command -> !command.isEmpty() || command.trim().length() > 0)
        .toArray(String[]::new);

    return commands;
  }
}
