package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TestExternalMergeSort {
	
	File _inp;
	File _out;
	public TestExternalMergeSort(File inp, File output)
	{
		this._inp = inp;
		_out = output;
	}
	public void execute() throws Exception
	{
		// step# 1 take the input file, read a set lines from the file, sort the lines and write them to a temporary file.
		List<File> files = sortAndSaveTemporaryFiles();
		sortAndSaveFile(files);
	}
	
	private void sortAndSaveFile(List<File> files) throws FileNotFoundException
	{
		// create a priority queue
		PriorityQueue<PQNode> pq = new PriorityQueue<PQNode>();
		for (File file : files)
			pq.add(new PQNode(file));
		while (pq.isEmpty() == false)
		{
			PQNode node = pq.poll();
			String line = node.poll();
			if (node.isEmpty() == true)
				continue;
			pq.offer(node);
		}
	}
	private static class PQNode implements Comparable<PQNode> {
		File file;
		int pointer;
		int loaded;
		BufferedReader bfr;
		ArrayList<String> lines = new ArrayList<String>();
		private PQNode(File file) throws FileNotFoundException
		{
			pointer = 0;
			loaded = 5;
			this.file = file;
			bfr = new BufferedReader(new FileReader(file));
			
		}

		@Override
		public int compareTo(PQNode o) 
		{
			// TODO Auto-generated method stub
			String s1 = peek();
			if (s1 == null) 
				return -1;
			String s2 = o.peek();
			if (s1 == null)
				return 1;
			return peek().compareTo(o.peek());
		}
		
		public String peek()
		{
			if (pointer >= lines.size()  )
				reload();
			if (isEmpty())
				return null;
			return getString();
		}
		
		public String poll()
		{
			String str = getString();
			++pointer;
			return str;
		}
		
		private String getString()
		{
			if (pointer >= lines.size())
				reload();
			if (isEmpty())
				return null;
			String line = lines.get(pointer);
			return line;
		}
		public boolean isEmpty()
		{
			return lines.size() == 0;
		}
		public void reload()  
		{
			pointer = 0;
			String line = null;
			lines.clear();
			try {
				int count = 4;
			 
				while ((line = bfr.readLine()) != null && count >= 0) {
					lines.add(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		protected void finalize() throws Throwable
		{
			try {
				bfr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file.delete();
			super.finalize();
		}
	}
	private List<File> sortAndSaveTemporaryFiles() throws Exception
	{
		BufferedReader reader = new BufferedReader(new FileReader(_inp));
		int counter = 0;
		String line = null;
		line = reader.readLine();
		ArrayList<File> outputFiles = new ArrayList<File>();
		ArrayList<String> items = new ArrayList<String>();
		while (line != null)
		{
			items.add(line);
			++counter;
			if (counter % 5 == 0)
			{
				// sort and save a temporary file.
				File tmpFile = Files.createTempFile("TO_", Integer.toString(counter)).toFile();
				outputFiles.add(tmpFile);
				tmpFile.deleteOnExit();
				Collections.sort(items,
						new Comparator<String>() {
					public int compare(String s1, String s2)
					{
						return s1.compareTo(s2);
					}
				});
				// create a new filewriter.
				BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile));
				for (String oneline: items)
				{
					writer.write(oneline);
					writer.newLine();
				}
				writer.flush();
				writer.close();
				items.clear();
			}
			line = reader.readLine();
		}
		reader.close();
		return outputFiles;
	}
	public static void main(String[] args)
	{
		// get the input file
		File inputFile = new File(TestExternalMergeSort.class.getResource("/resources/ToSort").getFile());
		// get the output file
		File outputFile = new File(TestExternalMergeSort.class.getResource("/resources/Sorted").getFile());
		TestExternalMergeSort externalSort = new TestExternalMergeSort(inputFile, outputFile);
		try 
		{
			externalSort.execute();
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
