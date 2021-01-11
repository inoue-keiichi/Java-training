package ch09.ex04;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

	// XML解析でParserConfigurationException, SAXException, IOExceptionの複数例外をキャッチしないといけない
	public static void main(final String[] args) {
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final File f = new File("MyTest.gwt.xml");
			final Document doc = builder.parse(f);
			final Element root = doc.getDocumentElement();
			final NodeList children = root.getChildNodes();

			for (int i = 0; i < children.getLength(); i++) {
				final Node child = children.item(i);
				if (child instanceof Element) {
					final Element childElement = (Element) child;
					System.out.println(childElement.getTagName());
				}
			}

		} catch (final ParserConfigurationException | SAXException | IOException e) {

		}
	}
}
