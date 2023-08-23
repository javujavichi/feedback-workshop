package feedback.workshop.application.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import javax.persistence.Entity;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "feedback.workshop.application")
public class AnnotationRulesTest {


  @ArchTest
  public ArchRule components_should_be_located_in_infrastructure = classes()
      .that().areAnnotatedWith(Component.class)
      .should().resideInAPackage("..infrastructure..");

  @ArchTest
  public ArchRule repositories_should_be_located_in_the_domain = classes()
      .that().areAnnotatedWith(Repository.class)
      .should().resideInAPackage("..domain..");

  @ArchTest
  public static ArchRule controller_should_be_located_in_infrastructure = classes()
      .that().areAnnotatedWith(RestController.class)
      .should().resideInAPackage("..infrastructure..");

  @ArchTest
  public static ArchRule entities_should_be_located_in_domain = classes()
      .that().areAnnotatedWith(Entity.class)
      .should().resideInAPackage("..domain..");
}
