package com.youcruit.onfido.api.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Manifest;

public class ManifestHelper {
    public static Manifest getManifest(String implementationTitle) {
        try {
            Enumeration<URL> resources = ManifestHelper.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                try (InputStream is = url.openStream()) {
                    Manifest manifest = new Manifest(is);
                    String otherImplementationTitle = getAttribute(manifest, "Implementation-Title");
                    if (implementationTitle.equals(otherImplementationTitle)) {
                        return manifest;
                    }
                } catch (IOException e) {
                }
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static String getManifestProperty(String implementationTitle, String propertyName) {
        Manifest manifest = getManifest(implementationTitle);
        if (manifest != null) {
            return getAttribute(manifest, propertyName);
        }
        return null;
    }

    private static String getAttribute(Manifest manifest, String attributeName) {
        return manifest.getMainAttributes().getValue(attributeName);
    }

    public static String getVersion(String implementationTitle) {
        String version = getManifestProperty(implementationTitle, "Implementation-Version");
        if (version != null) {
            return version;
        }
        return "UNKNOWN";
    }
}
