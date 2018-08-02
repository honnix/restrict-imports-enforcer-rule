package de.skuzzle.enforcer.restrictimports.analyze;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class MatchFormatterImplTest {

    private final MatchFormatter subject = MatchFormatter.getInstance();

    @Test
    public void testFormatWithReason() throws Exception {
        final BannedImportGroup group = BannedImportGroup.builder()
                .withBasePackages("**")
                .withBannedImports("foo.bar.**")
                .withReason("Some reason")
                .build();

        final URL resourceDirUrl = getClass().getResource("/SampleJavaFile.java");
        final File resourceDirFile = new File(resourceDirUrl.toURI());
        final Path root = resourceDirFile.toPath().getParent();
        final Path sourceFile = root.resolve("SampleJavaFile.java");
        final Collection<Path> roots = ImmutableList.of(root);

        final AnalyzeResult analyzeResult = AnalyzeResult.builder()
                .withMatches(MatchedFile.forSourceFile(sourceFile)
                        .withMatchAt(3, "java.util.ArrayList"))
                .build();

        final String formatted = subject.formatMatches(roots, analyzeResult, group);

        assertEquals("\nBanned imports detected:\n" +
                "Reason: Some reason\n" +
                "\tin file: SampleJavaFile.java\n" +
                "\t\tjava.util.ArrayList (Line: 3)\n", formatted);
    }
}