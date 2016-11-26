package Entity;

/**
 * Created by jacky on 18/11/2016.
 */
public class HouseLanguage {
    private String nHouseId;
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
    private String szComments;
    private String dPurchasePrice;
    private String dTreatmentFees;
    private String dRenovationFees;
    private String dDiversFees;


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

    public String getRenovationFees() {
        return dRenovationFees;
    }

    public void setRenovationFees(String dRenovationFees) {
        this.dRenovationFees = dRenovationFees;
    }

    public String getDiversFees() {
        return dDiversFees;
    }

    public void setDiversFees(String dDiversFees) {
        this.dDiversFees = dDiversFees;
    }

    @Override
    public String toString() {
        return "HouseLanguage{" +
                "nHouseId='" + nHouseId + '\'' +
                ", szState='" + szState + '\'' +
                ", szCity='" + szCity + '\'' +
                ", szStreet='" + szStreet + '\'' +
                ", nHouseNumber='" + nHouseNumber + '\'' +
                ", EHouseKind='" + EHouseKind + '\'' +
                ", nNumberOfRooms='" + nNumberOfRooms + '\'' +
                ", nNumberOfLivingRooms='" + nNumberOfLivingRooms + '\'' +
                ", nNumberOfKitchens='" + nNumberOfKitchens + '\'' +
                ", nNumberOfBedrooms='" + nNumberOfBedrooms + '\'' +
                ", nNumberOfBathrooms='" + nNumberOfBathrooms + '\'' +
                ", eLocationKind='" + eLocationKind + '\'' +
                ", szComments='" + szComments + '\'' +
                ", dPurchasePrice='" + dPurchasePrice + '\'' +
                ", dTreatmentFees='" + dTreatmentFees + '\'' +
                ", dRenovationFees='" + dRenovationFees + '\'' +
                ", dDiversFees='" + dDiversFees + '\'' +
                '}';
    }

    public String toJson() {
        return "{ \"HouseLanguage\":[ {" +
                "\"HouseId\":\"" + nHouseId + "\"" +
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
                ",\"Comments\":\"" + szComments + "\"" +
                ",\"PurchasePrice\":\"" + dPurchasePrice + "\"" +
                ",\"TreatmentFees\":\"" + dTreatmentFees + "\"" +
                ",\"RenovationFees\":\"" + dRenovationFees + "\"" +
                ",\"DiversFees\":\"" + dDiversFees + "\"" +
                "} ]}";
    }
}
