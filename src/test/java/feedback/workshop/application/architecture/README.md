# Architecture Tests

This folder contains ArchUnit tests to enforce architectural rules and conventions in the feedback-workshop application.

## Getting Started

ArchUnit is a Java library that allows you to check the architecture of your application using unit tests. These tests help maintain clean architecture by preventing unwanted dependencies and enforcing naming conventions.

## Test Structure

Each test class should follow this basic structure:

```java
package feedback.workshop.application.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "feedback.workshop.application")
public class YourTestClassName {

    @ArchTest
    public static final ArchRule your_rule_name = classes()
        .that()./* your condition */
        .should()./* your assertion */
        .because("explanation of why this rule exists");
}
```

## Tests to Implement

### 1. NamingConventions.java
Enforce naming standards across the application:
- Controllers should end with "Controller"
- Services should end with "Service"
- UseCases should end with "UseCase"
- Repositories should end with "Repository"

### 2. AccessRules.java
Define and enforce layered architecture rules:
- Controllers layer
- Services layer
- UseCases layer
- Repository layer

### 3. PackageCycleTest.java
Detect circular dependencies between packages to maintain clean architecture.

**What are circular dependencies?**
A circular dependency (or cyclic dependency) occurs when package A depends on package B, and package B depends on package A (directly or through other packages). This creates coupling and makes code harder to maintain and test.

**Example of a violation:**
- `usecase` package imports a class from `infrastructure.web.model` (DTO)
- This creates a dependency: `usecase` → `infrastructure`
- But `infrastructure.web.controller` also depends on `usecase`
- Result: `usecase` ↔ `infrastructure` cycle ❌

**How to test:**
```java
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@ArchTest
public static final ArchRule no_cycles = slices()
    .matching("feedback.workshop.application.(*)..")
    .should().beFreeOfCycles();
```

**Common violations:**
- Use cases accepting DTOs from infrastructure layer
- Domain services importing web models
- Repositories depending on controller packages

**How to fix:**
- Use cases should accept primitives (String, Integer) instead of DTOs
- Controllers should map DTOs → primitives before calling use cases
- Keep dependencies flowing in one direction: `controller` → `usecase` → `domain`

### 4. AnnotationRulesTest.java
Validate proper use of Spring Framework annotations:
- Controllers use `@RestController`
- Services use `@Service`
- Repositories extend `JpaRepository` or use `@Repository`

## Running the Tests

```bash
./gradlew test --tests '*architecture*'
```

## Expected Results

⚠️ **Important:** These tests are expected to fail initially! The failures reveal architectural violations in the codebase that can be discussed and potentially fixed.

## Resources

- [ArchUnit User Guide](https://www.archunit.org/userguide/html/000_Index.html)
- [ArchUnit API Documentation](https://javadoc.io/doc/com.tngtech.archunit/archunit/latest/index.html)
- [ArchUnit Examples on GitHub](https://github.com/TNG/ArchUnit-Examples)
