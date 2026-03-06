## Compose Multiplatform Toast, Snackbar, Dialog & Custom Alert Library

QuickNotify is a **Kotlin Multiplatform** library (powered by Compose Multiplatform) for showing
Toast messages, Snackbars, Dialogs, and custom alerts across **Android, iOS, Desktop, and Web**
with **zero setup** on every platform.

[![](https://jitpack.io/v/swatikulkarni123/QuickNotify.svg)](https://jitpack.io/#swatikulkarni123/QuickNotify)

---

## Supported Platforms

| Platform | Support | Setup required |
|---|---|---|
| Android | Full | None — auto-attaches via App Startup |
| iOS | Full | None — auto-attaches via UIKit overlay |
| Desktop (JVM) | Full | None — auto-attaches via Swing glass pane |
| Web (WasmJS) | Full | None — auto-attaches via canvas overlay |

---

## Features

- **Zero setup on all platforms** — just call `QuickNotify.showToast(...)` anywhere
- Global overlay auto-attaches on first use — same pattern as Android on every platform
- **Toast** with text + icon + custom duration
- **Snackbar** with icon + custom duration
- **Dialog** with header image, title, body, up to 3 customizable buttons, optional close icon
- **Custom Overlay** — show any Composable UI globally with full control over alignment and appearance
- Coroutine-based auto-dismiss

---

## How the auto-attach overlay works

| Platform | Mechanism |
|---|---|
| Android | `ComposeView` added to the first Activity window via `ActivityLifecycleCallbacks` (App Startup) |
| Desktop | `ComposePanel` set as the `glassPane` of the active `JFrame` |
| iOS | `ComposeUIViewController` added as a child view controller on the key `UIWindow`'s root VC |
| Web | A full-screen `<canvas>` element appended above existing page content |

---

## Installation

### Step 1: Add JitPack

```kotlin
// settings.gradle.kts
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

# Usage

No setup. No wrapping. Just call it.

---

## 1. Toast

```kotlin
QuickNotify.showToast("Hello world!")
```

```kotlin
QuickNotify.showToast(
    message = "Saved successfully",
    duration = 2500L,
    icon = Icons.Default.Check
)
```

---

## 2. Snackbar

```kotlin
QuickNotify.showSnackbar(message = "No internet connection")
```

```kotlin
QuickNotify.showSnackbar(
    message = "Message sent",
    icon = Icons.Default.Send
)
```

---

## 3. Dialog

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

#### Dialog with close button only

```kotlin
QuickNotify.showDialog(
    title = "Info",
    body = "This is a message-only dialog."
)
```

---

## 4. Custom Overlay

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

## Manual dismiss

```kotlin
QuickNotifyController.clear()
```

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
