import hsa.*;
public class SearchAndSort1 {
    public static void main (String[] args) {
	printCourseInfo("*");
    } 
    
    public static void printNumbers(int[] numbers){
	for(int i = 0; i < numbers.length; i++){
	    Stdout.println(numbers[i]);
	}    
    }   
	
    public static void bubbleSort(int[] items){
	boolean swapped = true;
	for(int i = items.length -1; i > 0 && swapped; i--){
	    swapped = false;
	    for(int j = 0; j < i; j++){
		if(items[j] > items[j + 1]){
		    swapNumbers(items, j, j+1);
		    swapped = true;
		}
	    }
	}

    }
    
    public static void swapNumbers(int[] numbers, int i, int j){
	int sub = numbers[i];
	numbers[i] = numbers[j];
	numbers[j] = sub;
    } 
    
    public static int linearSearch(String[] list, String key){
	int i = 0;
	while(i < list.length && !list[i].equals(key)){
	    i++;
	}
	return i == list.length ? -1 : i;
    }
    
    public static int indexOfLargest(int[] list, int end){
	if (list != null) {
	    int largest = 0;
	
	    for (int i = 1; i <= end; i++) {
		if (list[i] > list[largest]) {
		    largest = i;
		}
	    }
	    return largest;
	}
	else {
	    return -1;
	}
    }
    
    public static void selectionSort(int [] integers){
	for (int i = integers.length -1; i > 0; i-- ){
	    swapNumbers(integers, indexOfLargest(integers, i), i);
	}
	printNumbers(integers);
    }
    
    public static void shiftElements(int[] list, int start, int end){
	for(int i = end; i > start; i--){
	    list[i] = list[i - 1];
	}
    }

    public static void insertionSort(int[] list){
	for(int i = 1; i < list.length; i++){
	    boolean largerFound = false;
	    int j = 0;
	    for(j = 0; j < 1 && !largerFound; j++){
		largerFound = list[j] > list[i];
	    }
	    if(largerFound){
		j--;
		int temp = list[i];
		shiftElements(list, j, i);
		list[j] = temp;
	    }
	}
    }
    
    public static String[] mergeLists(String[] list1, String[] list2){
	String[] resultList = new String[list1.length + list2.length];
	int p = 0;
	int q = 0;        
	int r = 0;
	
	while (p < list1.length || q < list2.length) {
	    if ( p == list1.length) {
		resultList[r] = list2[q];
		q++;                
	    }
	    else if (q == list2.length) {
		resultList[r] = list1[p];
		p++;
	    }
	    else if (list1[p].compareTo(list2[q]) <= 0){
		resultList[r] = list1[p];
		p++; 
	    }
	    else {
		resultList[r] = list2[q];
		q++;
	    } 
	    r++;
	} 
	
	return resultList;
    }
    
    public static String[][] studentsAndCourses = {{"733822811", "CSC148,MAT223,PHY150,CHM292"},
					 {"432011922", "MUS305,HIS378,ENG140"},
					 {"732392194", "ENG140,PSY100,CHM108"},
					 {"531118220", "CSC148,PHY150"}};
    public static String[][] courses = {{"CSC148", "Computer Science"},
					{"MAT223", "Linear Algebra"},
					{"PHY150", "Theoretical Physics"},
					{"MUS305", "Music Performance III"},
					{"ENG140", "English Literature I"},
					{"HIS378", "19th Century History"},
					{"PSY100", "Introduction to Psychology"}};
					 
    public static void printCourseInfo(String studentNumber){ 
	boolean asterisk = studentNumber.equals("*");
	for(int i = 0; i < studentsAndCourses.length; i++){
	    if(studentsAndCourses[i][0].indexOf(studentNumber) == 0 || asterisk){
		System.out.println(studentsAndCourses[i][0]);
		String code = studentsAndCourses[i][1].substring(0, 6);
		for(int j = 0; j < courses.length; j++){
		    if (code.equals(courses[j][0])){
			System.out.println('\t' + code + '\t' + courses[j][1]);
			if(studentsAndCourses[i][1].length() > 6){
			    studentsAndCourses[i][1] = studentsAndCourses[i][1].substring(7);
			    code = studentsAndCourses[i][1].substring(0, 6);
			    j = 0;
			}
			else{
			    j = courses.length;
			}
		    }
		    else if (j == courses.length - 1){
			System.out.println('\t' + code + '\t' + "N/A");
		    }
		}
	    }
	}  
    }
} 
