package sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PQBufferedFile implements Comparable<PQBufferedFile> {
	private static final int LINES = 2;
	private File _file = null;
	BufferedReader reader = null;
	ArrayList<String> lines = new ArrayList<String>();
	int currentpointer = 0;
	public PQBufferedFile(File file) throws FileNotFoundException
	{
		 reader = new BufferedReader(new FileReader(file));
		_file = file;
		reload();
	}
	
	public int compareTo(PQBufferedFile other)
	{
		return peek().compareTo(other.peek());
	}
	
	public String peek()
	{
		if (currentpointer >= LINES)
			reload();
		return lines.get(currentpointer);
	}
	public String poll()
	{
		if (currentpointer >= LINES)
			reload();
		return lines.get(currentpointer++);
	}
	public boolean isEmpty()
	{
		return lines.size() == 0;
	}
	
	private void reload()
	{
		lines.clear();
		currentpointer = 0;
		for (int i = 0; i < LINES; ++i)
		{
			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (line != null)
				lines.add(i,line);
		}
			
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
