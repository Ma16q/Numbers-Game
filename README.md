# Numbers Game (لعبة الأرقام) 🎮

A sleek, responsive Android game built with Java and Material 3 Design components. Players test their quick-thinking skills by identifying and tapping the higher number between two randomly generated cards.

---

## 🚀 Features

- **Modern Material 3 Interface**: Card-based design with rounded corners, custom color palettes, and responsive layouts.
- **Interactive Gameplay**:
  - Live Score Tracking
  - Streak Counter (`🔥`)
  - Local High Score Persistence (`SharedPreferences`)
  - Animated Touch Feedback (Card scaling animations)
  - Color-coded feedback (Green container for correct, Red container for wrong)
- **Multi-language Support**: Fully localized in **Arabic** and **English**.
- **Edge-to-Edge Design**: Full system bar inset handling for modern Android screens.
- **Custom Adaptive Launcher Icon**: Vector adaptive icon with active selection highlights.

---

## 🛠️ Built With

- **Language**: Java
- **UI Framework**: Android XML with Material 3 Components (`com.google.android.material`)
- **Min SDK**: API 24 (Android 7.0)
- **Target SDK**: API 37
- **Build System**: Gradle (Kotlin DSL / Version Catalog)

---

## 📁 Project Structure

```text
Lab2/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/lab2/
│   │   │   │   └── MainActivity.java
│   │   │   ├── res/
│   │   │   │   ├── drawable/           # Vector icons & adaptive launcher graphics
│   │   │   │   ├── layout/             # Responsive ConstraintLayout UI
│   │   │   │   ├── values/             # English strings, colors, themes
│   │   │   │   └── values-ar/          # Arabic localized strings
│   │   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

---

## ⚙️ How to Build & Run

1. **Clone the Repository**:
   ```bash
   git clone <your-private-repo-url>
   cd Lab2
   ```

2. **Open in Android Studio**:
   - Open Android Studio -> Select `Open` -> Navigate to the `Lab2` directory.

3. **Build the Project**:
   ```bash
   ./gradlew assembleDebug
   ```

4. **Run on Device / Emulator**:
   - Select an emulator or connected device and press `Run` (`Shift + F10`).

---

## 🔒 Private Repository Instructions

To create a **Private Repository** on GitHub:

1. Go to [GitHub New Repository](https://github.com/new).
2. Name the repository (e.g., `NumbersGame` or `Lab2`).
3. Select **Private**.
4. Run the following commands in your terminal inside the project directory:

```bash
git add .
git commit -m "Initial commit: Numbers Game with Material 3 UI"
git branch -M main
git remote add origin https://github.com/<YOUR_USERNAME>/<YOUR_REPO_NAME>.git
git push -u origin main
```
