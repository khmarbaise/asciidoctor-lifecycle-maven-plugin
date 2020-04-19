package com.coutemeier.maven.plugins.asciidoctor.lifecycle.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.apache.maven.plugin.MojoFailureException;
import org.eclipse.aether.resolution.ArtifactResolutionException;
import org.junit.Assert;
import org.junit.Test;

public class FileUtilTestCase {
    @Test
    public void walkRegularFiles_emptyDirsAreIgnoredTest()
    throws IOException {
        final Path copyDir = new File( FileUtilTestCase.class.getClassLoader().getResource( "copy-dir" ).getFile() ).toPath();
        final Path emptyDir = new File( "empty-dir" ).toPath();
        final Path childIsEmptyDir = new File( "child-is-empty-dir" ).toPath();

        final Stream<Path> paths = FileUtil.walkRegularFiles( copyDir );

        boolean containsEmptyDir =
            paths
                .filter(
                    item->  item.getFileName().equals( emptyDir )
                            || item.getFileName().equals( childIsEmptyDir )
                )
                .count() == 0;
        Assert.assertTrue( containsEmptyDir );
    }
}