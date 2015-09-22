import org.apache.uima.UIMAFramework;
import org.apache.uima.collection.CollectionProcessingEngine;
import org.apache.uima.collection.base_cpm.CasProcessor;
import org.apache.uima.collection.metadata.CpeDescription;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.util.XMLInputSource;

import collectionReader.FileSystemCollectionReader;
public class Main {

  /**
   * This method is the main program and entry point of your system for PI3. It runs a Collection
   * Processing Engine (CPE).
   * 
   * @param args
   */
	/**
	* The CPE instance.
	*/
  private static CollectionProcessingEngine mCPE;
  public static void main(String[] args) throws Exception {
    // ### A guideline for implementing this method ###
    // 1. Accept integer n (1, 2, or 3) as a positional argument, specifying the length of n-grams.
	int n = Integer.parseInt(args[0]);
	String inputDirectory = args[1];
	String outputDirectory = args[2];
    // 2. Initialize a CPE by loading your CPE descriptor at 'src/main/resources/cpeDescriptor.xml'.
	CpeDescription cpeDesc = UIMAFramework.getXMLParser().
	      parseCpeDescription(new XMLInputSource("src/main/resources/QACpe.xml"));    
	//instantiate CPE
	mCPE = UIMAFramework.produceCollectionProcessingEngine(cpeDesc);
	FileSystemCollectionReader fsCollectionReader = (FileSystemCollectionReader) mCPE.getCollectionReader();
	fsCollectionReader.setConfigParameterValue("InputDirectory", inputDirectory);
	CasProcessor[] casProcessors = mCPE.getCasProcessors();
	CasProcessor aeCasProcessor = casProcessors[0];
	ConfigurationParameterSettings cpss = aeCasProcessor.getProcessingResourceMetaData().getConfigurationParameterSettings();
	cpss.setParameterValue("N", n);
	CasProcessor ccCasProcessor = casProcessors[0];
	cpss = ccCasProcessor.getProcessingResourceMetaData().getConfigurationParameterSettings();
	cpss.setParameterValue("OutputDirectory", outputDirectory);
    // 3. Pass the parameter n to your analysis engine(s) properly.
    // 4. Run the CPE.
	//Start Processing
	mCPE.process();
    // Implement your code from here.
	
  }

}
