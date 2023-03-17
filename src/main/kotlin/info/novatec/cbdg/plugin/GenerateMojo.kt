package info.novatec.cbdg.plugin

import info.novatec.cbdg.DocumentationGenerator
import info.novatec.cbdg.freemarker.FreemarkerDocumentationGenerator
import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

/**
 * Mojo - Class for cbdg-plugin. Calls by Maven-command 'mvn cbdg:generate'.
 */
@Mojo(name = "generate")
class GenerateMojo : AbstractMojo() {

    /**
     * Default usage is the templates/default.ftl from Jar-File.
     * To use it, it will be created in Build-Dir of the target project the empty file default.ftl
     * and fill it with the stream of templates/default.ftl from Jar-File
     */
    @Parameter(property = "templateFile", defaultValue = "\${project.build.directory}/classes/templates/default.ftl")
    lateinit var templateFile: File

    /**
     * Directory with bpmn-files. Default is '{project.basedir}/src/main/resources/bpmn'
     */
    @Parameter(property = "camundaBpmnDir", defaultValue = "\${project.basedir}/src/main/resources/bpmn")
    lateinit var camundaBpmnDir: File

    /**
     * Target-directory fot generated content. Default is '{project.build.directory}/cbdg/html'
     */
    @Parameter(property = "resultOutputDir", defaultValue = "\${project.build.directory}/cbdg/html")
    lateinit var resultOutputDir: File

    override fun execute() {
        if (templateFile.name.equals("default.ftl")) {
            FileOutputStream(templateFile, false).use { javaClass.classLoader.getResourceAsStream("templates/default.ftl")
                ?.transferTo(it) ?: throw FileNotFoundException("templates/default.ftl doesn't exist.")}
        }

        val documentationGenerator: DocumentationGenerator = FreemarkerDocumentationGenerator(resultOutputDir, templateFile)

        camundaBpmnDir.listFiles()?.forEach { bpmnFile ->
            log.info("Generating documentation for file ${bpmnFile.absolutePath}")
            log.info("Using template ${templateFile.absolutePath}")

            documentationGenerator.generateDocumentation(bpmnFile)

            log.info("Output report into path ${resultOutputDir.absolutePath}")
        } ?: throw FileNotFoundException("${camundaBpmnDir.absolutePath} doesn't exist.")

        resultOutputDir.listFiles()?.forEach {
            log.info("Output: " + it.absolutePath)
        } ?: throw FileNotFoundException("${resultOutputDir.absolutePath} doesn't exist.")
    }
}
