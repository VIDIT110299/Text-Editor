/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 1000; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) 
	{
		this.dict = dict;
	}

	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<String>();
		   insertions(s, retList, wordsOnly);
		   substitution(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);
		   return retList;
	}

	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(wordsOnly == false && s.equals(sb.toString()) == false && currentList.contains(sb.toString()) == false)
					currentList.add(sb.toString());
				else if(!currentList.contains(sb.toString()) && (!wordsOnly||dict.isWord(sb.toString())) && !s.equals(sb.toString()))
					currentList.add(sb.toString());
			}
		}
	}
	
	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		// TODO: Implement this method
		int len = s.length();
		
		for(int i=0; i<=len; i++)
		{
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++)
			{
				String inserted = "";
				
				if(i == 0)
					inserted = (char)charCode + s;
				else if(i == s.length())
					inserted = s + (char)charCode;
				else if(i>0 && i<s.length())
					inserted = s.substring(0, i) + (char)charCode + s.substring(i);
				
				if(wordsOnly == false && currentList.contains(inserted) == false)
					currentList.add(inserted);
				else if(!currentList.contains(inserted) && (!wordsOnly||dict.isWord(inserted)) && !s.equals(inserted))
					currentList.add(inserted);
			}
		}
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		// TODO: Implement this method
		for(int i=0; i<s.length(); i++)
		{
			String removed = "";
			
			if(i == 0)
				removed = s.substring(1);
			else if(i == s.length()-1)
				removed = s.substring(0, s.length()-1);
			else if(i>0 && i<s.length()-1)
				removed = s.substring(0, i) + s.substring(i+1);
			
			if(wordsOnly == false && currentList.contains(removed) == false)
				currentList.add(removed);
			else if(!currentList.contains(removed) && (!wordsOnly||dict.isWord(removed)) && !s.equals(removed))
				currentList.add(removed);
		}
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		// initial variables
		List<String> queue = new LinkedList<String>();     // String to explore
		HashSet<String> visited = new HashSet<String>();   // to avoid exploring the same  
														   // string multiple times
		List<String> retList = new LinkedList<String>();   // words to return
		 
		
		// insert first node
		queue.add(word);
		visited.add(word);
					
		// TODO: Implement the remainder of this method, see assignment for algorithm
		Iterator<String> iterator = queue.iterator();
		
		while(iterator.hasNext() && numSuggestions > 0)
		{
			String curr = queue.remove(0);
				
			for(String n: distanceOne(curr, true))
			{
				if(visited.contains(n) == false && numSuggestions > 0)
				{
					visited.add(n);
					queue.add(n);
					
					if(dict.isWord(n) && numSuggestions > 0)
					{
						retList.add(n);
						numSuggestions -= 1;
					}
				}
			}
		}
		
		return retList;

	}	

   public static void main(String[] args) {
	   /*
	   //String word = "asi";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   DictionaryLoader.loadDictionary(d, "test_cases/dict2.txt");
	   NearbyWords w = new NearbyWords(d);
	   //List<String> l = w.distanceOne(word, true);
	   //System.out.println("One away word Strings for for \""+word+"\" are:");
	   //System.out.println(l+"\n");

	   String word = "dag";
	   List<String> suggest = w.suggestions(word, 4);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
	   */
   }

}
