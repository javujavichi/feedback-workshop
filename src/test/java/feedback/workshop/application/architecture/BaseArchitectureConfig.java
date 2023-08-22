package feedback.workshop.application.architecture;

import org.junit.jupiter.api.BeforeAll;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

public abstract class BaseArchitectureConfig {
  protected static final String CONTROLLER_PACKAGES = "feedback.workshop.application";
    protected static final String SERVICE_PACKAGES = "feedback.workshop.application";
    protected static final String REPOSITORY_PACKAGES = "feedback.workshop.application";


    protected static JavaClasses classes;

    @BeforeAll
    public static void setUp() {
        classes =
                new ClassFileImporter()
                        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
                        .importPackages("feedback.workshop.application");
    }
}
