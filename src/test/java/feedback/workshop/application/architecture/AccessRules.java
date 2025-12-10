package feedback.workshop.application.architecture;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;

import org.junit.runner.RunWith;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "feedback.workshop.application")
public class AccessRules {

  @ArchTest
  public static final ArchRule layer_dependencies_are_respected = layeredArchitecture().consideringAllDependencies()

      .layer("Controllers").definedBy("feedback.workshop.application.infrastructure.web.controller..")
      .layer("Services").definedBy("feedback.workshop.application.domain.service..")
      .layer("UseCases").definedBy("feedback.workshop.application.usecase..")
      .layer("Repository").definedBy("feedback.workshop.application.domain.repository..")

      .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
      .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers", "UseCases")
      .whereLayer("Repository").mayOnlyBeAccessedByLayers("Services", "UseCases");
}
