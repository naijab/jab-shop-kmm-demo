import com.naijab.demo.jabshop.product.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductInteractorTest {

    private val worker = MockProductAPI()
    private val presenter = MockPresenter()
    private val interactor: ProductBusinessLogic = ProductInteractor(presenter, worker)

    @Test
    fun `Test Get All Product - When Success`() {
        worker.isFail = false
        interactor.getALLProduct()
        assertEquals(true, presenter.isPresentLoadingCalled)
        assertEquals(true, presenter.isPresentAllProductCalled)
        assertEquals(true, presenter.isPresentHideLoadingCalled)
    }
    @Test
    fun `Test Get All Product - When Fail`() {
        worker.isFail = true
        interactor.getALLProduct()
        assertEquals(true, presenter.isPresentLoadingCalled)
        assertEquals(true, presenter.isPresentGetProductErrorCalled)
        assertEquals(true, presenter.isPresentHideLoadingCalled)
    }

}

class MockPresenter: ProductPresentLogic {
    var isPresentAllProductCalled = false
    var isPresentGetProductErrorCalled = false
    var isPresentLoadingCalled = false
    var isPresentHideLoadingCalled = false

    override fun presentAllProduct(products: List<Product>) {
        isPresentAllProductCalled = true
    }

    override fun presentGetProductError(message: String) {
        isPresentGetProductErrorCalled = true
    }

    override fun presentLoading() {
        isPresentLoadingCalled = true
    }

    override fun presentHideLoading() {
        isPresentHideLoadingCalled = true
    }
}

class MockProductAPI: ProductAPI() {
    var isFail = false
    val mockData = listOf(Product(1, "Test Product", "Test Desc", "food", 299.0, "", Rating(5.0, 10)))

    override fun getProducts(success: (List<Product>) -> Unit, failure: (Throwable?) -> Unit) {
        if (isFail) {
            failure(Throwable("Cannot get all product"))
        }
        success(mockData)
    }
}