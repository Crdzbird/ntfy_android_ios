//
//  ISSTrackerApp.swift
//  ISSTracker
//
//  Created by Luis Cardoza Bird on 11/2/24.
//

import SwiftUI

@main
struct ISSTrackerApp: App {
    @StateObject var navigationState = NavigationState()
    var body: some Scene {
        WindowGroup {
            switch navigationState.page {
            case .map: MapScreen(mapViewModel: MapViewModel())
            case .login: MapScreen(mapViewModel: MapViewModel())
            }
        }
    }
}
