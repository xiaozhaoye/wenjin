
import org.apache.xerces.dom.DeepNodeListImpl;
        import org.apache.xml.serialize.OutputFormat;
        import org.apache.xml.serialize.XMLSerializer;
        import org.w3c.dom.Document;
        import org.w3c.dom.Node;
        import org.w3c.dom.NodeList;

        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import java.io.IOException;
        import java.util.Enumeration;
        import java.util.zip.ZipEntry;
        import java.util.zip.ZipFile;

public class TestXFormData {

    private static StringBuilder nodeContent;

    public static void main(String[] args) throws Exception {
        //Unzip the openOffice Document
        ZipFile zipFile = new ZipFile("C:\\Users\\Administrator.DESKTOP-61JNMB9\\Desktop\\df.odt");
        Enumeration entries = zipFile.entries();
        ZipEntry entry;

        while(entries.hasMoreElements()) {
            entry = (ZipEntry) entries.nextElement();
            if (entry.getName().equals("content.xml")) {
                // construct document
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder docBuilder = domFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(zipFile.getInputStream(entry));
                // print the document
                printDocument(doc);
                // get the node
                NodeList list = doc.getElementsByTagName("myTagName");
                Node node = ((DeepNodeListImpl) list).item(0);
                nodeContent = new StringBuilder();
                // print the xml with the form data
                prettyPrint(node);
                System.out.println(nodeContent.toString());
            }
        }
    }


    private static void prettyPrint(Node node) {
        if (node.getNodeType() == Node.TEXT_NODE) {
            nodeContent.append(node.getNodeValue());
        } else if (node.getNodeType() == Node.ELEMENT_NODE) {
            nodeContent.append("<" + node.getNodeName() + ">");
            NodeList kids = node.getChildNodes();
            for (int i = 0; i < kids.getLength(); i++) {
                prettyPrint(kids.item(i));
            }
            nodeContent.append("</" + node.getNodeName() + ">");
        }
    }


    private static void printDocument(Document doc) throws IOException {
        OutputFormat format = new OutputFormat(doc);
        format.setIndenting(true);
        XMLSerializer serializer = new XMLSerializer(System.out, format);
        serializer.serialize(doc);
    }
}