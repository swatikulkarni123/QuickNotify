## Compose Multiplatform Toast, Snackbar, Dialog & Custom Alert Library

QuickNotify is a **Kotlin Multiplatform** library (powered by Compose Multiplatform) for showing
Toast messages, Snackbars, Dialogs, and custom alerts across **Android, iOS, Desktop, and Web**.

[![](https://jitpack.io/v/swatikulkarni123/QuickNotify.svg)](https://jitpack.io/#swatikulkarni123/QuickNotify)

---

## Supported Platforms

| Platform | Support | Setup required |
|---|---|---|
| Android | Full | None (auto-init via App Startup) |
| iOS | Full | Wrap root with `QuickNotifyHost` |
| Desktop (JVM) | Full | Wrap root with `QuickNotifyHost` |
| Web (WasmJS) | Full | Wrap root with `QuickNotifyHost` |

---

## Features

- **Kotlin Multiplatform** — one API, all platforms
- Global overlay — zero setup on Android; one-line wrapper on other platforms
- **Toast** with text + icon + custom duration
- **Snackbar** with icon + custom duration
- **Dialog** with header image, title, body, up to 3 customizable buttons, optional close icon
- **Custom Overlay** — show any Composable UI globally with full control over content, alignment, and appearance
- Coroutine-based auto-dismiss

---

## Installation

### Step 1: Add JitPack

Add in `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Step 2: Add dependency

```gradle
dependencies {
    implementation("com.github.swatikulkarni123:QuickNotify:2.0.0")
}
```

---

## Setup

### Android

No setup needed. The library auto-attaches via App Startup. Just call `QuickNotify.showToast(...)` anywhere.

### iOS, Desktop, and Web

Wrap your root composable with `QuickNotifyHost`:

#### iOS (`MainViewController.kt`)
```kotlin
import com.swa.quicknotify.core.QuickNotifyHost

fun MainViewController() = ComposeUIViewController {
    QuickNotifyHost {
        App()
    }
}
```

#### Desktop (`main.kt`)
```kotlin
import com.swa.quicknotify.core.QuickNotifyHost

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "MyApp") {
        QuickNotifyHost {
            App()
        }
    }
}
```

#### Web (`main.kt`)
```kotlin
import com.swa.quicknotify.core.QuickNotifyHost

fun main() {
    onWasmReady {
        CanvasBasedWindow("MyApp") {
            QuickNotifyHost {
                App()
            }
        }
    }
}
```

> On Android, `QuickNotifyHost` is optional but can still be used if you prefer the explicit pattern.

---

# Usage

All four notification types work identically on every platform.

---

## 1. Toast

#### Simple toast
```kotlin
QuickNotify.showToast("Hello world!")
```

#### Toast with duration + icon
```kotlin
QuickNotify.showToast(
    message = "Saved successfully",
    duration = 2500L,
    icon = Icons.Default.Check
)
```

---

## 2. Snackbar

#### Basic Snackbar
```kotlin
QuickNotify.showSnackbar(
    message = "No internet connection"
)
```

#### Snackbar with icon
```kotlin
QuickNotify.showSnackbar(
    message = "Message sent",
    icon = Icons.Default.Send
)
```

---

## 3. Dialog

Shows a fully customizable dialog with an optional top image, title, body, and up to 3 buttons.

```kotlin
QuickNotify.showDialog(
    title = "Delete Item?",
    body = "This action cannot be undone.",

    btn1Text = "Cancel",
    btn1Color = Color.Gray,
    onBtn1Click = { },

    btn2Text = "Delete",
    btn2Color = Color.Red,
    onBtn2Click = { /* Delete logic */ }
)
```

#### Dialog with close button only (no action buttons)
```kotlin
QuickNotify.showDialog(
    title = "Info",
    body = "This is a message-only dialog."
)
```

This shows a top-right close icon automatically.

---

## 4. Custom Overlay

Use `showOverlay` to display any Composable UI in a global overlay.

```kotlin
QuickNotify.showOverlay(
    overlayAlignment = Alignment.Center,
    autoCancel = false,
    content = { dismiss ->
        Card(shape = RoundedCornerShape(20.dp)) {
            Row {
                Text("Operation complete!")
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.clickable { dismiss() }
                )
            }
        }
    }
)
```

---

# How it works

### Android
- Automatically initializes via `QuickNotifyInitializer` (App Startup)
- Attaches a hidden `ComposeView` to the first Activity window

### iOS / Desktop / Web
- Notifications are rendered inside `QuickNotifyHost` which wraps your app content
- Uses Compose state (`QuickNotifyController.currentMessage`) to trigger rendering

### All platforms
- `QuickNotifyController` holds shared state (Compose `mutableStateOf`)
- `QuickNotifyHostInternal` renders the correct UI for Toast / Snackbar / Dialog / Overlay
- Auto-dismiss uses `kotlinx.coroutines.delay`
- Manual dismiss: `QuickNotifyController.clear()`

---

## License

MIT

---

### Keywords
Compose Multiplatform Toast
Compose Multiplatform Snackbar
Compose Multiplatform Dialog
Kotlin Multiplatform Notification
Android iOS Desktop Web Compose Alert
