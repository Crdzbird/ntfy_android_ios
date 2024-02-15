//
//  ISSLocation.swift
//  ISSTracker
//
//  Created by Luis Cardoza Bird on 11/2/24.
//

import Foundation

struct ISSLocation: Codable{
    let timestamp: Int
    let iss_position: ISSPosition
    let message: String
}
