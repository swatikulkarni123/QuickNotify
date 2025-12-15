## QuickNotify – Jetpack Compose Toast, Snackbar, Dialog & Custom Alert Library

QuickNotify helps Android developers easily show **Toast**, **Snackbar**, **Dialog**, and **Custom Alerts** in **Jetpack Compose** apps...


[![](https://jitpack.io/v/swatikulkarni123/QuickNotify.svg)](https://jitpack.io/#swatikulkarni123/QuickNotify)

A **lightweight Jetpack Compose library** for showing  
**Toast-style notifications**, **Snackbars**, and **Custom Dialogs**  
using a **global overlay** with **zero setup**.

Works across all Activities without needing a host Composable.

---

## 🚀 **Features**
- Global overlay via **App Startup**
- Jetpack Compose UI — Toast, Snackbar, Dialog
- **Toast** with **text + icon + custom duration**
- **Snackbar** with ** icon + custom duration **
- **Dialog** with:
    - Header image
    - Title
    - Body text
    - Up to **3 customizable buttons**
    - Optional **top-right close icon**
    - Rounded or square corners
- **Custom Overlay** for showing any Composable UI globally:
    * Full control over content, alignment, and appearance.
    * Manual or automatic dismissal.
- Coroutine-based visibility handling
- Works across entire application

---
### How to Display Toast in Jetpack Compose

To display a toast in Jetpack Compose, you can use the QuickNotify library.

## 📦 **Installation**

### **Step 1: Add JitPack**

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

### **Step 2: Add dependency**

```gradle
dependencies {
    implementation("com.github.swatikulkarni123:QuickNotify:1.0.0")
}
```

---

# 🛠 Usage

---

## ✅ **1. Toast**

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

## ✅ **2. Snackbar**

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

## ✅ **3. Dialog**

Shows a fully customizable dialog with an optional **top image**,  
**title**, **body**, and **up to 3 buttons**.

### Example
```kotlin
QuickNotify.showDialog(
    title = "Delete Item?",
    body = "This action cannot be undone.",
    topImage = painterResource(R.drawable.warning),

    btn1Text = "Cancel",
    btn1Color = Color.Gray,
    onBtn1Click = { },

    btn2Text = "Delete",
    btn2Color = Color.Red,
    onBtn2Click = { /* Delete logic */ }
)
```

### Dialog only close button (no action buttons)
```kotlin
QuickNotify.showDialog(
    title = "Info",
    body = "This is message-only dialog."
)
```

This will show a **top-right close icon** automatically.


## ✨ **4. Custom Overlay / Custom View**

Use `showOverlay` to display **any Composable UI** in a global overlay. This is ideal for custom alerts, banners, or full-screen loaders.

The `content` lambda provides a `dismiss: () -> Unit` function, which you must call to manually dismiss the overlay.

### Example: Custom Success Alert
```kotlin
QuickNotify.showOverlay(
    overlayAlignment = Alignment.Center, // Position your custom view
    autoCancel = false, // Set to true to dismiss after default duration (2000ms) or 'duration'
    content = { dismiss -> // 'dismiss' function to manually close the overlay
        Card(
            // ... your custom UI for the alert ...
            shape = RoundedCornerShape(20.dp),
            // ...
        ) {
            Row(
                // ... your alert content ...
            ) {
                // ...
                // Example of a dismiss button inside the custom view
                Icon(
                    // ...
                    modifier = Modifier.size(20.dp).clickable {
                        dismiss() // **Crucial: Call dismiss() to close the overlay**
                        // You can also show another QuickNotify message here
                        // QuickNotify.showToast("Clicked") 
                    }
                )
            }
        }
    }
)
```

---

# ⚙️ How it works

- Automatically initializes via `QuickNotifyInitializer`
- Uses a hidden **global Compose host**
- Renders Toast/Snackbar/Dialog using:
    - `QuickNotifyHostInternal`
    - `QuickNotifyController`
- Auto-dismiss using coroutine delays
- Manual dismiss:
```kotlin
QuickNotifyController.clear()
```

---

## 📁 Icons / Resources

If using a close icon, place it here:

```
your-library-module/
 └── src/
      └── main/
           └── res/
                └── drawable/
                     └── ic_close.xml
```

Use inside dialog:
```kotlin
painterResource(R.drawable.ic_close)
```

---

## 📜 License

MIT
