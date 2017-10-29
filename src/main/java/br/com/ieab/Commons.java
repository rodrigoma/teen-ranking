package br.com.ieab;

/**
 * Created by montanha on 29/10/17.
 */
public class Commons {

    public static final String KEY = "teen_ieab";

    public static String nameToHash(String name) {
        return name.trim().toUpperCase().replace(" ", "_");
    }

    public static String hashToName(String hash) {
        return hash.trim().toUpperCase().replace("_", " ");
    }
}