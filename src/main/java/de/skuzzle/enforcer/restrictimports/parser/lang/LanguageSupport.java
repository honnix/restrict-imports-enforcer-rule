package de.skuzzle.enforcer.restrictimports.parser.lang;

import de.skuzzle.enforcer.restrictimports.parser.ImportStatement;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * SPI for plugging in import statement recognition for different languages.
 * Implementations will be looked up using java's {@link ServiceLoader}.
 *
 * @author Simon Taddiken
 */
public interface LanguageSupport {

    /**
     * The set of supported file extensions. Extensions returned here are case insensitive
     * and may or may not start with a '.' (dot).
     *
     * @return The set of file extensions.
     */
    Set<String> getSupportedFileExtensions();

    /**
     * Parses the given line and returns the declared package
     *
     * @param line Line in the source file
     * @return The package declared
     */
    Optional<String> parsePackage(String line);

    /**
     * Extract the package names that this import statement represents. As some languages
     * allow to specify multiple imports in a single line, this method returns a list.
     *
     * e.g. import java.util.List; The above should return java.util.List in a Java source
     * file.
     *
     * @param importLine Line in the source file
     * @param lineNumber The line number of the import.
     * @return Fully qualified package name that this import represents
     */
    List<ImportStatement> parseImport(String importLine, int lineNumber);
}