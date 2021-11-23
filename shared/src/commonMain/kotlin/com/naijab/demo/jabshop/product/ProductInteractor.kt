package com.naijab.demo.jabshop.product

interface ProductBusinessLogic {
    fun getALLProduct()
}

class ProductInteractor(private val presenter: ProductPresentLogic,
                        private val worker: ProductAPI):
    ProductBusinessLogic {

    override fun getALLProduct() {
        presenter.presentLoading()
        worker.getProducts(
            success = {
                presenter.presentAllProduct(it)
                presenter.presentHideLoading()
            },
            failure = {
                presenter.presentGetProductError(it?.message ?: "Cannot get all product")
                presenter.presentHideLoading()
            }
        )
    }

}