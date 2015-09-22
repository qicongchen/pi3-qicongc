

/* First created by JCasGen Mon Sep 21 13:41:56 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Stores the information about an answer.
 * Updated by JCasGen Tue Sep 22 14:20:21 EDT 2015
 * XML source: /Users/yuchenluo/Downloads/pi3-qicongc/src/main/resources/typeSystem.xml
 * @generated */
public class Answer extends ComponentAnnotation implements Comparable<Answer> {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Answer.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Answer() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Answer(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Answer(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Answer(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets An identifier for answers.
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "type.Answer");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Answer_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets An identifier for answers. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "type.Answer");
    jcasType.ll_cas.ll_setStringValue(addr, ((Answer_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: sentence

  /** getter for sentence - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSentence() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "type.Answer");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Answer_Type)jcasType).casFeatCode_sentence);}
    
  /** setter for sentence - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentence(String v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "type.Answer");
    jcasType.ll_cas.ll_setStringValue(addr, ((Answer_Type)jcasType).casFeatCode_sentence, v);}    
   
    
  //*--------------*
  //* Feature: label

  /** getter for label - gets This stores a gold standard label of this answer.  'True' means that it is correct; otherwise, it is incorrect.
   * @generated
   * @return value of the feature 
   */
  public boolean getLabel() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "type.Answer");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Answer_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets This stores a gold standard label of this answer.  'True' means that it is correct; otherwise, it is incorrect. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLabel(boolean v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "type.Answer");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Answer_Type)jcasType).casFeatCode_label, v);}    
  
  	@Override
	public int compareTo(Answer o) {
		// TODO Auto-generated method stub
		if (this.getScore() > o.getScore())
			return -1;
		else if (this.getScore() < o.getScore())
			return 1;
		else
			return 0;
	}    
  }

    