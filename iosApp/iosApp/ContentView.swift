import SwiftUI
import shared

class ViewModel : ObservableObject{
    @Published var products: [Product] = []
    @Published var isLoading: Bool = false
    @Published var isError: Bool = false

    init() {
        getProducts()
    }

    func getProducts() -> Void {
        isLoading = true
        
        ProductAPI().getProducts (
            success: {  [weak self] (result) in
                self?.products = result
                self?.isError = false
                self?.isLoading = false
            },
            failure: { [weak self] (error) in
                self?.isError = true
                self?.isLoading = false
             }
        )
    }
}

struct ContentView: View {

    @ObservedObject
    var viewModel = ViewModel()

	var body: some View {
        if viewModel.isLoading {
            ProgressView()
        }
        
        if viewModel.products.count > 0 {
            List(viewModel.products, id: \.id) { product in
                VStack(alignment: .leading) {
                    if #available(iOS 15.0, *) {
                        AsyncImage(
                            url: URL(string: product.image),
                            content: { image in
                                image.resizable()
                                    .aspectRatio(contentMode: .fit)
                                    .frame(maxWidth: 300, maxHeight: 100)
                            },
                            placeholder: {
                            ProgressView()
                        })
                
                    }
                    Text(product.title)
                        .font(.headline)
                        .foregroundColor(.secondary)
                    Text("$ \(String(format: "%.2f", product.price))")
                        .font(.title)
                        .fontWeight(.black)
                        .foregroundColor(.primary)
                        .lineLimit(3)
                }.padding(20)
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
