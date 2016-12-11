import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Entity.Dictionary;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.junit.*;

import BL.getterBL;

/**
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 */
public class ApplicationTest {
    getterBL getterBL = new getterBL();

    @Test
    public void getListOfExistingLanguage() {
        System.out.println(getterBL.getListOFExistingLanguage());
    }

    @Test
    public void readXml() {
        try {

            File fXmlFile = new File("../app/Entity/Language/Language.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("hebrew");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Dictionary dictionaryToReturn = new Dictionary();
                    dictionaryToReturn.setHouseId(eElement.getElementsByTagName("house_id").item(0).getTextContent());
                    dictionaryToReturn.setState(eElement.getElementsByTagName("state").item(0).getTextContent());
                    dictionaryToReturn.setCity(eElement.getElementsByTagName("city").item(0).getTextContent());
                    dictionaryToReturn.setStreet(eElement.getElementsByTagName("street").item(0).getTextContent());
                    dictionaryToReturn.setHouseNumber(eElement.getElementsByTagName("house_number").item(0).getTextContent());
                    dictionaryToReturn.setHouseKind(eElement.getElementsByTagName("house_kind").item(0).getTextContent());
                    dictionaryToReturn.setNumberOfRooms(eElement.getElementsByTagName("number_of_rooms").item(0).getTextContent());
                    dictionaryToReturn.setNumberOfLivingRooms(eElement.getElementsByTagName("number_of_living_rooms").item(0).getTextContent());
                    dictionaryToReturn.setNumberOfKitchens(eElement.getElementsByTagName("number_of_kitchens").item(0).getTextContent());
                    dictionaryToReturn.setNumberOfBedrooms(eElement.getElementsByTagName("number_of_bedrooms").item(0).getTextContent());
                    dictionaryToReturn.setNumberOfBathrooms(eElement.getElementsByTagName("number_of_bathrooms").item(0).getTextContent());
                    dictionaryToReturn.setLocationKind(eElement.getElementsByTagName("location_kind").item(0).getTextContent());
                    dictionaryToReturn.setComments(eElement.getElementsByTagName("comments").item(0).getTextContent());
                    dictionaryToReturn.setPurchasePrice(eElement.getElementsByTagName("purchase_price").item(0).getTextContent());
                    dictionaryToReturn.setTreatmentFees(eElement.getElementsByTagName("treatment_fees").item(0).getTextContent());
                    dictionaryToReturn.setRenovationFeesForSale(eElement.getElementsByTagName("renovation_fees_for_sale").item(0).getTextContent());
                    dictionaryToReturn.setRenovationFeesForRenting(eElement.getElementsByTagName("renovation_fees_for_renting").item(0).getTextContent());
                    dictionaryToReturn.setDiversFees(eElement.getElementsByTagName("divers_fees").item(0).getTextContent());
                    System.out.println(dictionaryToReturn.toJson());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void replaceACharInAString() {
        String a = "blabla/blabla.jpg";
        String b = a.replace("/", "\\\\");
        System.out.println("a" + a);
        System.out.println("b" + b);
    }


}
