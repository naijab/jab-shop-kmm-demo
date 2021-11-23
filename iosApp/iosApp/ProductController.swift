//
//  ProductController.swift
//  iosApp
//
//  Created by Nattapon Pondongnok on 23/11/2564 BE.
//  Copyright © 2564 BE orgName. All rights reserved.
//

import Foundation
import shared

class ProductController: ObservableObject {
    
    @Published var products: [Product] = []
    @Published var isLoading: Bool = false
    @Published var errorMessage: String? = nil
    
    private var presenter: ProductPresentLogic!
    var interactor: ProductBusinessLogic!
    
    init() {
        let worker = ProductAPI()
        presenter = ProductPresenter(controller: self)
        interactor = ProductInteractor(presenter: presenter, worker: worker)
    }
}

extension ProductController: ProductDisplayLogic {
    
    func showAllProduct(products: [Product]) {
        self.products = products
    }
    
    func showErrorGetAllProduct(message: String) {
        errorMessage = message
    }
    
    func showLoading() {
        isLoading = true
    }
    
    func hideLoading() {
        isLoading = false
    }
    
}
