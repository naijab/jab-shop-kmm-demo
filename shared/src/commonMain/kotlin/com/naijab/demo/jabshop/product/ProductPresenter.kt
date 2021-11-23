package com.naijab.demo.jabshop.product

interface ProductPresentLogic {
    fun presentAllProduct(products: List<Product>)
    fun presentGetProductError(message: String)
    fun presentLoading()
    fun presentHideLoading()
}

interface ProductDisplayLogic {
    fun showAllProduct(products: List<Product>)
    fun showErrorGetAllProduct(message: String)
    fun showLoading()
    fun hideLoading()
}

class ProductPresenter(private val controller: ProductDisplayLogic): ProductPresentLogic {

    override fun presentAllProduct(products: List<Product>) {
        controller.showAllProduct(products)
    }

    override fun presentGetProductError(message: String) {
        controller.showErrorGetAllProduct(message)
    }

    override fun presentLoading() {
        controller.showLoading()
    }

    override fun presentHideLoading() {
        controller.hideLoading()
    }

}

