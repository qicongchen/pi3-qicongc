import org.apache.uima.UIMAFramework;
import org.apache.uima.collection.CollectionProcessingEngine;
import org.apache.uima.collection.base_cpm.CasProcessor;
import org.apache.uima.collection.metadata.CpeDescription;
import org.apache.uima.resource.ConfigurableResource;
import org.apache.uima.util.XMLInputSource;
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
	      parseCpeDescription(new XMLInputSource("src/main/resources/cpeDescriptor.xml"));
	
	//instantiate CPE
	mCPE = UIMAFramework.produceCollectionProcessingEngine(cpeDesc);
	ConfigurableResource collectionReader = (ConfigurableResource) mCPE.getCollectionReader();
	collectionReader.setConfigParameterValue("InputDirectory", inputDirectory);
	collectionReader.reconfigure();
	CasProcessor[] casProcessors = mCPE.getCasProcessors();
	ConfigurableResource analysisEngine = (ConfigurableResource) casProcessors[0];
	analysisEngine.setConfigParameterValue("N", n);
	analysisEngine.reconfigure();
	ConfigurableResource casConsumer = (ConfigurableResource) casProcessors[1];
	casConsumer.setConfigParameterValue("OutputDirectory", outputDirectory);
	casConsumer.reconfigure();
    // 3. Pass the parameter n to your analysis engine(s) properly.
    // 4. Run the CPE.
	//Start Processing
	mCPE.process();
	
  }

}
