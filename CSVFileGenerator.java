import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class CSVFileGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		generateCSVFile("1100", 1000, "LOCK");

	}

	public static void generateCSVFile(String batchNumber, int dataRecordCount, String fileType){

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		final char DEFAULT_SEPARATOR = ',';

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String currentTimeStamp = sdf.format(timestamp);

		String checkFileName = "DMF_WBAPORTAL_" + fileType + "_" +batchNumber+"_"+currentTimeStamp+".csv";
		String wsoID[] = new String[]{"BBOO","LTEL","XS42","XENO","REGC"};
		String serviceGroupID[] = new String[]{"EAP/","SG-"};
		String xdfID[] = new String[]{"CFX","CFB","CFW","REF","ODF"};


		String csvFileLocation = "C:/Users/singh510/Desktop/file/" + checkFileName;
		FileWriter writer = null;
		try {
			writer = new FileWriter(csvFileLocation);

			StringBuilder sb = new StringBuilder();

			//First 00 Row
			sb.append("00");
			sb.append(DEFAULT_SEPARATOR);
			sb.append(batchNumber);
			sb.append(DEFAULT_SEPARATOR);
			sb.append(currentTimeStamp);
			sb.append("\r\n");

			//Second 01 Row
			sb.append("01");
			sb.append(DEFAULT_SEPARATOR);
			if("LOCK".equals(fileType))
				sb.append("WSO-ID,SERVICE_GROUP_ID,XDF_ACCESS_SERVICE_ID1,XDF_ACCESS_SERVICE_ID2");
			else
				sb.append("WSO-ID,SERVICE_GROUP_ID,XDF_ACCESS_SERVICE_ID1,XDF_ACCESS_SERVICE_ID2,ACTION");
			sb.append("\r\n");

			//Data 11 Row
			int dataRowCounter = 0;
			while(dataRowCounter < dataRecordCount)
			{
				sb.append("11");
				sb.append(DEFAULT_SEPARATOR);
				sb.append(wsoID[dataRowCounter%wsoID.length]);
				sb.append(DEFAULT_SEPARATOR);
				sb.append(serviceGroupID[dataRowCounter%serviceGroupID.length] + String.format("%07d", dataRowCounter+1));
				sb.append(DEFAULT_SEPARATOR);
				sb.append(xdfID[dataRowCounter%xdfID.length] + String.format("%08d", dataRowCounter+1));
				sb.append(DEFAULT_SEPARATOR);
				sb.append(xdfID[dataRowCounter%xdfID.length] + "1" + String.format("%07d", dataRowCounter+1));
				if("UNLOCK".equals(fileType))
				{
					sb.append(DEFAULT_SEPARATOR);
					if(dataRowCounter%3 == 0)
						sb.append("UNLOCK");
					else
						sb.append("LOCK");
				}
				sb.append("\r\n");
				dataRowCounter++;
			}

			//Trailer 99 Row
			sb.append("99");
			sb.append(DEFAULT_SEPARATOR);
			sb.append(dataRecordCount);

			writer.append(sb.toString());
			System.out.println("File Generated!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
