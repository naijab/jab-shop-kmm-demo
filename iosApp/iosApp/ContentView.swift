import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject
    var productController = ProductController()
    
    init() {
        productController.interactor.getALLProduct()
    }

	var body: some View {
        if productController.isLoading {
            ProgressView()
        }
        
        if let errorMessage = productController.errorMessage {
            Text("Error: \(errorMessage)").foregroundColor(.red)
            Button("Try Again") {
                productController.interactor.getALLProduct()
            }.padding()
        }
        
        if productController.products.count > 0 {
            List(productController.products, id: \.id) { product in
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
