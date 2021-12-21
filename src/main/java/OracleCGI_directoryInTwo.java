public class OracleCGI_directoryInTwo {

    public static String main(String[] args) {
        String[] arr = new String[]{"d", "e", "f", "g", "a", "b ", "c"};

        int length = arr.length;
        int start = 0;
        int end = length;
        int mid = 0;

        while (start < end) {
            mid = (start + end) / 2;
            if (arr[start].compareTo(arr[mid]) > 0) {
                end = mid;
            } else if (arr[mid].compareTo(arr[end]) > 0) {
                start = mid;
            }
        }

        return arr[mid];
    }
}
