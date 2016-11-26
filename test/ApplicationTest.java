import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Entity.House;
import Entity.HouseLanguage;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {


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

                    HouseLanguage houseLanguageToReturn = new HouseLanguage();
                    houseLanguageToReturn.setHouseId(eElement.getElementsByTagName("house_id").item(0).getTextContent());
                    houseLanguageToReturn.setState(eElement.getElementsByTagName("state").item(0).getTextContent());
                    houseLanguageToReturn.setCity(eElement.getElementsByTagName("city").item(0).getTextContent());
                    houseLanguageToReturn.setStreet(eElement.getElementsByTagName("street").item(0).getTextContent());
                    houseLanguageToReturn.setHouseNumber(eElement.getElementsByTagName("house_number").item(0).getTextContent());
                    houseLanguageToReturn.setHouseKind(eElement.getElementsByTagName("house_kind").item(0).getTextContent());
                    houseLanguageToReturn.setNumberOfRooms(eElement.getElementsByTagName("number_of_rooms").item(0).getTextContent());
                    houseLanguageToReturn.setNumberOfLivingRooms(eElement.getElementsByTagName("number_of_living_rooms").item(0).getTextContent());
                    houseLanguageToReturn.setNumberOfKitchens(eElement.getElementsByTagName("number_of_kitchens").item(0).getTextContent());
                    houseLanguageToReturn.setNumberOfBedrooms(eElement.getElementsByTagName("number_of_bedrooms").item(0).getTextContent());
                    houseLanguageToReturn.setNumberOfBathrooms(eElement.getElementsByTagName("number_of_bathrooms").item(0).getTextContent());
                    houseLanguageToReturn.setLocationKind(eElement.getElementsByTagName("location_kind").item(0).getTextContent());
                    houseLanguageToReturn.setComments(eElement.getElementsByTagName("comments").item(0).getTextContent());
                    houseLanguageToReturn.setPurchasePrice(eElement.getElementsByTagName("purchase_price").item(0).getTextContent());
                    houseLanguageToReturn.setTreatmentFees(eElement.getElementsByTagName("treatment_fees").item(0).getTextContent());
                    houseLanguageToReturn.setRenovationFees(eElement.getElementsByTagName("renovation_fees").item(0).getTextContent());
                    houseLanguageToReturn.setDiversFees(eElement.getElementsByTagName("divers_fees").item(0).getTextContent());
                    System.out.println(houseLanguageToReturn.toJson());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
