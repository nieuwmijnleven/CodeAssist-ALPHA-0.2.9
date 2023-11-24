package com.tyron.builder;

import java.io.*;

public class Logger {

  public static void log(String log) {
    //String baseFolder;
    // check if external storage is available
    /*if (Environment.getExternalStorageState().equals(
            Environment.MEDIA_MOUNTED)) {
      baseFolder = Environment
                       .getExternalStoragePublicDirectory(
                           Environment.DIRECTORY_DOWNLOADS)
                       .toString();
    } else {
      baseFolder = this.getExternalFilesDir().getAbsolutePath();
      }*/

    String baseFolder = this.getExternalFilesDir("logs").getAbsolutePath();
    File file = new File(baseFolder, "build.log");

    PrintWriter writer = null;
    try {
      if (!file.exists()) {
        file.createNewFile();
	Runtime.getRuntime().exec("chmod 666 " + file.getAbsolutePath());
      }
      
      writer = new PrintWriter(new FileWriter(file, true), true);
      writer.println(log);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } finally {
      try {
        if (writer != null)
          writer.close();
      } catch (Exception ignored) {
      }
    }
  }
}
