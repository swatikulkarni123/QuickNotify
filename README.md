# **QuickNotify**  

A **lightweight Jetpack Compose library** for showing **toast-style notifications** using a **global overlay**.  
**Simple API**, customizable UI, and works across all Activities without setup.

---

## 🚀 **Features**
- **Global overlay** attached automatically using `App Startup`  
- **Jetpack Compose** based toast UI  
- **Custom message, icon, and duration**  
- **Coroutine-based visibility handling**  
- **Easy one-line usage**

  ## ✅ Benefits
- **No need to write or predefine**: Toasts work automatically across all Activities
- **Lightweight**: Minimal dependencies, fast performance
- **Customizable**: Change message, icon, and duration
- **Composable-friendly**: Fully works in Jetpack Compose projects
- **Reusable**: Can be used in multiple modules or projects easily
- **Clean API**: One-line notification display

---

## 📦 **Installation**
**Step 1: Add JitPack repository**

Add it in your `settings.gradle.kts` at the end of repositories:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```


```gradle
dependencies {
    implementation("com.github.swatikulkarni123:ToastImageLibrary:1.0.0")
}
````
## 🛠 Usage

### Show a toast
```kotlin
QuickNotify.showToast(
    message = "Hello world!",
    duration = 2000L
)

Button(onClick = {
    QuickNotify.showToast("This is QuickNotify!")
}) {
    Text("Show Toast")
}
```

## ⚙️ How it works
- Automatically initializes via **`QuickNotifyInitializer`**
- Injects a **ComposeView overlay** into the first launched Activity
- Hosts toast UI through **`QuickNotifyHostInternal`**
- Uses **`LaunchedEffect` + coroutine delay** for visibility timing

---
