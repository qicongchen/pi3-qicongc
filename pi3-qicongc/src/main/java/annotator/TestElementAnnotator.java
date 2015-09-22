package annotator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.fit.util.FSCollectionFactory;
import org.apache.uima.jcas.cas.FSList;

import type.*;

public class TestElementAnnotator extends JCasAnnotator_ImplBase {
  // create regular expression pattern for Question
	private Pattern questionPattern = Pattern.compile("(Q) (.*)");

  // create regular expression pattern for Answer
	private Pattern answerPattern = Pattern.compile("(A\\d+) ([0-1]) (.*)");
	
	
	private boolean stringToBoolean(String str){
		if (str.equals("1"))
			return true;
		else
			return false;
	}

  public void process(JCas aJCas) {
    
    // The JCas object is the data object inside UIMA where all the 
    // information is stored. It contains all annotations created by 
    // previous annotators, and the document text to be analyzed.
    InputDocument inputDocument = new InputDocument(aJCas);
    // get document text from JCas
    String docText = aJCas.getDocumentText();
    
    // search for Questions
    Matcher matcher = questionPattern.matcher(docText);
    int pos = 0;
    matcher.find(pos);
	Question question = new Question(aJCas);
	inputDocument.setBegin(matcher.start());
	question.setBegin(matcher.start(2));
	question.setEnd(matcher.end());
	question.setId(matcher.group(1));
	question.setSentence(matcher.group(2));
	inputDocument.setQuestion(question);
  
    // search for Answers
    matcher = answerPattern.matcher(docText);
    pos = 0;
    List<Answer> answerList=new LinkedList<Answer>();
    while (matcher.find(pos)) {
      // match found - create the match as annotation in 
      // the JCas with some additional meta information
      Answer answer = new Answer(aJCas);
      answer.setBegin(matcher.start(3));
      answer.setEnd(matcher.end());
      answer.setId(matcher.group(1));
      answer.setSentence(matcher.group(3));
      answer.setLabel(stringToBoolean(matcher.group(2)));
      answerList.add(answer);
      pos = matcher.end();
    }
    FSList answers = FSCollectionFactory.createFSList(aJCas, answerList);
    inputDocument.setEnd(pos);
    inputDocument.setAnswers(answers);
    inputDocument.addToIndexes();
  }
}
