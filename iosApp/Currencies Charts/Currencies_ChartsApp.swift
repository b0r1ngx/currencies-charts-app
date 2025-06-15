import SwiftUI
import Compose

@main
struct Currencies_ChartsApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    var body: some Scene {
        WindowGroup {
            ContentView(dependencies: appDelegate.dependencies)
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    let dependencies: Dependencies = Dependencies()
}
