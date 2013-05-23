package org.gradle

import org.gradle.api.Project
import org.gradle.api.Plugin

class PreprocessorPlugin implements Plugin<Project> {
    void apply(Project target) {
        target.task('preprocess', type: PreprocessorTask)
    }
}
