//
//  MapScreen.swift
//  ISSTracker
//
//  Created by Luis Cardoza Bird on 12/2/24.
//

import SwiftUI
import Combine
import MapKit

struct MapScreen: View {
    @ObservedObject var mapViewModel = MapViewModel()
    @State private var region = MKCoordinateRegion(
    center: CLLocationCoordinate2D(), span: MKCoordinateSpan(latitudeDelta: 180, longitudeDelta: 180))
    
    var body: some View {
        ZStack{
            Map(coordinateRegion: $region, annotationItems:mapViewModel.issAnnotations){ annotation in
                MapAnnotation(coordinate: annotation.coordinate){
                    Circle().fill(Color.black).frame(width: 20, height: 20)
                }
            }.onAppear() {
                mapViewModel.startFetchingLocation(every: 2.0)
            }
        }
    }
}

#Preview {
    MapScreen()
}
