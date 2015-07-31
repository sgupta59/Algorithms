package threading.Chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PreLoader {

	static class ProductInfo {
		String info = "abc";
	}
	
	ProductInfo loadProductInfo() { 
		return new ProductInfo();
	}
	
	private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {

		@Override
		public ProductInfo call() throws Exception {
			// TODO Auto-generated method stub
			Thread.sleep(2000);
			return loadProductInfo();
		}
	});
	
	private final Thread thread = new Thread(future);
	
	public void start() { thread.start();}
	
	public ProductInfo get() throws InterruptedException, ExecutionException {
		ProductInfo info = future.get();
		return info;
	}
	
	public static void main(String[] args) {
		PreLoader loader = new PreLoader();
		loader.start();
		try {
			loader.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
	}
}
