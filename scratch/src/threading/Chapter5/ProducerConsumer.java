package threading.Chapter5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {


	public static class FileCrawler implements Runnable {
		private final File root;
		private final BlockingQueue<File> queue;
		private final FileFilter fileFilter;
		public FileCrawler(File root, FileFilter fileFilter, BlockingQueue<File> queue) {
			this.root = root;
			this.queue = queue;
			this.fileFilter = fileFilter;
		}
		public void run() {
			crawl(root);
		}
		
		private void crawl(File rootFile) {
			File[] files = rootFile.listFiles(fileFilter);
			if (files != null) {
				 for (File file : files) {
					 if (file.isDirectory()) {
						 crawl(file);
						 queue.add(file);
					 }
				 }
			}
		}
	}
	
	public static class Indexer implements Runnable {
		
		private final BlockingQueue<File> queue;
		public Indexer(BlockingQueue<File> queue) {
			this.queue = queue;
		}
		public void run() {
			while (true) {
				try {
					index(queue.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		private void index(File toIndex) {
			
		}
	}
	
	public static void crawlerTest(File root)
	{
		// create a blocking queue.
		BlockingQueue<File> queue = new LinkedBlockingQueue<File>();
		FileFilter fileFilter = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return true;
			}};
		 Thread crawler = new Thread(new FileCrawler(root, fileFilter, queue));
		 Thread indexer = new Thread(new Indexer(queue));
		 crawler.start();
		 indexer.start();
		 try {
			 indexer.join();
		 } catch (Exception e)
		 {
			 
		 }
	}
	
}
