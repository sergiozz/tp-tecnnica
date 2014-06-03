package ar.fiuba.tecnicas.logger.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;
import ar.fiuba.tecnicas.logger.model.Level;

public class XMLConfigReaderAdapter extends ConfigReaderAdapter {

	private static final String OUTPUTS_TAG_NAME = "outputs";
	private Document xmlDoc;
	
	@Override
	public String getProperty(String key) {
		return  xmlDoc.getElementsByTagName(key).item(0).getTextContent(); 
		
	}

	@Override
	public void loadConfig(String filename) throws FileNotFoundException, MalformedConfigFileException {
		try{
			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			this.xmlDoc = dBuilder.parse(fXmlFile);
			this.xmlDoc.getDocumentElement().normalize();
		}catch(Exception e){
			
		}
	}

	@Override
	public List<OutputConfig> getOutputConfigs() throws MalformedConfigFileException{
		List<OutputConfig> outputs = new ArrayList<OutputConfig>();
		
		NodeList nList = this.xmlDoc.getElementsByTagName(OUTPUTS_TAG_NAME);
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String path = eElement.getElementsByTagName("filename").item(0).getTextContent();
				Level filter = Level.valueOf(eElement.getElementsByTagName("filter").item(0).getTextContent());
				OutputType outputType = OutputType.valueOf(eElement.getElementsByTagName("type").item(0).getTextContent());
				OutputConfig outputConfig = new OutputConfig(filter, path, outputType); 
				outputs.add(outputConfig);
			}
		}
		
		return outputs;
	}

}

