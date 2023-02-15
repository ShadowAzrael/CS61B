import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        List<String> nonBlank = new ArrayList<String>();
        for(String s: asciis) {
            if (!s.trim().isEmpty()) {
                nonBlank.add(s);
            }
        }
        String[] arr = (String[]) nonBlank.toArray( new String[nonBlank.size()] );

        int W = findMax(asciis);
        int N = asciis.length;
        String[] temp = new String[asciis.length];

        for(int d = W - 1; d >= 0; d--){
            int[] count  = new int[256];
            for(int i = 0; i < N; i++){
                if(d > arr[i].length() - 1){
                    count[1+1]++;
                }else{
                    count[arr[i].charAt(d) + 1]++;
                }
            }
            for(int k = 1; k < 256; k++){
                count[k] = count[k] + count[k - 1];
            }
            for(int i = 0; i < N; i++){
                if(d > arr[i].length() - 1){
                    temp[count[1]++] = arr[i];
                }else{
                    temp[count[arr[i].charAt(d)]++] = arr[i];
                }
            }
            for(int i = 0; i < N; i++){
                arr[i] = temp[i];
            }
        }
        return temp;
    }

    private static int findMax(String[] a){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < a.length; i++){
            max = max > a[i].length() ? max : a[i].length();
        }
        return max;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
