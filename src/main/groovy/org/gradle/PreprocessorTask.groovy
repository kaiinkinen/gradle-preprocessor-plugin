package org.gradle

import antenna.preprocessor.v3.PreprocessorBridge
import groovy.io.FileType
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.apache.maven.artifact.ant.shaded.FileUtils

class PreprocessorTask extends DefaultTask {

    File source
    File destination
    // Fixme: Make this a set/map or something else
    String symbols

    @TaskAction
    def preprocess() {
        if (!source?.isDirectory()) {
            // Throw an error
        }

        if (!destination?.exists()) {
            if (!destination.mkdirs()) {
                // Throw an error
            }
        }

        FileUtils.copyDirectory(source, destination)

        PreprocessorBridge pp = new PreprocessorBridge(null);
        pp.addSymbols(symbols)

        destination.eachFile(FileType.FILES) {
            InputStream is = it.newDataInputStream()
            OutputStream os = it.newDataOutputStream()

            pp.preprocess(is, os, "UTF-8")

        }


    }
}
