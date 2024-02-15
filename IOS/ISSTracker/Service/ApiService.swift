//
//  ApiService.swift
//  ISSTracker
//
//  Created by Luis Cardoza Bird on 11/2/24.
//

import Foundation
import Combine

class ApiService {
    func getISSLocation() -> AnyPublisher<ISSLocation, Error> {
        let url = URL(string: "http://api.open-notify.org/iss-now.json")
        return URLSession.shared.dataTaskPublisher(for: url!)
            .map(\.data)
            .decode(type: ISSLocation.self, decoder: JSONDecoder())
            .eraseToAnyPublisher()
    }
}
