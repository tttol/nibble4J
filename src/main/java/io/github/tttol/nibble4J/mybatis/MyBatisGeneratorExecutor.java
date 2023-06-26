package io.github.tttol.nibble4J.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;

public class MyBatisGeneratorExecutor {
    public static void main(final String[] args) throws Exception {
        final var warnings = new ArrayList<String>();
        final var configuration = new ConfigurationParser(warnings)
                .parseConfiguration(new File("src/main/resources/generatorConfig.xml"));
        final var defaultShellCallback = new DefaultShellCallback(true);

        final var myBatisGenerator = new MyBatisGenerator(configuration, defaultShellCallback, warnings);
        myBatisGenerator.generate(null);
    }
}

