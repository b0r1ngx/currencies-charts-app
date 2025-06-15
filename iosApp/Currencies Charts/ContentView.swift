import SwiftUI
import Compose

let gradient = LinearGradient(
    colors: [
        Color.black.opacity(0.6),
        Color.black.opacity(0.6),
        Color.black.opacity(0.5),
        Color.black.opacity(0.3),
        Color.black.opacity(0.0),
    ],
    startPoint: .top, endPoint: .bottom
)

struct ContentView: View {
    let dependencies: Dependencies

    var body: some View {
        ZStack {
            ComposeView(dependencies: dependencies)
                .ignoresSafeArea(.all) // Compose has own keyboard handler

            VStack {
                gradient.ignoresSafeArea(edges: .top).frame(height: 0)
                Spacer()
            }
        }.preferredColorScheme(.dark)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    let dependencies: Dependencies

    func makeUIViewController(context: Context) -> UIViewController {
        let controller = MainViewControllerKt.MainViewController(dependencies: dependencies)
        controller.overrideUserInterfaceStyle = .light
        return controller
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

//#Preview {
//    ContentView()
//}
