package info.novatec.cbdg.plugin

import info.novatec.docu.generator.DocuGenerator
import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import java.io.File

/**
 * Mojo - Class for cbdg-plugin. Calls by Maven-command 'mvn cbdg:generate'.
 */
@Mojo(name = "generate")
class CamundaBpmnDocumentationGeneratorMojo : AbstractMojo() {
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

    /**
     * Directory with the images of bpmn-files. Default is '{project.basedir}/src/main/resources/images'
     */
    @Parameter(property = "bpmnDiagramImageDir", defaultValue = "\${project.basedir}/src/main/resources/images")
    var bpmnDiagramImageDir: File? = null

    override fun execute() {
        DocuGenerator().parseAndGenerate(
            templateFile,
            camundaBpmnDir,
            resultOutputDir,
            bpmnDiagramImageDir
        )
    }
}
