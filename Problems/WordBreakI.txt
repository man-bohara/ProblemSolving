package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreakI {
	
	public static void main(String[] args) {
		
		String s = "leetcode";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("leet");
		wordDict.add("code");
		Map<String, Boolean> map = new HashMap<>();
		System.out.println(wordBreak(s, wordDict, map));
	}
	
	public static boolean wordBreak(String s, List<String> wordDict, Map<String, Boolean> map) {
		
		if(wordDict.contains(s))
			return true;
		
		if(map.containsKey(s))
			return map.get(s);
		
		for(int i=1; i<s.length(); i++) {
			String left = s.substring(0, i);
			if(wordDict.contains(left) && wordBreak(s.substring(i), wordDict, map)) {
				map.put(s, true);
				return true;
			}
		}
        
		map.put(s, true);
		return false;        
    }

}
