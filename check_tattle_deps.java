// check_tattle_deps.java

import java.lang.String;
import java.lang.ClassNotFoundException;
import java.lang.System;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.IOException;

public class check_tattle_deps {

    public static void class_check(String clsname) {
        try {
            Class c = Class.forName(clsname);
            System.out.println("good: " + clsname);
        } catch(ClassNotFoundException e) {
            System.out.println("FAIL: " + clsname);
        }
    }

    public static void parse_reportfile(String fname) {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(fname), Charset.forName("UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.indexOf("</i>, <i>") < 0)
                    continue;
                line = line.replaceFirst(" *<td><i>", "").replaceFirst("</i></td>", "");
                String[] words = line.split("</i>, <i>");
                for (int w = 0; w < words.length; w++)
                    class_check(words[w]);
            }
        } catch (IOException e) {
            System.err.println("Cannot read from " + fname + ": " + e);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: check_tattle_deps /path/to/tattletale_report/dependson/index.html");
            System.exit(1);
        }
        for (int n = 0; n < args.length; n++) {
            parse_reportfile(args[n]);
        }
    }
}

// END //
