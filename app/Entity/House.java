package Entity;

/**
 * Created by jacky on 18/11/2016.
 */
public class House {
    private int nHouseId;
    private String szState;
    private String szCity;
    private String szStreet;
    private int nHouseNumber;
    private EHouseKind EHouseKind;
    private int nNumberOfRooms;
    private int nNumberOfLivingRooms;
    private int nNumberOfKitchens;
    private int nNumberOfBedrooms;
    private int nNumberOfBathrooms;
    private ELocationKind eLocationKind;
    private String szComments;
    private double dPurchasePrice;
    private double dTreatmentFees;
    private double dRenovationFees;
    private double dDiversFees;

    public House(int nHouseId, String szState, String szCity, String szStreet, int nHouseNumber, Entity.EHouseKind EHouseKind, int nNumberOfRooms, int nNumberOfLivingRooms, int nNumberOfKitchens, int nNumberOfBedrooms, int numberOfBathrooms, ELocationKind locationKind, String szComments, double dPurchasePrice, double dTreatmentFees, double dRenovationFees, double dDiversFees) {
        this.nHouseId = nHouseId;
        this.szState = szState;
        this.szCity = szCity;
        this.szStreet = szStreet;
        this.nHouseNumber = nHouseNumber;
        this.EHouseKind = EHouseKind;
        this.nNumberOfRooms = nNumberOfRooms;
        this.nNumberOfLivingRooms = nNumberOfLivingRooms;
        this.nNumberOfKitchens = nNumberOfKitchens;
        this.nNumberOfBedrooms = nNumberOfBedrooms;
        this.nNumberOfBathrooms = numberOfBathrooms;
        this.eLocationKind = locationKind;
        this.szComments = szComments;
        this.dPurchasePrice = dPurchasePrice;
        this.dTreatmentFees = dTreatmentFees;
        this.dRenovationFees = dRenovationFees;
        this.dDiversFees = dDiversFees;
    }

    /***
     * Constractor for house details
     * @param nHouseId
     * @param szState
     * @param szCity
     * @param szStreet
     * @param nHouseNumber
     * @param EHouseKind
     * @param nNumberOfRooms
     * @param nNumberOfLivingRooms
     * @param nNumberOfKitchens
     * @param nNumberOfBedrooms
     * @param numberOfBathrooms
     * @param locationKind
     * @param szComments
     */
    public House(int nHouseId, String szState, String szCity, String szStreet, int nHouseNumber, Entity.EHouseKind EHouseKind, int nNumberOfRooms, int nNumberOfLivingRooms, int nNumberOfKitchens, int nNumberOfBedrooms, int numberOfBathrooms, ELocationKind locationKind, String szComments) {
        this.nHouseId = nHouseId;
        this.szState = szState;
        this.szCity = szCity;
        this.szStreet = szStreet;
        this.nHouseNumber = nHouseNumber;
        this.EHouseKind = EHouseKind;
        this.nNumberOfRooms = nNumberOfRooms;
        this.nNumberOfLivingRooms = nNumberOfLivingRooms;
        this.nNumberOfKitchens = nNumberOfKitchens;
        this.nNumberOfBedrooms = nNumberOfBedrooms;
        this.nNumberOfBathrooms = numberOfBathrooms;
        this.eLocationKind = locationKind;
        this.szComments = szComments;
    }

    public House() {
    }

    public int getHouseId() {
        return nHouseId;
    }

    public void setHouseId(int nHouseId) {
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

    public int getHouseNumber() {
        return nHouseNumber;
    }

    public void setHouseNumber(int nHouseNumber) {
        this.nHouseNumber = nHouseNumber;
    }

    public Entity.EHouseKind getHouseKind() {
        return EHouseKind;
    }

    public void setHouseKind(EHouseKind ehouseKind) {
        this.EHouseKind = EHouseKind;
    }

    public int getNumberOfRooms() {
        return nNumberOfRooms;
    }

    public void setNumberOfRooms(int nNumberOfRooms) {
        this.nNumberOfRooms = nNumberOfRooms;
    }

    public int getNumberOfLivingRooms() {
        return nNumberOfLivingRooms;
    }

    public void setNumberOfLivingRooms(int nNumberOfLivingRooms) {
        this.nNumberOfLivingRooms = nNumberOfLivingRooms;
    }

    public int getNumberOfKitchens() {
        return nNumberOfKitchens;
    }

    public void setNumberOfKitchens(int nNumberOfKitchens) {
        this.nNumberOfKitchens = nNumberOfKitchens;
    }

    public int getNumberOfBedrooms() {
        return nNumberOfBedrooms;
    }

    public void setNumberOfBedrooms(int nNumberOfBedrooms) {
        this.nNumberOfBedrooms = nNumberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return nNumberOfBathrooms;
    }

    public void setNumberOfBathrooms(int nNumberOfBathrooms) {
        this.nNumberOfBathrooms = nNumberOfBathrooms;
    }

    public ELocationKind getLocationKind() {
        return eLocationKind;
    }

    public void setLocationKind(ELocationKind eLocationKind) {
        this.eLocationKind = eLocationKind;
    }

    public String getComments() {
        return szComments;
    }

    public void setComments(String szComments) {
        this.szComments = szComments;
    }

    public double getPurchasePrice() {
        return dPurchasePrice;
    }

    public void setPurchasePrice(double dPurchasePrice) {
        this.dPurchasePrice = dPurchasePrice;
    }

    public double getTreatmentFees() {
        return dTreatmentFees;
    }

    public void setTreatmentFees(double dTreatmentFees) {
        this.dTreatmentFees = dTreatmentFees;
    }

    public double getRenovationFees() {
        return dRenovationFees;
    }

    public void setRenovationFees(double dRenovationFees) {
        this.dRenovationFees = dRenovationFees;
    }

    public double getDiversFees() {
        return dDiversFees;
    }

    public void setDiversFees(double dDiversFees) {
        this.dDiversFees = dDiversFees;
    }
}
