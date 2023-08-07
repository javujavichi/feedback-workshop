package feedback.workshop.architecture;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.junit.AnalyzeClasses;
import org.junit.runner.RunWith;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "feedback.workshop.application")
public class LayeredArchitecture {

  @ArchTest
  public static final ArchRule layer_dependencies_are_respected = layeredArchitecture().consideringAllDependencies()

      .layer("Controllers").definedBy("feedback.workshop.application.controller..")
      .layer("Services").definedBy("feedback.workshop.application.service..")
      .layer("Persistence").definedBy("feedback.workshop.application.persistence..")

      .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
      .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
      .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services");

}
