# Android Bidding App

An Android application written in Java that lets users register, log in, browse items, and place bids. The app includes a dashboard for navigating between features and a PHP script for the login backend.

## Features

- User registration and login
- Dashboard for navigating the app
- Browse and view items
- Place and view bids
- Background task handling for server communication
- Custom UI styling for buttons, input fields, and error states

## Project Structure

| File | Purpose |
|------|---------|
| `MainActivity.java` | App entry screen |
| `LoginActivity.java` | User login screen |
| `Register.java` | User registration screen |
| `DashboardActivity.java` | Main dashboard after login |
| `Bids.java` | Bidding screen logic |
| `BackgroundWorker.java` | Runs background tasks |
| `AndroidManifest.xml` | App configuration, activities, and permissions |
| `activity_*.xml`, `item_view.xml`, `layout.xml` | Screen and list item layouts |
| `button_background.xml`, `edittext_backgroun.xml`, `error_background.xml` | UI style drawables |
| `login.php` | Server-side login script |
| `build.gradle`, `settings.gradle`, `gradle.properties` | Gradle build configuration |
| `logo.jpg`, `icon.png`, `bob.jpeg` | Image assets |

## Tech Stack

Java · Android SDK · XML layouts · Gradle · PHP

## Getting Started

### Prerequisites

- Android Studio
- Android SDK and an emulator or physical Android device
- A local web server with PHP (such as XAMPP) for the login script

### Run the App

1. Clone the repository:

```bash
git clone https://github.com/TimBroAhm/Android-Project.git
```

2. Open the project in Android Studio and let Gradle sync.
3. Host `login.php` on your web server and point the app to your server address.
4. Run the app on an emulator or connected device.

## Future Work

- Add real-time bid updates
- Improve password security and input validation
- Add item images and search
- Migrate to a modern architecture with Retrofit and a REST API

## Author

**Tim** ([@TimBroAhm](https://github.com/TimBroAhm))
