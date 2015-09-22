package annotator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;

import type.*;

public class AnswerScoringAnnotator extends JCasAnnotator_ImplBase {

  private double getAnswerScore(List<String> qNgramList, List<String> aNgramList){
	  int overlap = 0;
	  int total = aNgramList.size();
	  if (total>0){
		  for (String ngram : qNgramList){
			  if (aNgramList.contains(ngram)){ 
				  overlap++;
			  }
		  }
		  return (overlap+0.0)/total;
	  }
	  else
		  return 0.0;
  }
  
  public void process(JCas aJCas) {
    
    // The JCas object is the data object inside UIMA where all the 
    // information is stored. It contains all annotations created by 
    // previous annotators, and the document text to be analyzed.
	String docText = aJCas.getDocumentText();
    FSIndex inputDocumentIndex = aJCas.getAnnotationIndex(InputDocument.type);
    FSIndex ngramIndex = aJCas.getAnnotationIndex(Ngram.type);
    Iterator inputDocumentIter = inputDocumentIndex.iterator();
    if (inputDocumentIter.hasNext()) {
        InputDocument inputDocument = (InputDocument) inputDocumentIter.next();
        Question question = inputDocument.getQuestion();
        Iterator ngramIter = ngramIndex.iterator();
        List<String> questionNgramList = new LinkedList<String>();
        while (ngramIter.hasNext()){
        	Ngram ngram = (Ngram) ngramIter.next();
        	int begin = ngram.getBegin();
        	int end = ngram.getEnd();
        	if (begin < question.getBegin())
        		continue;
        	else if (end > question.getEnd())
        		break;
        	else{
        		questionNgramList.add(docText.substring(begin, end));
        	}
        }
        
        FSList answers = inputDocument.getAnswers();
        while (answers instanceof NonEmptyFSList) {
    	  FeatureStructure head = ((NonEmptyFSList)answers).getHead();
    	  //do something with this element
    	  Answer answer = (Answer) head;
    	  ngramIter = ngramIndex.iterator();
          List<String> answerNgramList = new LinkedList<String>();
          while (ngramIter.hasNext()){
          	Ngram ngram = (Ngram) ngramIter.next();
          	int begin = ngram.getBegin();
        	int end = ngram.getEnd();
        	if (begin < answer.getBegin())
        		continue;
        	else if (end > answer.getEnd())
        		break;
        	else{
        		answerNgramList.add(docText.substring(begin, end));
        	}
          }
          double score = getAnswerScore(questionNgramList, answerNgramList);
          answer.setScore(score);
    	  answers = ((NonEmptyFSList)answers).getTail();
    	}
        inputDocument.addToIndexes();
    }
   
  }
}
