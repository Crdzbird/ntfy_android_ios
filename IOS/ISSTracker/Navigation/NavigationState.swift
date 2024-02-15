//
//  NavigationState.swift
//  ISSTracker
//
//  Created by Luis Cardoza Bird on 11/2/24.
//

import Foundation
import Combine

class NavigationState: ObservableObject {
    @Published var page: Page = .login
    
    enum Page {
        case login
        case map
    }
}
