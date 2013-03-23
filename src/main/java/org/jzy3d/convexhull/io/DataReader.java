package org.jzy3d.convexhull.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

import java.io.File;
import java.util.List;
import org.jzy3d.convexhull.algorithms.Point2f;
import org.jzy3d.convexhull.utils.LinkedList;


/**
 * Hint: The buffering makes the program more than 20 times faster.
 *
 * @author Teemu Linkosaari
 */
public class DataReader {

    /**
     *
     * @param filename
     * @return
     * @throws java.io.IOException
     */
    public static List<Point2f> readData(String filename) throws IOException {
        Reader r = new BufferedReader(new FileReader(filename));
        return readData(r);
    }

    /**
     *
     * @param file
     * @return
     * @throws java.io.IOException
     */
    public static List<Point2f> readData(File file) throws IOException {
        Reader r = new BufferedReader(new FileReader(file));
        return readData(r);
    }

    // PRIVATE 
    private static List<Point2f> readData(Reader r) throws IOException {

        StreamTokenizer stok = new StreamTokenizer(r);
        stok.resetSyntax();
        stok.parseNumbers();
        stok.wordChars('A', 'z');

        stok.nextToken();

        int keys = 0;
        boolean flag = false;
        double x = 0, y;

        List<Point2f> listP = new LinkedList<Point2f>();
        List<String> listS = new LinkedList<String>();

        // Lue, kunnes tiedosto loppuu.
        while (stok.ttype != StreamTokenizer.TT_EOF) {

            // Jos merkkijono on luku
            if (stok.ttype == StreamTokenizer.TT_NUMBER) {
                if (flag == false) {
                    x = stok.nval;
                }
                if (flag == true) {
                    y = stok.nval;
                    listP.add(new Point2f(x, y));
                }
                flag = !flag;
            }

            // Jos merkkijono on merkkijono.
            if (stok.ttype == StreamTokenizer.TT_WORD) {
                //System.out.println("Avain: " + stok.sval);
                keys += 1;
                listS.add(stok.sval);
            }

            stok.nextToken();
        }

        return listP;
    }
}
