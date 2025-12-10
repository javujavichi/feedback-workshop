package feedback.workshop.application.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "feedback.workshop.application")
public class PackageCycleTest {

  @ArchTest
  public static final ArchRule no_cycles_between_packages = slices()
      .matching("feedback.workshop.application.(*)..")
      .should().beFreeOfCycles()
      .because("Circular dependencies between packages should be avoided to maintain clean architecture");

  @ArchTest
  public static final ArchRule no_cycles_in_domain = slices()
      .matching("feedback.workshop.application.domain.(*)..")
      .should().beFreeOfCycles()
      .because("Domain layer should not have circular dependencies between its sub-packages");

  @ArchTest
  public static final ArchRule no_cycles_in_infrastructure = slices()
      .matching("feedback.workshop.application.infrastructure.(*)..")
      .should().beFreeOfCycles()
      .because("Infrastructure layer should not have circular dependencies between its sub-packages");
}
