// J3b_9.java: Email (EPosta) örneði.

// Bu programý çalýþtýramadýðýmdan olduðu gibi býrakýyorum...
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

/**
 * Example monitors a specified directory for new files.
 * If a newly added file is a plain text file, the file can
 * be emailed to the appropriate alias.  The emailing details
 * are left to the reader.  What the example actually does is
 * print the details to standard out.
 */

public class J3b_9 {

    private final WatchService watcher;
    private final Path dir;

    /**
     * Creates a WatchService and registers the given directory
     */
    J3b_9(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        dir.register(watcher, ENTRY_CREATE);
        this.dir = dir;
    }

    /**
     * Process all events for the key queued to the watcher.
     */
    void processEvents() {
        for (;;) {

            // wait for key to be signaled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                //The filename is the context of the event.
                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();

                //Verify that the new file is a text file.
                try {
                    Path child = dir.resolve(filename);
                    if (!Files.probeContentType(child).equals("text/plain")) {
                        System.err.format("New file '%s' is not a plain text file.%n", filename);
                        continue;
                    }
                } catch (IOException x) {
                    System.err.println(x);
                    continue;
                }

                //Email the file to the specified email alias.
                System.out.format("Emailing file %s%n", filename);
                //Details left to reader....
            }

            //Reset the key -- this step is critical if you want to receive
            //further watch events. If the key is no longer valid, the directory
            //is inaccessible so exit the loop.
            boolean valid = key.reset();
            if (!valid) {
                    break;
            }
        }
    }

    static void usage() {
        System.err.println("usage: java J3b_9 dir");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {
        //parse arguments
        if (args.length < 1)
            usage();

        //register directory and process its events
        Path dir = Paths.get(args[0]);
        new J3b_9(dir).processEvents();
    } // main(..) metodu sonu...
} // J3b_9 sýnýfý sonu...

/* Çýktý:
**  >java J3b_9 "C:\Users\pc\Desktop\MyFiles\4. Dersler\JDK-8\uygulama"
// Bekler; yeni bir text dosyasýnýn dizine gelmesini ve kullanýcýnýn EPosta'ya göndermesi için...
*/