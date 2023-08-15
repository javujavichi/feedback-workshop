package feedback.workshop.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.stereotype.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "feedback.workshop.application")
public class NamingConventions {

  @ArchTest
  public static ArchRule classes_named_controller_should_be_in_a_controller_package = classes()
      .that().haveSimpleNameEndingWith("Controller")
      .should().resideInAPackage("..controller..");

  @ArchTest
  public static ArchRule services_should_be_suffixed = classes()
      .that().resideInAPackage("..service..")
      .and().areAnnotatedWith(Service.class)
      .should().haveSimpleNameEndingWith("Service");
 
  @ArchIgnore
  @ArchTest
  public static ArchRule controllers_should_be_suffixed = classes()
      .that().resideInAPackage("..controller..")
      .or().areAnnotatedWith(Controller.class)
      .should().haveSimpleNameEndingWith("Controller");

}
