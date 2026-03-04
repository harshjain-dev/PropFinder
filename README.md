# PropFinder

Android grocery list app built with Kotlin, Jetpack Compose, Room, Coroutines, and Hilt, organized with a Clean Architecture style split (`app`, `domain`, `data`).

## Features

- Add grocery items with category selection
- Edit, delete, and mark items as purchased
- Filter grocery list by category
- Seed default categories from local JSON asset

## Architecture

This project follows a layered modular setup:

- `:app`
  - Presentation/UI layer
  - Compose screens/components
  - `MainViewModel` uses domain use cases only
- `:domain`
  - Business models
  - Repository interfaces
  - Use cases (`AddGroceryItemUseCase`, `ObserveGroceryItemsUseCase`, etc.)
- `:data`
  - Room database, DAOs, entities
  - Repository implementations
  - Mappers and seed loader
  - Hilt modules (`@Binds` and `@Provides`)

Dependency direction:

- `app -> domain`
- `data -> domain`
- `app` also includes `data` so Hilt can resolve bindings contributed by `data`

## Tech Stack

- Kotlin
- Jetpack Compose (Material 3)
- Coroutines + Flow
- Room
- Hilt (DI)
- Gradle (KTS, Version Catalog)

## Requirements

- Android Studio (latest stable recommended)
- JDK 17
- Android SDK matching project config (`minSdk 26`, `targetSdk 36`)

## Getting Started

1. Clone and open in Android Studio.
2. Let Gradle sync complete.
3. Run the `app` configuration on an emulator/device.

## Build & Verify

```bash
./gradlew :app:assembleDebug
./gradlew :app:lintDebug :data:lintDebug :domain:lintDebug
./gradlew :app:testDebugUnitTest :data:testDebugUnitTest :domain:testDebugUnitTest
```

## Hilt Wiring Notes

- Interfaces live in `domain.repository.*`
- Implementations live in `data.repository.*`
- Binding module:
  - `data/src/main/java/com/harshjaindev/data/di/RepositoryModule.kt`

Example:

- `GroceryRepositoryImpl` -> `domain.repository.GroceryRepository`
- `CategoryRepositoryImpl` -> `domain.repository.CategoryRepository`

## Project Structure

```text
PropFinder/
  app/
    src/main/java/com/harshjaindev/propfinder/
      presentation/
      ui/
  domain/
    src/main/java/com/harshjaindev/domain/
      models/
      repository/
      usecases/
  data/
    src/main/java/com/harshjaindev/data/
      di/
      local/
      mappers/
      repository/
```

## Future Improvements

- Add migration strategy instead of destructive fallback
- Add unit tests for use cases and ViewModel state flows
- Add repository instrumentation tests for Room
- Improve accessibility and localization coverage in UI

