//
//  ISSAnnotation.swift
//  ISSTracker
//
//  Created by Luis Cardoza Bird on 12/2/24.
//

import Foundation
import MapKit

struct ISSAnnotation: Identifiable, Equatable {
    let id = UUID()
    var coordinate: CLLocationCoordinate2D
    
    static func ==(lhs: ISSAnnotation, rhs: ISSAnnotation) -> Bool {
        return lhs.coordinate.latitude == rhs.coordinate.latitude && lhs.coordinate.longitude == rhs.coordinate.longitude
    }
}
