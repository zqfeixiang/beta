package com.dong.beta;

import com.dong.beta.config.ApplicationContextTestResourceNameType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Resource 注入优先级：
 * 1. Match by Name
 * 2. Match by Type
 * 3. Match by Qualifier
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = ApplicationContextTestResourceNameType.class)
public class FieldResourceInjectionIntegrationTest {

    // Match by Name
    @Resource(name = "namedFile")
    private File defaultFile;

    // Match by Type
    @Resource
    private File defaultFile2;

    @Test
    public void givenResourceAnnotation_WhenOnField_ThenDependencyValid() {
        assertNotNull(defaultFile);
        assertEquals("namedFile.txt", defaultFile.getName());
    }
    @Test
    public void givenResourceAnnotation_WhenOnField_ThenDependencyValid2() {
        assertNotNull(defaultFile2);
        assertEquals("namedFile.txt", defaultFile2.getName());
    }
}