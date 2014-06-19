package ar.fiuba.tecnicas.logger.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ar.fiuba.tecnicas.logger.exceptions.MalformedConfigFileException;
import ar.fiuba.tecnicas.logger.model.Format;
import ar.fiuba.tecnicas.logger.model.Level;


/*
 * Responsabilities: provee acceso a un archivo xml e interpreta su contenido.
 * 
 * 
 * */

public class XMLConfigReaderAdapter extends ConfigReaderAdapter {

	private Document xmlDoc;
	private static final String OUTPUT_FACTORIES_TAGNAME = "output_types";
	private static final String OUTPUT_FILTERS_TAGNAME = "filters";
	
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
		
		NodeList nList = this.xmlDoc.getElementsByTagName(ConfigReaderAdapter.OUTPUT_CONFIG);
	    for (int i = 0; i < nList.getLength(); i++) {
	        NodeList childList = nList.item(i).getChildNodes();
	        Map<String,String> values = new HashMap<String, String>();
	        for (int j = 0; j < childList.getLength(); j++) {
	            Node childNode = childList.item(j);
	            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
	            	String key = childNode.getNodeName();
					String value = childNode.getTextContent();
					values.put(key, value);
	            }
	        }
	        this.replaceAlias(values, XMLConfigReaderAdapter.OUTPUT_FACTORIES_TAGNAME, OutputConfig.OUTPUT_FACTORY_CLASS_NAME);
	        this.replaceAlias(values, XMLConfigReaderAdapter.OUTPUT_FILTERS_TAGNAME, OutputConfig.FILTER_FACTORY_CLASS_NAME);
			OutputConfig outputConfig = new OutputConfig(values); 
			outputs.add(outputConfig);
	    }
		
		return outputs;
	}

	private void replaceAlias(Map<String, String> map, String tagName, String mapKey) throws MalformedConfigFileException{
		NodeList nList = this.xmlDoc.getElementsByTagName(tagName);
		if (nList.getLength() > 0){
			Node n = nList.item(0);
			NodeList childList = n.getChildNodes();
			for (int i = 0; i < childList.getLength(); i++){
				Node c = childList.item(i);
				if (c.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element)c;
					if (e.getAttribute("id").equalsIgnoreCase(map.get(mapKey))){
						map.put(mapKey, e.getTextContent());
						break;
					}
				}
				
			}
			
		}
	}

	@Override
	public Format getFormat() {
		String separator = this.getProperty(ConfigReaderAdapter.SEPARATOR_CONFIG);
		if (separator == null){separator = ConfigReaderAdapter.DEFAULT_SEPARATOR;}
		String format = this.getProperty(ConfigReaderAdapter.FORMAT_CONFIG);
		String formatType = this.getProperty(ConfigReaderAdapter.FORMAT_TYPE);
		String formatterClassName = "";
		NodeList nList = this.xmlDoc.getElementsByTagName(ConfigReaderAdapter.OUTPUT_FORMATTERS);
		if (nList.getLength() > 0){
			Node n = nList.item(0);
			NodeList childList = n.getChildNodes();
			for (int i = 0; i < childList.getLength(); i++){
				Node c = childList.item(i);
				if (c.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element)c;
					if (e.getAttribute("id").equalsIgnoreCase(formatType)){
						formatterClassName = e.getTextContent();
						break;
					}
				}
				
			}
			
		}
		return new Format(format, formatterClassName, separator);
	}

}

