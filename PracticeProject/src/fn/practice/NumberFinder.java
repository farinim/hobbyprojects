/*
bin>javac E:\Work\Application\Miscellenous\PracticeProject\src\fn\practice\NumberFinder.java
bin>java -cp "E:\Work\Application\Miscellenous\PracticeProject\src" fn.practice.NumberFinder
test fn.practice.NumberFinder
*/
package fn.practice;


import java.util.*;
public class NumberFinder{
	
	public static void main(String[] args){
		System.out.println("test fn.practice.NumberFinder");
		
		String inputStr ="1 3 8 9 10 3 6 4 12 18 11 7";
		int K = 4;
		String[] inputSeqn = inputStr.split(" ");
		int csize = inputSeqn.length/K;
		
		System.out.println("csize=" +csize);
		List<Integer[]> chunks = new ArrayList<>(4);
		int j=0;
		for(int i=0; j<inputSeqn.length; i=i+csize){
			j+=csize;
			System.out.println("i=" + i +", j=" +j);
			chunks.add(getChunk(inputSeqn, i,j));
		}
		chunks.forEach(chunk -> System.out.println(Arrays.asList(chunk)));
		
		System.out.println("Biggest number formed from the chunks : " + biggestNumber(chunks,1));
		System.out.println("Second biggest number formed from the chunks : " + biggestNumber(chunks, 2));
	}
	
	private static int biggestNumber(List<Integer[]> chunks, int k){
		
		/*chunks.forEach(chunk -> {
									//System.out.println(Arrays.asList(chunk));
									Collections.sort(Arrays.asList(chunk), Collections.reverseOrder());
									//System.out.println(Arrays.asList(chunk));
								});*/
		String outNumberStr = "";					
		for(Integer[] chunk: chunks){
			
			Collections.sort(Arrays.asList(chunk), Collections.reverseOrder());
			outNumberStr += chunk[k-1];
		}
		return Integer.parseInt(outNumberStr);
	}
	private static Integer []  getChunk(String[] inputSeqn, int begin, int end){
		int index = 0;
		
		Integer [] chunk = new Integer[end - begin];
		for(int i=begin; i < end;i++){
			chunk[index++] = Integer.parseInt(inputSeqn[i]);
		}
		//System.out.println(Arrays.asList(chunk));
		return chunk;
	}
}