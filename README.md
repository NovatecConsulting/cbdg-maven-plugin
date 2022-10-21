# Maven plugin for CBDG

[Test Project](https://github.com/NovatecConsulting/cbdg-maven-test)

## Code documentation
Hosted via [github pages](https://novatecconsulting.github.io/camunda-bpmn-documentation-generator/)

## The configuration of the plugin in the POM of target-project

    <plugins>
        ... 
        <plugin>
            <groupId>info.novatec</groupId>
            <artifactId>cbdg-maven-plugin</artifactId>
            <version>1.0-SNAPSHOT</version>
            <!-- optionaly, executes with install --> 
            <executions>
                <execution>
                    <id>generate</id>
                    <phase>install</phase>
                    <goals>
                        <goal>generate</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
        ...
    </plugins>

## Run the goal of the plugin on the target-project 

    ./mw cbdg:generate
