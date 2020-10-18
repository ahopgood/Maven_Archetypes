package com.labs.reclusive.spring.boot;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.ImportOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchitectureIT {

    private static JavaClasses importedClasses;

    @BeforeAll
    static void setup() {
        importedClasses = new ClassFileImporter(new ImportOptions().with(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS))
                .importPackages("com.labs.reclusive.spring.boot");
    }

    @Test
    @DisplayName("Rest Model should only be accessed by Controllers")
    void testRestModel() {
        classes().that().resideInAPackage("..rest.model..")
                .should().onlyBeAccessed().byAnyPackage("..rest..")
                .check(importedClasses);
    }

    //Controller name ends in Controller
    //Controller must have @RestController annotation
    @Test
    @DisplayName("Controller should be in rest package and name should end in Controller")
    void testController() {
        classes().that().areAnnotatedWith("RestController")
                .should().resideInAPackage("..rest..")
                .andShould().haveSimpleNameEndingWith("Controller")
                .check(importedClasses);
    }

    //Service must be in service package
    //Service name ends in Service
    //Service must have @Component or @Configurable annotation
    @Test
    @DisplayName("Service should be in the service package be annotated as a Component and the name should end in Service")
    void testService() {
        classes().that().resideInAPackage("..service..")
                .and().areAnnotatedWith("Component")
                .should().haveSimpleNameEndingWith("Service")
                .check(importedClasses);
    }

    @Test
    @DisplayName("Enforce layers")
    void testLayers() {
        layeredArchitecture()
                .layer("Controller").definedBy("..rest..")
                .layer("Service").definedBy("..service..")
//                .layer("Persistence").definedBy("..persistence..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
//                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service")
                .check(importedClasses);
    }
}
