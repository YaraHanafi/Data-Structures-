package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie. 
 * 
 * @author Yara Hanafi, RUID 194005395
 *
 */
public class Trie {
	
	// prevent instantiation
	private Trie() { }

	
	/**
	 * Builds a trie by inserting all words in the input array, one at a time,
	 * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
	 * The words in the input array are all lower case.
	 * 
	 * @param allWords Input array of words (lowercase) to be inserted.
	 * @return Root of trie with all words inserted from the input array
	 */
	public static TrieNode buildTrie(String[] allWords) {
		/** COMPLETE THIS METHOD **/
		TrieNode root = new TrieNode(null,null,null);
		int  arraysize = allWords.length;
		for(int i=0; i<arraysize; i++){
			String currentword = allWords[i];
			if (root.firstChild==null){
			Indexes wordind = new Indexes(i,(short)0,(short)(currentword.length()-1));
			TrieNode word = new TrieNode(wordind,null,null);
			root.firstChild = word;
			} else {
				addnode(currentword,root,i,allWords);
			}
				 
		}
		return root;
		
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
	}

	private static void addnode(String currentword,TrieNode root, int i, String [] allWords) {
		TrieNode ptr = root.firstChild;
	    int arrayindex = ptr.substr.wordIndex;
		boolean n = false;
		TrieNode ptrr = ptr;
		while(ptrr != null){
			if (currentword.charAt((int)(ptrr.substr.startIndex)) == allWords[(int)(ptrr.substr.wordIndex)].charAt((int)(ptrr.substr.startIndex))){
				ptr = ptrr;
				n=true;
				break;
			} 
			ptr = ptrr;
			ptrr = ptr.sibling;
		}
		if(n==false){
			Indexes wordind = new Indexes(i,(ptr.substr.startIndex),(short)(currentword.length()-1));
			TrieNode word = new TrieNode(wordind,null,null);
			ptr.sibling = word;
		}

		if(n==true){
			if(ptr.firstChild==null){
			int size = (allWords[(int)(ptr.substr.wordIndex)].length() <= currentword.length()) ? allWords[(int)(ptr.substr.wordIndex)].length() : currentword.length();
			int J =0;
			for(int j=0; j<size; j++){
				if (currentword.charAt(j) != allWords[(int)(ptr.substr.wordIndex)].charAt(j)){
					break; }
					J = j;
				}	
				ptr.substr.endIndex = (short)J;
				Indexes ptr2in = new Indexes((int)(ptr.substr.wordIndex),(short)(J+1), (short)(allWords[(int)(ptr.substr.wordIndex)].length()-1) );
				TrieNode ptr2 = new TrieNode(ptr2in, null, null);
				ptr.firstChild = ptr2;
				Indexes wordind = new Indexes(i,(short)(J+1),(short)(currentword.length()-1));
				TrieNode word = new TrieNode(wordind,null,null);
				ptr2.sibling = word;
			} else if(ptr.firstChild.sibling==null){
			addnode(currentword,ptr,i,allWords);
			} else {
				for(int j=0; j<=((int)(ptr.substr.endIndex)); j++){
					if (currentword.charAt(j) != allWords[(int)(ptr.substr.wordIndex)].charAt(j)){
						editnode(currentword,ptr,i,allWords);
					}
				}
				addnode(currentword,ptr,i,allWords);
			}
		}	
	}

	private static void editnode(String currentword,TrieNode root, int i, String [] allWords) {
		int K =0;
		for(int j=0; j<((int)(root.substr.endIndex)); j++){
			if (currentword.charAt(j) != allWords[(int)(root.substr.wordIndex)].charAt(j)){
				break; 
			}
			K = j;
		}
		root.substr.endIndex = (short)K;
		TrieNode ptr=root.firstChild;
		ptr.substr.startIndex = (short)(K+1);
		ptr = ptr.sibling;
		root.firstChild.sibling = null;
		while(ptr!=null){
			String word = allWords[ptr.substr.wordIndex];
			int l = ptr.substr.wordIndex;
			addnode(word,root,l,allWords);
			ptr = ptr.sibling;
		}
	}
	
	/**
	 * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 
	 * trie whose words start with this prefix. 
	 * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
	 * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 
	 * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 
	 * and for prefix "bell", completion would be the leaf node that holds "bell". 
	 * (The last example shows that an input prefix can be an entire word.) 
	 * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
	 * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
	 *
	 * @param root Root of Trie that stores all words to search on for completion lists
	 * @param allWords Array of words that have been inserted into the trie
	 * @param prefix Prefix to be completed with words in trie
	 * @return List of all leaf nodes in trie that hold words that start with the prefix, 
	 * 			order of leaf nodes does not matter.
	 *         If there is no word in the tree that has this prefix, null is returned.
	 */
	public static ArrayList<TrieNode> completionList(TrieNode root,
										String[] allWords, String prefix) {
		/** COMPLETE THIS METHOD **/
		TrieNode ptr = root.firstChild;
		ArrayList<TrieNode> list = new ArrayList<TrieNode>();
		boolean find = false;
		while(ptr!=null){
			if (prefix.charAt(0) == allWords[(ptr.substr.wordIndex)].charAt(0)){
				find = true;
				break;
			}
			ptr = ptr.sibling;
		}

		if(find == false){
			list = null;
			return list;
		}

		if(ptr.firstChild == null){
			list.add(ptr);
			return list;
		} else {
			find(ptr,allWords,prefix,list);
			return list;
		}
		
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
	}

	private static void find(TrieNode root, String[] allWords, String prefix, ArrayList<TrieNode> list){
		if(root==null){
			return;
		}
		boolean N = true;
		boolean L= true;
		L = ((int)(root.substr.endIndex) >= (prefix.length()-1)) ? true : false;
		int size = ((int)(root.substr.endIndex) >= (prefix.length()-1)) ?  (prefix.length()-1) : (int)(root.substr.endIndex) ;
		for(int i=0; i<=size; i++){
			if(prefix.charAt(i) != allWords[(root.substr.wordIndex)].charAt(i)){
				N = false;
				break;
			}
		}

		if(L==false && N==true){
			find(root.firstChild,allWords,prefix,list);
		}
		if(N == false){
			find(root.sibling,allWords,prefix,list);
		}

		if(N==true && L==true){
			TrieNode ptr=root;
			TrieNode T=ptr.firstChild;
			if(T == null){
			list.add(ptr);
			} else {
				while(T != null){
				find(T,allWords,prefix,list);
				T= T.sibling;
			}
			}
		}
	}
	
	public static void print(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}
	
	private static void print(TrieNode root, int indent, String[] words) {
		if (root == null) {
			return;
		}
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		
		if (root.substr != null) {
			String pre = words[root.substr.wordIndex]
							.substring(0, root.substr.endIndex+1);
			System.out.println("      " + pre);
		}
		
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		if (root.substr == null) {
			System.out.println("root");
		} else {
			System.out.println(root.substr);
		}
		
		for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
			for (int i=0; i < indent-1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent+1, words);
		}
	}
 }
