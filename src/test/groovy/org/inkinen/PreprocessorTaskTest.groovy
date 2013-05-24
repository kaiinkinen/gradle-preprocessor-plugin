package org.inkinen

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*
import org.inkinen.PreprocessorTask

class PreprocessorTaskTest {
    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('preprocessor', type: PreprocessorTask)
        assertTrue(task instanceof PreprocessorTask)
    }
}
