# Kora Global - Native Android Football App (Kotlin & Jetpack Compose)

Welcome to the **Kora Global** native Android application project.

## 📱 Project Status & Data Notice
- **UI Framework**: 100% Native Jetpack Compose + Material 3.
- **Data Engine**: Offline Sample Data Engine (`FakeFootballDataSource`). All scores, kickoff times, and standings are **DEMO DATA** for UI testing.
- **Moroccan Botola Pro 1 & 2 Focus**: Dedicated league hubs, 16-team rosters, round-by-round schedules, and qualification zones.
- **Multilingual RTL**: Native Arabic right-to-left layout by default, with French and English support.
- **Clean Architecture**: Repository pattern ready for plugging in Football-Data.org or API-Football.

---

## 🚀 How to Build and Obtain Your Installable APK

### 📱 Option A: If You Are on an Android Phone (No Computer Needed!)
You can use free **GitHub Actions** to build the APK in the cloud and download it directly to your phone:
1. Create a free account at [github.com](https://github.com) from your phone browser.
2. Create a new repository named `kora-global-android`.
3. Extract this ZIP file on your phone using any file manager (e.g., Google Files or ZArchiver).
4. Upload the files to your GitHub repository (or push via Git/Termux).
5. The included workflow (`.github/workflows/build-apk.yml`) will **automatically trigger**.
6. Go to the **Actions** tab on your GitHub repository.
7. Click the running workflow **"Build Android APK"**.
8. Once finished (~3 minutes), scroll down to **Artifacts** and download **`kora-global-debug-apk`**.
9. Extract and install `app-debug.apk` directly on your Android phone!

---

### 💻 Option B: Using Android Studio on a Computer
1. Extract this ZIP archive.
2. Open **Android Studio** (Koala, Iguana, or Hedgehog).
3. Select **File -> Open...** and pick the extracted folder.
4. Android Studio will recognize the `settings.gradle.kts`, `gradle/libs.versions.toml`, and wrapper.
5. In the menu, go to **Build -> Build Bundle(s) / APK(s) -> Build APK(s)**.
6. Once compiled, click **locate** in the popup to get your installable `app-debug.apk`!

---

### ⌨️ Option C: Using Command Line (Gradle CLI)
```bash
# Grant execution permission to Gradle wrapper
chmod +x gradlew

# Assemble debug APK
./gradlew assembleDebug

# The APK will be generated at:
# app/build/outputs/apk/debug/app-debug.apk
```
