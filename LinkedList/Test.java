
public class Test {

    public static void main(String[] args) {
   //    String[] array = {"c","a"};
        SortedLinkedList sortedWords = new SortedLinkedList();
        
        
        //sortedWords.removeAt(3);
       sortedWords.add("1");
      
       sortedWords.add("2");
       sortedWords.display();
  //     sortedWords.add("3");
  //     sortedWords.add("4");
   //     sortedWords.removeAt(9);
        sortedWords.removeAt(0);
        sortedWords.removeAt(0);
   //     sortedWords.removeAt(-1);
     //   sortedWords.size();
        sortedWords.display();
        sortedWords.add("3");
        sortedWords.display();
   //     System.out.println(sortedWords.size());
        
    }

}
