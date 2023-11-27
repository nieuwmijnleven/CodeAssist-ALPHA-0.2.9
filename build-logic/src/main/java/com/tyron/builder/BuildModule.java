package com.tyron.builder;

import android.content.Context;
import com.tyron.common.util.Decompress;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class BuildModule {

  private static Context sApplicationContext;
  private static File sAndroidJar;
  private static File sLambdaStubs;
  private static File sDesugaredLib;
  private static File sDesugaredLibConfig;

  public static void initialize(Context applicationContext) {
    sApplicationContext = applicationContext.getApplicationContext();
  }

  public static Context getContext() {
    return sApplicationContext;
  }

  public static File getAndroidJar() {
    if (sAndroidJar == null) {
      Context context = BuildModule.getContext();
      if (context == null) {
        return null;
      }

      sAndroidJar = new File(context.getFilesDir(), "rt.jar");
      if (!sAndroidJar.exists()) {
        Decompress.unzipFromAssets(
            BuildModule.getContext(), "rt.zip", sAndroidJar.getParentFile().getAbsolutePath());
      }
    }

    return sAndroidJar;
  }

  public static File getDesugaredLib() {
    if (sDesugaredLib == null) {
      Context context = BuildModule.getContext();
      if (context == null) {
        return null;
      }

      sDesugaredLib = new File(context.getFilesDir(), "desugar_jdk_libs.jar");
      if (!sDesugaredLib.exists()) {
        Decompress.unzipFromAssets(
            BuildModule.getContext(),
            "desugar_jdk_libs.zip",
            sDesugaredLib.getParentFile().getAbsolutePath());
      }
    }

    return sDesugaredLib;
  }

  public static File getDesugaredLibConfig() {
    if (sDesugaredLibConfig == null) {
      Context context = BuildModule.getContext();
      if (context == null) {
        return null;
      }

      sDesugaredLibConfig = new File(context.getFilesDir(), "desugar.json");
      if (!sDesugaredLibConfig.exists()) {
        try (InputStream in = context.getAssets().open("desugar.json")) {
          Files.copy(in, sDesugaredLibConfig.toPath());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }

    return sDesugaredLibConfig;
  }

  public static File getLambdaStubs() {
    if (sLambdaStubs == null) {
      sLambdaStubs = new File(BuildModule.getContext().getFilesDir(), "core-lambda-stubs.jar");
      if (!sLambdaStubs.exists()) {
        Decompress.unzipFromAssets(
            BuildModule.getContext(),
            "lambda-stubs.zip",
            sLambdaStubs.getParentFile().getAbsolutePath());
      }
    }
    return sLambdaStubs;
  }
}
