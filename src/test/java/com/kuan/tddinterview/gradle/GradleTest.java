package com.kuan.tddinterview.gradle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class GradleTest {

    @Test
    public void should_get_messages_properties_file_from_resources_folder() {
        String currentDir = System.getProperty("user.dir");
        String separator = File.separator;

        String targetBuildFilePath = String.join(separator,
                currentDir, "build", "resources", "main", "messages_zh_CN.properties");
        String targetOutFilePath = String.join(separator,
                currentDir, "out", "production", "resources", "messages_zh_CN.properties");

        Assertions.assertTrue(new File(targetBuildFilePath).exists() || new File(targetOutFilePath).exists());
    }


}
