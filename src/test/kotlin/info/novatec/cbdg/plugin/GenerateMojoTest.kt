package info.novatec.cbdg.plugin

import org.apache.maven.plugin.testing.AbstractMojoTestCase
import org.junit.jupiter.api.Test
import java.io.File

class GenerateMojoTest : AbstractMojoTestCase() {

    private val testPom = "src/test/resources/pom.xml"

    @Test
    fun `test Generate with Non-Default parameters`() {
        super.setUp()
        val testPomFile: File = getTestFile(testPom)
        assertNotNull(testPomFile)
        assertTrue(testPomFile.exists())

        val myMojo = lookupMojo("generate", testPomFile)
        assertNotNull(myMojo)
        myMojo.execute()

        assertNotNull(File("build/test-results/cbdg/html/TestDiagram.html"))
        assertNotNull(File("build/test-results/cbdg/html/TestDiagramWithoutImage.html"))
    }
}
