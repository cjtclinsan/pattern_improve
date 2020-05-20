package suanfa;

import java.util.HashMap;
import java.util.Map;

/**
 * @author taosh
 * @create 2020-05-20 9:06
 */
public class FindTheLongestStr {
    private static final String VOWELS = "aeiou";

    public static int findTheLongestSubstring(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        int state = 0;   //00000
        int size = s.length();
        int maxSize = 0;
        map.putIfAbsent(0, -1);

        for ( int i = 0; i < size; i++ ){
            for( int k = 0; k < VOWELS.length(); k++ ){
                if( s.charAt(i) == VOWELS.charAt(k) ){
                    state ^= 1 << k;
                    break;
                }
            }

            if( map.containsKey(state) ){
                maxSize = Math.max(maxSize, i - map.get(state));
            }else {
                map.putIfAbsent(state, i);
            }
        }

        return maxSize;
    }

    public static void main(String[] args) {
        System.out.println(findTheLongestSubstring("eetretiuioeuoro"));
    }
}
