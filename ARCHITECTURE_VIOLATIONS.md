# Architecture Violations Found

The PackageCycleTest detected 3 circular dependencies in the codebase. These violations prevent clean separation of concerns and make the code harder to maintain.

## Violation 1: domain ↔ usecase Cycle

**Problem:** Domain services implement use case interfaces, while use cases depend on domain repositories and entities.

**Dependencies:**
- `domain.service.GetFeedbackByGivenByUseCaseImplService` implements `usecase.GetFeedbackByGivenByUseCase`
- Use cases depend on `domain.repository.FeedbackRepository` and `domain.entity.Feedback`

**Root Cause:** Use case interfaces are in the wrong package. In clean architecture:
- **Use cases** should define application-specific business rules
- **Domain** should contain pure business logic (entities, value objects)
- Use case **implementations** can be in domain/service OR application layer
- Use case **interfaces** should be in the application/use case layer

**Solution Options:**

### Option A: Move use case implementations out of domain.service
```
Current:
  domain/
    service/
      GetFeedbackByGivenByUseCaseImplService.java  ❌

Better:
  application/
    service/  (or usecase/impl/)
      GetFeedbackByGivenByUseCaseImpl.java  ✅
```

### Option B: Keep structure but make domain not depend on usecase
- Use case interfaces can stay in `usecase` package
- Move implementations to `application.service` (separate from domain)
- Domain services should implement domain-specific logic, not use cases

## Violation 2: domain ↔ usecase ↔ infrastructure Cycle

**Problem:** Three-way cycle between layers:
1. Domain → UseCase (implements interface)
2. UseCase → Infrastructure (depends on `SubmitFeedbackRequest` from web.model)
3. Infrastructure → Domain (mappers and controllers use domain entities)

**Root Cause:** Use cases depend on infrastructure DTOs (`SubmitFeedbackRequest`)

**Solution:** Use cases should NOT depend on infrastructure. They should either:
- Accept domain entities/primitives as parameters
- Accept use-case-specific DTOs (not infrastructure DTOs)

**Fix:**
```java
// ❌ BAD - UseCase depends on infrastructure
public Feedback submitFeedback(SubmitFeedbackRequest request) { ... }

// ✅ GOOD - UseCase depends on primitives or domain objects
public Feedback submitFeedback(String givenBy, String feedback) { ... }

// ✅ ALSO GOOD - UseCase has its own DTO
public record SubmitFeedbackCommand(String givenBy, String feedback) {}
public Feedback submitFeedback(SubmitFeedbackCommand command) { ... }
```

Then the controller maps infrastructure DTO → use case parameter:
```java
@PostMapping
public ResponseEntity<FeedbackResponse> submitFeedback(@RequestBody SubmitFeedbackRequest request) {
    Feedback feedback = submitFeedbackUseCase.submitFeedback(
        request.getGivenBy(), 
        request.getFeedback()
    );
    // ...
}
```

## Violation 3: infrastructure ↔ usecase Cycle

**Problem:** 
1. Infrastructure (controllers) → UseCase (calls use case methods)
2. UseCase → Infrastructure (depends on `SubmitFeedbackRequest`)

**Root Cause:** Same as Violation 2 - use cases depend on infrastructure DTOs.

**Solution:** Same fix as Violation 2.

---

## Recommended Refactoring Steps (Workshop Exercise)

### Step 1: Remove infrastructure dependency from use cases
1. Update `SubmitFeedbackUseCase` to accept primitives:
   ```java
   public Feedback submitFeedback(String givenBy, String feedback)
   ```
2. Update `FeedbackController` to map the request:
   ```java
   Feedback feedback = submitFeedbackUseCase.submitFeedback(
       submitFeedbackRequest.getGivenBy(),
       submitFeedbackRequest.getFeedback()
   );
   ```

### Step 2: Reorganize use case implementations
Either:
- **Option A:** Move `*UseCaseImplService` classes from `domain.service` to `application.service`
- **Option B:** Create separate domain services that don't implement use case interfaces

### Step 3: Verify
Run the tests:
```bash
./gradlew test --tests "*PackageCycleTest*"
```

---

## Clean Architecture Principles (Reference)

```
┌─────────────────────────────────────┐
│         Infrastructure              │
│  (Controllers, Persistence,         │
│   Mappers, External APIs)           │
│         ↓ depends on                │
├─────────────────────────────────────┤
│         Use Cases                   │
│  (Application Business Rules)       │
│         ↓ depends on                │
├─────────────────────────────────────┤
│         Domain                      │
│  (Entities, Value Objects,          │
│   Domain Services, Repositories)    │
│         (no dependencies)           │
└─────────────────────────────────────┘
```

**Key Rules:**
- ✅ Infrastructure can depend on Use Cases and Domain
- ✅ Use Cases can depend on Domain
- ❌ Domain should NOT depend on Use Cases or Infrastructure
- ❌ Use Cases should NOT depend on Infrastructure

---

## For Workshop Participants

This is a **chaos branch exercise**. Your goal:
1. Understand the violations from the test output
2. Identify the root causes
3. Refactor the code to break the cycles
4. Run tests to verify the architecture is clean

Good luck! 🚀
