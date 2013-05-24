package org.inkinen

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*
import org.inkinen.PreprocessorTask

class PreprocessorPluginTest {
    @Test
    public void preprocessorPluginAddsPreprocessorTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'preprocessor'



        assertTrue(project.tasks.preprocess instanceof PreprocessorTask)
    }
}
