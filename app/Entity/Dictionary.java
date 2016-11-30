package Entity;

/**
 * Created by jacky on 18/11/2016.
 */
public class Dictionary {
    private String szHouseLanguage;
    private String nHouseId;
    private String szAddress;
    private String szState;
    private String szCity;
    private String szStreet;
    private String nHouseNumber;
    private String EHouseKind;
    private String nNumberOfRooms;
    private String nNumberOfLivingRooms;
    private String nNumberOfKitchens;
    private String nNumberOfBedrooms;
    private String nNumberOfBathrooms;
    private String eLocationKind;
    private String nScore;
    private String szComments;
    private String dPurchasePrice;
    private String dTreatmentFees;
    private String dRenovationFeesForSale;
    private String dRenovationFeesForRenting;
    private String dDiversFees;
    private String szGeneralHouseDetailes;
    private String szFinancialHouseDetailes;


    public String getHouseId() {
        return nHouseId;
    }

    public void setHouseId(String nHouseId) {
        this.nHouseId = nHouseId;
    }

    public String getState() {
        return szState;
    }

    public void setState(String szState) {
        this.szState = szState;
    }

    public String getCity() {
        return szCity;
    }

    public void setCity(String szCity) {
        this.szCity = szCity;
    }

    public String getStreet() {
        return szStreet;
    }

    public void setStreet(String szStreet) {
        this.szStreet = szStreet;
    }

    public String getHouseNumber() {
        return nHouseNumber;
    }

    public void setHouseNumber(String nHouseNumber) {
        this.nHouseNumber = nHouseNumber;
    }

    public String getEHouseKind() {
        return EHouseKind;
    }

    public void setHouseKind(String EHouseKind) {
        this.EHouseKind = EHouseKind;
    }

    public String getNumberOfRooms() {
        return nNumberOfRooms;
    }

    public void setNumberOfRooms(String nNumberOfRooms) {
        this.nNumberOfRooms = nNumberOfRooms;
    }

    public String getNumberOfLivingRooms() {
        return nNumberOfLivingRooms;
    }

    public void setNumberOfLivingRooms(String nNumberOfLivingRooms) {
        this.nNumberOfLivingRooms = nNumberOfLivingRooms;
    }

    public String getNumberOfKitchens() {
        return nNumberOfKitchens;
    }

    public void setNumberOfKitchens(String nNumberOfKitchens) {
        this.nNumberOfKitchens = nNumberOfKitchens;
    }

    public String getNumberOfBedrooms() {
        return nNumberOfBedrooms;
    }

    public void setNumberOfBedrooms(String nNumberOfBedrooms) {
        this.nNumberOfBedrooms = nNumberOfBedrooms;
    }

    public String getNumberOfBathrooms() {
        return nNumberOfBathrooms;
    }

    public void setNumberOfBathrooms(String nNumberOfBathrooms) {
        this.nNumberOfBathrooms = nNumberOfBathrooms;
    }

    public String getLocationKind() {
        return eLocationKind;
    }

    public void setLocationKind(String eLocationKind) {
        eLocationKind = eLocationKind;
    }

    public String getComments() {
        return szComments;
    }

    public void setComments(String szComments) {
        this.szComments = szComments;
    }

    public String getPurchasePrice() {
        return dPurchasePrice;
    }

    public void setPurchasePrice(String dPurchasePrice) {
        this.dPurchasePrice = dPurchasePrice;
    }

    public String getTreatmentFees() {
        return dTreatmentFees;
    }

    public void setTreatmentFees(String dTreatmentFees) {
        this.dTreatmentFees = dTreatmentFees;
    }

    public String getdRenovationFeesForSale() {
        return dRenovationFeesForSale;
    }

    public void setRenovationFeesForSale(String dRenovationFees) {
        this.dRenovationFeesForSale = dRenovationFees;
    }

    public String getDiversFees() {
        return dDiversFees;
    }

    public void setDiversFees(String dDiversFees) {
        this.dDiversFees = dDiversFees;
    }

    public String getHouseLanguage() {
        return szHouseLanguage;
    }

    public void setHouseLanguage(String szHouseLanguage) {
        this.szHouseLanguage = szHouseLanguage;
    }

    public String getAddress() {
        return szAddress;
    }

    public void setAddress(String szAdress) {
        this.szAddress = szAdress;
    }

    public String getScore() {
        return nScore;
    }

    public void setScore(String nScore) {
        this.nScore = nScore;
    }

    public String getRenovationFeesForRenting() {
        return dRenovationFeesForRenting;
    }

    public void setRenovationFeesForRenting(String dRenovationFeesForRenting) {
        this.dRenovationFeesForRenting = dRenovationFeesForRenting;
    }

    public String getGeneralHouseDetailes() {
        return szGeneralHouseDetailes;
    }

    public void setGeneralHouseDetailes(String szGeneralHouseDetailes) {
        this.szGeneralHouseDetailes = szGeneralHouseDetailes;
    }

    public String getFinancialHouseDetailes() {
        return szFinancialHouseDetailes;
    }

    public void setFinancialHouseDetailes(String szFinancialHouseDetailes) {
        this.szFinancialHouseDetailes = szFinancialHouseDetailes;
    }

    public String toJson() {
        return "{ \"Dictionary\":[ {" +
                "\"Dictionary\":\"" + szHouseLanguage + "\"" +
                ",\"HouseId\":\"" + nHouseId + "\"" +
                ",\"Address\":\"" + szAddress + "\"" +
                ",\"State\":\"" + szState + "\"" +
                ",\"City\":\"" + szCity + "\"" +
                ",\"Street\":\"" + szStreet + "\"" +
                ",\"HouseNumber\":\"" + nHouseNumber + "\"" +
                ",\"HouseKind\":\"" + EHouseKind + "\"" +
                ",\"NumberOfRooms\":\"" + nNumberOfRooms + "\"" +
                ",\"NumberOfLivingRooms\":\"" + nNumberOfLivingRooms + "\"" +
                ",\"NumberOfKitchens\":\"" + nNumberOfKitchens + "\"" +
                ",\"NumberOfBedrooms\":\"" + nNumberOfBedrooms + "\"" +
                ",\"NumberOfBathrooms\":\"" + nNumberOfBathrooms + "\"" +
                ",\"LocationKind\":\"" + eLocationKind + "\"" +
                ",\"Score\":\"" + nScore + "\"" +
                ",\"Comments\":\"" + szComments + "\"" +
                ",\"PurchasePrice\":\"" + dPurchasePrice + "\"" +
                ",\"TreatmentFees\":\"" + dTreatmentFees + "\"" +
                ",\"RenovationFeesForSale\":\"" + dRenovationFeesForSale + "\"" +
                ",\"RenovationFeesForRenting\":\"" + dRenovationFeesForRenting + "\"" +
                ",\"GeneralHouseDetailes\":\"" + szGeneralHouseDetailes+ "\"" +
                ",\"FinancialHouseDetailes\":\"" + szFinancialHouseDetailes + "\"" +
                ",\"DiversFees\":\"" + dDiversFees + "\"" +
                "} ]}";
    }
}
