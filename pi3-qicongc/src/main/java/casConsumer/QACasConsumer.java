/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package casConsumer;
/* 
 *******************************************************************************************
 * N O T E :     The XML format (XCAS) that this Cas Consumer outputs, is eventually 
 *               being superceeded by the more standardized and compact XMI format.  However
 *               it is used currently as the expected form for remote services, and there is
 *               existing tooling for doing stand-alone component development and debugging 
 *               that uses this format to populate an initial CAS.  So it is not 
 *               deprecated yet;  it is also being kept for compatibility with older versions.
 *               
 *               New code should consider using the XmiWriterCasConsumer where possible,
 *               which uses the current XMI format for XML externalizations of the CAS
 *******************************************************************************************               
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import type.Answer;
import type.InputDocument;
import type.Question;
import type.SourceDocumentInformation;

/**
 * A simple CAS consumer that generates XCAS (XML representation of the CAS) files in the
 * filesystem.
 * <p>
 * This CAS Consumer takes one parameters:
 * <ul>
 * <li><code>OutputDirectory</code> - path to directory into which output files will be written</li>
 * </ul>
 * 
 * 
 */
public class QACasConsumer extends CasConsumer_ImplBase {
  /**
   * Name of configuration parameter that must be set to the path of a directory into which the
   * output files will be written.
   */
  public static final String PARAM_OUTPUTDIR = "OutputDirectory";

  private File mOutputDir;

  private int mDocNum;

  public void initialize() throws ResourceInitializationException {
    mDocNum = 0;
    mOutputDir = new File((String) getConfigParameterValue(PARAM_OUTPUTDIR));
    if (!mOutputDir.exists()) {
      mOutputDir.mkdirs();
    }
  }

  /**
   * Processes the CasContainer which was populated by the TextAnalysisEngines. <br>
   * In this case, the CAS is converted to XML and written into the output file .
   * 
   * @param aCAS
   *          CasContainer which has been populated by the TAEs
   * 
   * @throws ResourceProcessException
   *           if there is an error in processing the Resource
   * 
   * @see org.apache.uima.collection.base_cpm.CasObjectProcessor#processCas(org.apache.uima.cas.CAS)
   */
  public void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }

    // retrieve the filename of the input file from the CAS
    FSIterator it = jcas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
    File outFile = null;
    if (it.hasNext()) {
      SourceDocumentInformation fileLoc = (SourceDocumentInformation) it.next();
      File inFile;
      try {
        inFile = new File(new URL(fileLoc.getUri()).getPath());
        String outFileName = "a"+inFile.getName().substring(1);
        if (fileLoc.getOffsetInSource() > 0) {
          outFileName += fileLoc.getOffsetInSource();
        }
        outFile = new File(mOutputDir, outFileName);
      } catch (MalformedURLException e1) {
        // invalid URL, use default processing below
      }
    }
    if (outFile == null) {
      outFile = new File(mOutputDir, "doc" + mDocNum++);
    }
    
    FSIndex inputDocumentIndex = jcas.getAnnotationIndex(InputDocument.type);
    Iterator inputDocumentIter = inputDocumentIndex.iterator();
    if (inputDocumentIter.hasNext()) {
        InputDocument inputDocument = (InputDocument) inputDocumentIter.next();
        try {
			writeAnswers(outFile, inputDocument);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  }
  
  private void writeAnswers(File fout, InputDocument inputDocument) throws IOException {
  	FileOutputStream fos = new FileOutputStream(fout);
  	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
   
  	Question question = inputDocument.getQuestion();
  	bw.write(String.format("%g\n", question.getScore()));
  	
    FSList answers = inputDocument.getAnswers();
    while (answers instanceof NonEmptyFSList) {
	  FeatureStructure head = ((NonEmptyFSList)answers).getHead();
	  //do something with this element
	  Answer answer = (Answer) head;
	  bw.write(String.format("%s %g\n", answer.getId(),answer.getScore()));
	  answers = ((NonEmptyFSList)answers).getTail();
	}
   
  	bw.close();
  }

}
