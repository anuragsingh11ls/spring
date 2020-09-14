import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class CSVFileSplitter {

	/**
	 * @param args
	 */
	private static int startIndex = 0;

	public static void main(String[] args) {
		Map<String, Object> csvContent = readCSVFile("C:/Users/singh510/Desktop/File/DMF_WBAPORTAL_LOCK_1100_201803201655.csv");
		String header = (String)csvContent.get("Header");
		List<String> initialList = (List<String>)csvContent.get("RowRecord");
		List<List<String>> subLists = splitCSVRecords(initialList);
		Iterator<List<String>> itr = subLists.iterator();
		String outputFileLocation = "C:/Users/singh510/Desktop/file/";
		while(itr.hasNext()){
			writeCSVFile(itr.next(), outputFileLocation, header);
		}
	}

	private static int writeCSVFile(List<String> rowRecords, String outputFileLocation, String header){
		int counter = 0;
		String checkFileName = "DMF_WBAPORTAL_" + startIndex + ".csv";
		String csvFileLocation = outputFileLocation + checkFileName;
		FileWriter writer = null;
		try {
			writer = new FileWriter(csvFileLocation);

			StringBuilder sb = new StringBuilder();
			sb.append(header);
			while(counter < rowRecords.size())
			{
				sb.append(rowRecords.get(counter));
				sb.append("\r\n");
				counter++;
			}

			writer.append(sb.toString());
			System.out.println("File Generated!!!");
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}finally{
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		startIndex++;
		return 1;
	}

	private static List<List<String>> splitCSVRecords(List<String> originalList){
		int partitionSize = 100;
		List<List<String>> partitions = new LinkedList<List<String>>();
		for (int i = 0; i < originalList.size(); i += partitionSize) {
			partitions.add(originalList.subList(i,
					Math.min(i + partitionSize, originalList.size())));
		}
		return partitions;
	}

	private static Map<String, Object> readCSVFile(String csvFileCompletePath){
		BufferedReader br = null;
		String line = "";
		int counter = 1;
		StringBuffer header = new StringBuffer();
		List<String> rowRecords = new ArrayList<String>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			br = new BufferedReader(new FileReader(csvFileCompletePath));
			while ((line = br.readLine()) != null) {
				if(counter++<=2)
				{	
					header.append(line);
					header.append("\r\n");
					resultMap.put("Header", header.toString());
					continue;
				}	
				if(!line.startsWith("99")){
					rowRecords.add(line);
					continue;
				}
				resultMap.put("RowRecord", rowRecords);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultMap;
	}
}
