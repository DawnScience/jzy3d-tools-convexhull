package org.jzy3d.convexhull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 *
 * @author Tomas Barton
 */
public class Fixtures {
    
     /**
     * Resource packed in jar is not possible to open directly, this method uses
     * a .tmp file which should be on exit deleted
     *
     * @param path
     * @return
     */
    public File resource(String path) {
        String resource = "/org/jzy3d/convexhull/datasets/" + path;
        File file = null;
        URL url = Fixtures.class.getResource(resource);
        if (url == null) {
            throw new RuntimeException("resource not found: " + path);
        }

        if (url.toString().startsWith("jar:")) {
            try {
                InputStream input = getClass().getResourceAsStream(resource);
                file = File.createTempFile("datafile", ".tmp");
                OutputStream out = new FileOutputStream(file);
                int read;
                byte[] bytes = new byte[1024];

                while ((read = input.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                file.deleteOnExit();
            } catch (IOException ex) {
                System.err.println(ex.toString());
            }
        } else {
            file = new File(url.getFile());
        }
        return file;
    }
    
    
    public File data1(){
        return resource("data2D1.txt");
    }
        
    public File data2(){
        return resource("data2D2.txt");
    }

}
