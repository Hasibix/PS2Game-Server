package net.hasibix.ps2game.server.utils;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.common.reflect.ClassPath;

public class ClassLoader {
    public static Set<Class<?>> loadFromPackage(String packageName) throws IOException {
        return ClassPath.from(java.lang.ClassLoader.getSystemClassLoader())
          .getAllClasses()
          .stream()
          .filter(clazz -> clazz.getPackageName()
            .equalsIgnoreCase(packageName))
          .map(clazz -> clazz.load())
          .collect(Collectors.toSet());
    }
}
