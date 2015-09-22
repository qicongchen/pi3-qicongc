package annotator;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;

import type.*;

public class TokenAnnotator extends JCasAnnotator_ImplBase {
	private Pattern tokenPattern = Pattern.compile("\\b\\w+\\b");


  public void process(JCas aJCas) {
    
    // The JCas object is the data object inside UIMA where all the 
    // information is stored. It contains all annotations created by 
    // previous annotators, and the document text to be analyzed.
	  
    FSIndex inputDocumentIndex = aJCas.getAnnotationIndex(InputDocument.type);
    Iterator inputDocumentIter = inputDocumentIndex.iterator();
    if (inputDocumentIter.hasNext()) {
        InputDocument inputDocument = (InputDocument) inputDocumentIter.next();
        Question question = inputDocument.getQuestion();
        Matcher matcher = tokenPattern.matcher(question.getSentence());
        int pos = 0;     
        while (matcher.find(pos)) {
          // match found - create the match as annotation in 
          // the JCas with some additional meta information
          // getContext().getLogger().log(Level.FINEST, "Found: " + matcher.group());
          Token token = new Token(aJCas);
          token.setBegin(question.getBegin()+matcher.start());
          token.setEnd(question.getBegin()+matcher.end());
          token.addToIndexes();
          pos = matcher.end();
        }
        
        FSList answers = inputDocument.getAnswers();
        while (answers instanceof NonEmptyFSList) {
    	  FeatureStructure head = ((NonEmptyFSList)answers).getHead();
    	  //do something with this element
    	  Answer answer = (Answer) head;
    	  matcher = tokenPattern.matcher(answer.getSentence());
      	  pos = 0;     
          while (matcher.find(pos)) {
            // match found - create the match as annotation in 
            // the JCas with some additional meta information
            Token token = new Token(aJCas);
            token.setBegin(answer.getBegin()+matcher.start());
            token.setEnd(answer.getBegin()+matcher.end());
            token.addToIndexes();
            pos = matcher.end();
          }
    	  answers = ((NonEmptyFSList)answers).getTail();
    	}
       
    }
   
  }
}
