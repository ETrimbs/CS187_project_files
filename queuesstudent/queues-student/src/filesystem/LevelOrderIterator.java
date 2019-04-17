package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import sorting.MergeSorter;
import structures.Queue;

/**
 * An iterator to perform a level order traversal of part of a filesystem. A
 * level-order traversal is equivalent to a breadth- first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
	private File root;

	private File[] heirarchy;
	private Queue<File> files;
	private int count;
	private File next;
	private MergeSorter<File> ms;

	public static void main(String[] args) {
		// testing
		try {
			File tempDir;

			File tempFile = File.createTempFile("queues", "tmp");
			LevelOrderIterator singleIterator = new LevelOrderIterator(tempFile);

			tempDir = Files.createTempDirectory("queues").toFile();
			for (String fileName : new String[] { "a.txt", "z.exe" }) {
				File f = new File(tempDir, fileName);
				f.createNewFile();
			}

			File emptyDir = new File(tempDir, "empty");
			emptyDir.mkdir();

			File subDir = new File(tempDir, "subdir");
			subDir.mkdir();
			File subDirFile = new File(subDir, "yahoo");
			subDirFile.createNewFile();
			File leafDir = new File(subDir, "subsubdir");
			leafDir.mkdir();
			for (String fileName : new String[] { "foo.txt", "bar.exe" }) {
				File f = new File(leafDir, fileName);
				f.createNewFile();
			}

			LevelOrderIterator nestedDirIterator = new LevelOrderIterator(tempDir);

			System.out.println(nestedDirIterator.next());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * 
	 * @param rootNode
	 * @throws FileNotFoundException
	 *             if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		if (!rootNode.exists())
			throw new FileNotFoundException();

		ms = new MergeSorter<File>();
		root = rootNode;
		// files = new Queue<File>();

		// for (File a : root.listFiles()) {
		// files.enqueue(a);
		// }
		// files = ms.mergeSort(files);

		populateHeirarchyQueue();
		count = 0;
		next = heirarchy[0];
	}

	private void populateHeirarchy() {// index -1 is root, 0 is root's children.

		ArrayList<File[]> fileTree = new ArrayList<File[]>();
		File holder;
		File[] tem, nextL;
		tem = new File[1];
		tem[0] = root;
		fileTree.add(tem);

		boolean nextLayer = true;
		int count = 1;

		for (int i = 0; nextLayer; i++) {
			nextLayer = false;
			nextL = new File[0];

			for (int j = 0; j < fileTree.get(i).length; j++) { // fileTree.get(i) != null &&
				File[] temp = fileTree.get(i);
				holder = temp[j];
				tem = holder.listFiles();
				if (tem != null && tem.length > 0) {
					nextLayer = true;
					nextL = combine(nextL, tem);
				}
			}
			if (nextL.length > 0) {
				Arrays.sort(nextL);
				fileTree.add(nextL);
				for (File b : nextL) {
					count++;
				}
			}
		}

		heirarchy = new File[count];
		count = 0;
		for (File[] a : fileTree) {
			for (File b : a) {
				heirarchy[count] = b;
				count++;
			}
		}
	}

	private void populateHeirarchyQueue() {// index -1 is root, 0 is root's children.

		ArrayList<Queue<File>> fileTree = new ArrayList<Queue<File>>();
		File holder;
		File[] tem;
		Queue<File> nextL;
		tem = new File[1];
		tem[0] = root;
		fileTree.add(new Queue<File>(tem));

		boolean nextLayer = true;
		int count = 1;

		for (int i = 0; nextLayer; i++) {
			nextLayer = false;
			nextL = new Queue<File>(0);

			for (int j = 0; j < fileTree.get(i).size(); j++) { // fileTree.get(i) != null &&
				Queue<File> temp = fileTree.get(i);
				holder = temp.queue[j];
				tem = holder.listFiles();
				if (tem != null && tem.length > 0) {
					nextLayer = true;
					nextL = nextL.combine(new Queue<File>(tem));
				}
			}
			if (nextL.size() > 0) {
				ms.mergeSort(nextL);
				fileTree.add(nextL);
				for (File b : nextL.queue) {
					count++;
				}
			}
		}
				
		heirarchy = new File[count];
		count = 0;
		for (Queue<File> a : fileTree) {
			for (File b : a.queue) {
				heirarchy[count] = b;
				count++;
			}
		}
	}

	public static File[] combine(File[] tis, File[] o) {
		File[] out = new File[tis.length + o.length];
		for (int i = 0; i < tis.length; i++) {
			out[i] = tis[i];
		}
		for (int i = 0; i < o.length; i++) {
			out[i + tis.length] = o[i];
		}

		return out;
	}
	// private static Queue<File> toQueue(File[] in){
	// Queue<File> out = new Queue<File>(in.length);
	// for(File a: in) {
	// out.enqueue(a);
	// }
	// return out;
	// }

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public File next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException();

		File out = next;
		count++;
		if (count >= heirarchy.length)
			next = null;
		else
			next = heirarchy[count];

		return out;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
