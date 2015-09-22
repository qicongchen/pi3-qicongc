

/* First created by JCasGen Mon Sep 21 13:41:56 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;


import org.apache.uima.jcas.cas.FSList;


/** Stores the information about an n-gram.
 * Updated by JCasGen Tue Sep 22 14:20:21 EDT 2015
 * XML source: /Users/yuchenluo/Downloads/pi3-qicongc/src/main/resources/typeSystem.xml
 * @generated */
public class Ngram extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Ngram.class);
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
  protected Ngram() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Ngram(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Ngram(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Ngram(JCas jcas, int begin, int end) {
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
  //* Feature: n

  /** getter for n - gets The number of tokens in this n-gram.
   * @generated
   * @return value of the feature 
   */
  public int getN() {
    if (Ngram_Type.featOkTst && ((Ngram_Type)jcasType).casFeat_n == null)
      jcasType.jcas.throwFeatMissing("n", "type.Ngram");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Ngram_Type)jcasType).casFeatCode_n);}
    
  /** setter for n - sets The number of tokens in this n-gram. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setN(int v) {
    if (Ngram_Type.featOkTst && ((Ngram_Type)jcasType).casFeat_n == null)
      jcasType.jcas.throwFeatMissing("n", "type.Ngram");
    jcasType.ll_cas.ll_setIntValue(addr, ((Ngram_Type)jcasType).casFeatCode_n, v);}    
   
    
  //*--------------*
  //* Feature: tokens

  /** getter for tokens - gets Tokens contained in this n-gram.
   * @generated
   * @return value of the feature 
   */
  public FSList getTokens() {
    if (Ngram_Type.featOkTst && ((Ngram_Type)jcasType).casFeat_tokens == null)
      jcasType.jcas.throwFeatMissing("tokens", "type.Ngram");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Ngram_Type)jcasType).casFeatCode_tokens)));}
    
  /** setter for tokens - sets Tokens contained in this n-gram. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTokens(FSList v) {
    if (Ngram_Type.featOkTst && ((Ngram_Type)jcasType).casFeat_tokens == null)
      jcasType.jcas.throwFeatMissing("tokens", "type.Ngram");
    jcasType.ll_cas.ll_setRefValue(addr, ((Ngram_Type)jcasType).casFeatCode_tokens, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    