// J3dq_1.java: PersistentEcho (KararlýYanký) örneði.

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class J3dq_1 {
    public static void main (String[] args) {
        String argString = "";
        boolean notProperty = true;

        // Are there arguments? 
        // If so retrieve them.
        if (args.length > 0) {
            for (String arg: args) {
                argString += arg + " ";
            }
            argString = argString.trim();
        }
        // No arguments, is there
        // an environment variable?
        // If so, //retrieve it.
        else if ((argString = System.getenv("J3dq_1")) != null) {}
        // No environment variable
        // either. Retrieve property value.
        else {
            notProperty = false;
            // Set argString to null.
            // If it's still null after
            // we exit the try block,
            // we've failed to retrieve
            // the property value.
            argString = null;
            FileInputStream fileInputStream = null;
            try {
                fileInputStream =
                    new FileInputStream("ÇevreÖzellikleri1.txt");
                Properties inProperties
                    = new Properties();
                inProperties.load(fileInputStream);
                argString = inProperties.getProperty("argString");
            } catch (IOException e) {
                System.err.println("Can't read property file.");
                System.exit(1);
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch(IOException e) {};
                }
            }
        }
        if (argString == null) {
            System.err.println("Couldn't find argString property");
            System.exit(1);
        }

        // Somehow, we got the
        // value. Echo it already!
        System.out.println(argString);

        // If we didn't retrieve the
        // value from the property,
        // save it //in the property.
        if (notProperty) {
            Properties outProperties =
                new Properties();
            outProperties.setProperty("argString",
                                      argString);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream =
                    new FileOutputStream("ÇevreÖzellikleri1.txt");
                outProperties.store(fileOutputStream,
                        "PersistentEcho properties");
            } catch (IOException e) {}
            finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch(IOException e) {};
                } // if kararý sonu...
            } // try-catch-finally bloðu sonu...
        } // if kararý sonu...
    } // main(..) metodu sonu...
} // J3dq_1 sýnýfý sonu...

/* Çýktý:
**  >java J3dq_1 path temp tmp username computername os  **
path temp tmp username computername os
*/

 /* ÇevreÖzellikleri1.txt dosya içeriði:
#PersistentEcho properties
#Sat May 19 03:10:31 EET 2018
argString=path temp tmp username computername os
*/