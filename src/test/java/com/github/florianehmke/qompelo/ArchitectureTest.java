package com.github.florianehmke.qompelo;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
    packages = "com.github.florianehmke.qompelo",
    importOptions = {ImportOption.DoNotIncludeTests.class})
public class ArchitectureTest {

  @ArchTest
  public static final ArchRule publicClasses =
      classes()
          .that()
          .haveModifier(JavaModifier.PUBLIC)
          .should()
          .haveSimpleNameEndingWith("Controller")
          .orShould()
          .resideInAnyPackage("..models", "..util", "..configuration")
          .as("Only controllers, models, configuration and utility classes may be public.");

  @ArchTest
  public static final ArchRule packagePrivateClasses =
      classes()
          .that()
          .doNotHaveModifier(JavaModifier.PUBLIC)
          .should()
          .bePackagePrivate()
          .as("Everything is package private.");
}
