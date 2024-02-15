//
//  MapViewModel.swift
//  ISSTracker
//
//  Created by Luis Cardoza Bird on 12/2/24.
//

import Foundation
import Combine
import MapKit

class MapViewModel: ObservableObject {
    
    private var cancellables = Set<AnyCancellable>()
    private let apiService = ApiService()
    private var timer: Timer?
    
    @Published var issAnnotations: [ISSAnnotation]

    init() {
        issAnnotations = []
    }
    
    func fetchISSLocation() {
        apiService.getISSLocation().receive(on: DispatchQueue.main)
            .sink {
                completion in switch completion {
                    case .finished: break
                    case .failure(let error): print("Error: \(error)")
                }
            } receiveValue: { [weak self] response in
                print("Coordinates: \(response.iss_position.latitude), \(response.iss_position.longitude)")
                let annotation = ISSAnnotation(coordinate: CLLocationCoordinate2D(latitude: Double(response.iss_position.latitude) ?? 0, longitude: Double(response.iss_position.longitude) ?? 0))
                self?.issAnnotations = [annotation]
            }.store(in: &cancellables)
    }

    func startFetchingLocation(every interval: TimeInterval = 1.0) {
        print("SCHEDULE FETCHING")
        timer?.invalidate()
        timer = Timer.scheduledTimer(withTimeInterval: interval, repeats: true) { [weak self] _ in
            self?.fetchISSLocation()
        }
    }
    deinit {
        timer?.invalidate()
    }
}
