package test.java;

public class WordCount {
    public static void main(String[] args) {
        String names = "My Name is Balaji Kumar";
        String[] wordArray = names.split(" ");
        for (int i = 0; i < wordArray.length; i++) {
            StringBuilder temp = new StringBuilder();

            if ((i+1) % 2 != 0) {
                char[] chrArray = wordArray[i].toCharArray();
                int j = chrArray.length-1;
                while (j>=0) {
                    temp.append(chrArray[j]);
                    j--;
                }
                wordArray[i]= String.valueOf(temp);
            }
        }
        StringBuilder out= new StringBuilder();
        for(String s:wordArray){
            out.append(s);
            out.append(" ");
        }
        System.out.println(out);

    }

}
