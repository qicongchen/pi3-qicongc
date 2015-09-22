package annotator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.fit.util.FSCollectionFactory;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;

import type.*;

public class EvaluationAnnotator extends JCasAnnotator_ImplBase {

  private double getQuestionScore(List<Answer> answerList){
	  int correct = 0;
	  int total = 0;
    
      for (Answer answer : answerList){
      	if (answer.getLabel())
      		total++;
      }
      if (total>0){
    	  for (Answer answer : answerList.subList(0, total)){
	      	if (answer.getLabel())
	      		correct++;
	      }
		  return (correct+0.0)/total;
      }
      else
    	  return 0.0;
      
  }
  
  public void process(JCas aJCas) {
    
    // The JCas object is the data object inside UIMA where all the 
    // information is stored. It contains all annotations created by 
    // previous annotators, and the document text to be analyzed.
    FSIndex inputDocumentIndex = aJCas.getAnnotationIndex(InputDocument.type);
    Iterator inputDocumentIter = inputDocumentIndex.iterator();
    if (inputDocumentIter.hasNext()) {
        InputDocument inputDocument = (InputDocument) inputDocumentIter.next();
        Question question = inputDocument.getQuestion();
        
        FSList answers = inputDocument.getAnswers();
        List<Answer> answerList = new ArrayList<Answer>();
        while (answers instanceof NonEmptyFSList) {
    	  FeatureStructure head = ((NonEmptyFSList)answers).getHead();
    	  //do something with this element
    	  Answer answer = (Answer) head;
          answerList.add(answer);
    	  answers = ((NonEmptyFSList)answers).getTail();
    	}
        // sort the answers
        Collections.sort(answerList);
        answers = FSCollectionFactory.createFSList(aJCas, answerList);
        inputDocument.setAnswers(answers);
        // evaluation
        double score = getQuestionScore(answerList);
        question.setScore(score);
        inputDocument.setScore(score);
        inputDocument.addToIndexes();
    }
   
  }
}
