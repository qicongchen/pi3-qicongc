

/* First created by JCasGen Mon Sep 21 13:41:56 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** A parent annotation which expects individual annotation types to inherit from.
 * Updated by JCasGen Tue Sep 22 14:20:21 EDT 2015
 * XML source: /Users/yuchenluo/Downloads/pi3-qicongc/src/main/resources/typeSystem.xml
 * @generated */
public class ComponentAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ComponentAnnotation.class);
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
  protected ComponentAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ComponentAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ComponentAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ComponentAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: componentId

  /** getter for componentId - gets An identifier to figure out which system produces this annotation.
   * @generated
   * @return value of the feature 
   */
  public String getComponentId() {
    if (ComponentAnnotation_Type.featOkTst && ((ComponentAnnotation_Type)jcasType).casFeat_componentId == null)
      jcasType.jcas.throwFeatMissing("componentId", "type.ComponentAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ComponentAnnotation_Type)jcasType).casFeatCode_componentId);}
    
  /** setter for componentId - sets An identifier to figure out which system produces this annotation. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setComponentId(String v) {
    if (ComponentAnnotation_Type.featOkTst && ((ComponentAnnotation_Type)jcasType).casFeat_componentId == null)
      jcasType.jcas.throwFeatMissing("componentId", "type.ComponentAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ComponentAnnotation_Type)jcasType).casFeatCode_componentId, v);}    
   
    
  //*--------------*
  //* Feature: score

  /** getter for score - gets A component's confidence score assigned to this annotation.
   * @generated
   * @return value of the feature 
   */
  public double getScore() {
    if (ComponentAnnotation_Type.featOkTst && ((ComponentAnnotation_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "type.ComponentAnnotation");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((ComponentAnnotation_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets A component's confidence score assigned to this annotation. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore(double v) {
    if (ComponentAnnotation_Type.featOkTst && ((ComponentAnnotation_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "type.ComponentAnnotation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((ComponentAnnotation_Type)jcasType).casFeatCode_score, v);}    
  }

    