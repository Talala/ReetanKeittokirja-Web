package klo;


import java.io.File;
import java.io.OutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;

public class XmlFileWriter {
	
	File fileToWrite;
	String filepath;
	Document xmlDoc;
	
	public XmlFileWriter(File f, String fp, Document doc) {
		
		fileToWrite = f;
		filepath = fp;
		xmlDoc = doc;
		
	}
	
	public void setFile(File f) {
		
		fileToWrite = f;
	}
	
	public File getFile() {
		
		return this.fileToWrite;
	}
	
	public void setFilePath (String fn) {
		
		filepath = fn;
		
	}
	
	public void setDocument(Document doc) {
		
		xmlDoc = doc;
	}
	
	public void writeFile()  {
		
		
		try {
			File f = new File(filepath);
			
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			//transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			DOMSource source = new DOMSource(xmlDoc);			
			StreamResult result = new StreamResult(f);
			transformer.transform(source, result);
			
			
			
			
			
			
		} catch (TransformerConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerException e) {
			
			e.printStackTrace();
		}  catch (DOMException dex) {
		
		}

		
	}
	

}
