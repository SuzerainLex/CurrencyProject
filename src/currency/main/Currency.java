package currency.main;

import currency.document.NewDocument;
import currency.gui.NewFrame;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Currency {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String[][] rates = getRates();
        NewFrame.getFrame(rates);
    }

    private static String[][] getRates() throws IOException, SAXException, ParserConfigurationException {
        HashMap<String, NodeList> result = new HashMap<>();
        String[][] rates;
        NodeList nl = NewDocument.getDocument().getElementsByTagName("Valute");
        for (int i = 0; i < nl.getLength(); i++) {
            Node c = nl.item(i);
            NodeList nlChilds = c.getChildNodes();
            for (int j = 0; j < nlChilds.getLength(); j++) {
                if (nlChilds.item(j).getNodeName().equals("CharCode")) {
                    result.put(nlChilds.item(j).getTextContent(), nlChilds);
                }
            }
        }
        int k = 0;
        rates = new String[result.size()][2];

        for (Map.Entry<String, NodeList> entry : result.entrySet()) {
            NodeList temp = entry.getValue();
            double value = 0;
            int nominal = 0;
            for (int i = 0; i < temp.getLength(); i++) {
                if (temp.item(i).getNodeName().equals("Value")) {
                    value = Double.parseDouble(temp.item(i).getTextContent().replace(',', '.'));
                } else if (temp.item(i).getNodeName().equals("Nominal")) {
                    nominal = Integer.parseInt(temp.item(i).getTextContent());
                }
            }
            double amount = value / nominal;
            rates[k][0] = entry.getKey();
            rates[k][1] = ((double) Math.round(amount * 10000) / 10000) + " рублей";
            k++;
        }
        return rates;
    }
}
