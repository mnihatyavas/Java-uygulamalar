// J3b_11.java: Find (Bul) �rne�i.

// Bu program� �al��t�ramad�m...
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import static java.nio.file.FileVisitOption.*;
import java.util.*;

/**
 * Sample code that finds files that
 * match the specified glob pattern.
 * For more information on what
 * constitutes a glob pattern, see
 * http://docs.oracle.com/javase/javatutorials/tutorial/essential/io/fileOps.html#glob
 *
 * The file or directories that match
 * the pattern are printed to
 * standard out.  The number of
 * matches is also printed.
 *
 * When executing this application,
 * you must put the glob pattern
 * in quotes, so the shell will not
 * expand any wild cards:
 *     java Find . -name "*.java"
 */

public class J3b_11 {
    /**
     * A {@code FileVisitor} that finds
     * all files that match the
     * specified pattern.
     */
    public static class Finder extends SimpleFileVisitor<Path> {
        private final PathMatcher matcher;
        private int numMatches = 0;

        Finder (String pattern) {
            matcher = FileSystems.getDefault().getPathMatcher ("glob:" + pattern);
        } // Finder(..) kurucusu sonu...

        // Compares the glob pattern against
        // the file or directory name.
        void find (Path file) {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                numMatches++;
                System.out.println(file);
            } // if karar� sonu...
        } // find(..) metodu sonu...

        // Prints the total number of
        // matches to standard out.
        void done() {System.out.println ("Matched: " + numMatches);}

        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile (Path file, BasicFileAttributes attrs) {
            find(file);
            return CONTINUE;
        } // visitFile(..) metodu sonu...

        // Invoke the pattern matching
        // method on each directory.
        @Override
        public FileVisitResult preVisitDirectory (Path dir, BasicFileAttributes attrs) {
            find(dir);
            return CONTINUE;
        } // preVisitDirectory(..) metodu sonu...

        @Override
        public FileVisitResult visitFileFailed (Path file, IOException exc) {
            System.err.println (exc);
            return CONTINUE;
        } // visitFileFailed(..) metodu sonu...
    } // Finder s�n�f� sonu...

    static void usage() {
        System.err.println("java Find <path>" + " -name \"<glob_pattern>\"");
        System.exit(-1);
    } // usage() metodu sonu...

    public static void main (String[] args) throws IOException {
        if (args.length < 3 || !args[1].equals("-name")) usage();

        Path startingDir = Paths.get(args[0]);
        String pattern = args[2];

        Finder finder = new Finder(pattern);
        Files.walkFileTree(startingDir, finder);
        finder.done();
    } // main(..) metodu sonu...
} // J3b_11 s�n�f� sonu...

/* ��kt�:

*/