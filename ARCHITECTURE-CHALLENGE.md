# 🏗️ Architecture Challenge: Package Organization

## 🎯 Challenge Overview

Welcome to the **refactor-organization-chaos** branch! This exercise will test your understanding of clean architecture principles and package organization.

## 🚨 The Problem

A new developer in the team has been reorganizing the codebase, but they've moved classes to incorrect packages! The application compiles and runs, but when you run the architecture tests, you'll discover that many classes violate the architectural rules.

Your mission: **Move the classes to their correct packages** so all architecture tests pass.

## 📋 Instructions

### Step 1: Run the Tests

First, see what's broken:

```bash
./gradlew test --tests '*architecture*'
```

Or using make:
```bash
make test-architecture
```

You should see multiple test failures indicating classes are in the wrong packages.

### Step 2: Analyze the Violations

The test output will tell you:
- Which classes are in the wrong package
- What the naming conventions expect
- Which architectural rules are being violated

Common violations you'll encounter:
- ❌ Controllers not in `..controller..` package
- ❌ Use cases not in `..usecase..` package  
- ❌ Services in wrong package
- ❌ Models/DTOs misplaced

### Step 3: Fix the Package Structure

Move each class to its correct package following clean architecture principles:

**Expected Package Structure:**
```
feedback.workshop.application/
├── controller/               ❌ WRONG - should be infrastructure.web.controller
├── service/                  ❌ WRONG - contains use cases
├── model/                    ❌ WRONG - should be infrastructure.web.model
├── domain/
│   ├── entity/              ✅ Domain entities
│   ├── repository/          ✅ Repository interfaces
│   └── service/             ✅ Domain services
├── infrastructure/
│   ├── persistence/         ✅ Persistence mappers
│   └── web/
│       ├── controller/      ✅ REST controllers (CORRECT LOCATION)
│       └── model/           ✅ DTOs/Request/Response models (CORRECT LOCATION)
└── usecase/
    ├── [Interface files]    ✅ Use case interfaces (CORRECT LOCATION)
    └── impl/                ✅ Use case implementations
```

### Step 4: Move Files Correctly

For each misplaced file:

1. **Move the file** to the correct package directory
2. **Update the package declaration** in the file
3. **Update imports** in files that reference it
4. **Re-run tests** to verify

**Example:**

If `FeedbackController` is in `application.controller`, move it to:
```bash
mv src/main/java/feedback/workshop/application/controller/FeedbackController.java \
   src/main/java/feedback/workshop/application/infrastructure/web/controller/
```

Then update its package declaration:
```java
// Change from:
package feedback.workshop.application.controller;

// To:
package feedback.workshop.application.infrastructure.web.controller;
```

### Step 5: Verify Success

Once all files are moved and updated:

```bash
./gradlew clean test
```

All tests should pass! ✅

## 💡 Hints

### Naming Convention Rules

- **Controllers**: Must end with `Controller` and be in `..controller..` package
- **Use Cases**: Must end with `UseCase` and be in `..usecase..` package
- **Services**: Must end with `Service`, be annotated with `@Service`, and be in `..service..` package
- **Repositories**: Must end with `Repository` and be in repository package

### Package Purposes

- **`domain`**: Core business logic, entities, and domain rules (framework-agnostic)
- **`infrastructure`**: Technical details (web controllers, persistence, external services)
- **`usecase`**: Application-specific business rules and orchestration
- **DTOs/Models**: Belong in `infrastructure.web.model` (presentation layer concern)

### Architecture Principles

1. **Dependency Rule**: Dependencies point inward (infrastructure → usecase → domain)
2. **No Cycles**: Packages should not have circular dependencies
3. **Separation of Concerns**: Each layer has a specific responsibility
4. **Framework Isolation**: Domain should not depend on frameworks

## ✅ Success Criteria

When you've successfully completed the challenge:

- ✅ All architecture tests pass
- ✅ No package cycle violations
- ✅ All naming conventions satisfied
- ✅ Classes are in their appropriate architectural layers
- ✅ Application still runs correctly

## 📚 Resources

- [Clean Architecture by Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [ArchUnit User Guide](https://www.archunit.org/userguide/html/000_Index.html)
- [Package by Feature vs Package by Layer](https://phauer.com/2020/package-by-feature/)

## Learning Goals

After completing this challenge, you should understand:

- How to organize packages following clean architecture principles
- The importance of proper package structure for maintainability
- How to use ArchUnit to enforce architectural rules
- The relationship between different architectural layers
- Why dependency direction matters

---

**Good luck!** 

If you get stuck, remember: The test failures are your guide. Read them carefully - they tell you exactly what's wrong and where!


@Made with ❤️ by Javiera Laso
