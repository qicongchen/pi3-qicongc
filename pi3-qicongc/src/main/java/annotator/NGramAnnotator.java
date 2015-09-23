package annotator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.fit.util.FSCollectionFactory;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.resource.ResourceInitializationException;

import type.*;

public class NGramAnnotator extends JCasAnnotator_ImplBase {
	private Integer N;

	/**
	   * @see AnalysisComponent#initialize(UimaContext)
	   */
	  public void initialize(UimaContext aContext) throws ResourceInitializationException {
	    super.initialize(aContext);
	    // Get config. parameter values
	    N = (Integer)aContext.getConfigParameterValue("N");
	  }
	  
  public void process(JCas aJCas) {
    
    // The JCas object is the data object inside UIMA where all the 
    // information is stored. It contains all annotations created by 
    // previous annotators, and the document text to be analyzed.
	  
    FSIndex inputDocumentIndex = aJCas.getAnnotationIndex(InputDocument.type);
    FSIndex tokenIndex = aJCas.getAnnotationIndex(Token.type);
    Iterator inputDocumentIter = inputDocumentIndex.iterator();
    if (inputDocumentIter.hasNext()) {
        InputDocument inputDocument = (InputDocument) inputDocumentIter.next();
        Question question = inputDocument.getQuestion();
        Iterator tokenIter = tokenIndex.iterator();
        List<Token> tokenList = new LinkedList<Token>();
        while (tokenIter.hasNext()){
        	Token token = (Token) tokenIter.next();
        	int begin = token.getBegin();
        	int end = token.getEnd();
        	if (begin < question.getBegin())
        		continue;
        	else if (end > question.getEnd())
        		break;
        	else
        		tokenList.add(token);
        }
        for (int i=0; i<= tokenList.size()-N ; i++){
        	Ngram ngram = new Ngram(aJCas);
        	List<Token> subTokenList = tokenList.subList(i, i+N);
        	FSList tokens = FSCollectionFactory.createFSList(aJCas, subTokenList);
        	ngram.setBegin(tokenList.get(i).getBegin());
        	ngram.setEnd(tokenList.get(i+N-1).getEnd());
        	ngram.setN(N);
        	ngram.setTokens(tokens);
        	ngram.addToIndexes();
        }
        
        
        FSList answers = inputDocument.getAnswers();
        while (answers instanceof NonEmptyFSList) {
    	  FeatureStructure head = ((NonEmptyFSList)answers).getHead();
    	  //do something with this element
    	  Answer answer = (Answer) head;
    	  tokenIter = tokenIndex.iterator();
          tokenList = new LinkedList<Token>();
          while (tokenIter.hasNext()){
          	Token token = (Token) tokenIter.next();
          	int begin = token.getBegin();
          	int end = token.getEnd();
          	if (begin < answer.getBegin())
        		continue;
        	else if (end > answer.getEnd())
        		break;
        	else
        		tokenList.add(token);
          }
          for (int i=0; i<= tokenList.size()-N ; i++){
          	Ngram ngram = new Ngram(aJCas);
          	List<Token> subTokenList = tokenList.subList(i, i+N);
          	FSList tokens = FSCollectionFactory.createFSList(aJCas, subTokenList);
          	ngram.setBegin(tokenList.get(i).getBegin());
          	ngram.setEnd(tokenList.get(i+N-1).getEnd());
          	ngram.setN(N);
          	ngram.setTokens(tokens);
          	ngram.addToIndexes();
          }
    	  answers = ((NonEmptyFSList)answers).getTail();
    	}
       
    }
   
  }
}
