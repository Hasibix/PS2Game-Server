package net.hasibix.ps2game.server.utils;

public class EqualsArray {
    public static Boolean Equals(Object obj, Object[] array) {
        for (Object i : array) {
            if(obj.equals(i)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }
}
