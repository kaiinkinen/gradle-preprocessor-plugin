package org.inkinen

import antenna.preprocessor.v3.PreprocessorBridge
import groovy.io.FileType
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

class PreprocessorTask extends DefaultTask {

    Set<File> source
    File destination
    String symbols
    String encoding = "UTF-8"

    @TaskAction
    def preprocess() {
        if (source?.isEmpty()) {
            return;
        }

        if (!destination?.exists()) {
            if (!destination.mkdirs()) {
                // Throw an error
                throw GradleException("Could not create destination dir " + destination)
            }
        }

        PreprocessorBridge pp = new PreprocessorBridge(null);
        if (symbols != null && !symbols.isEmpty()) {
            pp.addSymbols(symbols)
        }

        for (File dir : source) {
            dir.eachFileRecurse(FileType.FILES) {
                def path = it.getPath()
                def output = path.replaceFirst(dir.path, destination.path)

                def outputFile = new File(output)
                outputFile.getParentFile().mkdirs()
                outputFile.createNewFile()

                InputStream is = it.newDataInputStream()
                OutputStream os = outputFile.newDataOutputStream()

                pp.preprocess(is, os, encoding)

            }

        }


    }
}
