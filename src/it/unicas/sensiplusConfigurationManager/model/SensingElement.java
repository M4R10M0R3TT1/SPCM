package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.*;

/**
 * Created by  on 21/11/2017.
 */
public class SensingElement {

    private StringProperty idSensingElement;
    private IntegerProperty rSense;
    private IntegerProperty inGain;
    private IntegerProperty outGain;
    private StringProperty contacts;
    private IntegerProperty frequency;
    private StringProperty harmonic;
    private IntegerProperty dcBias;
    private StringProperty modeVI;
    private StringProperty measureTechnique;
    private StringProperty measureType;
    private IntegerProperty filter;
    private StringProperty phaseShiftMode;
    private IntegerProperty phaseShift;
    private StringProperty iq;
    private IntegerProperty conversionRate;
    private StringProperty inPortADC;
    private IntegerProperty nData;
    private StringProperty measureUnit;
    private DoubleProperty rangeMin;
    private DoubleProperty rangeMax;
    private DoubleProperty defaultAlarmThreshold;
    private IntegerProperty multiplier;
    private StringProperty name;
    //parte family
    private StringProperty family_id;
    private StringProperty family_Name;
    private StringProperty port_Name;
    private BooleanProperty port_internal;


    //Default constructor
    public SensingElement(){

        this(null/*,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null*/);
    }

    public SensingElement(String idSensingElement, Integer rSense, Integer inGain, Integer outGain, String contacts,
                          Integer frequency, String harmonic, Integer dcBias, String modeVI, String measureTechnique,
                          String measureType, Integer filter, String phaseShiftMode, Integer phaseShift, String iq,
                          Integer conversionRate, String inPortADC, Integer nData, String measureUnit,Double rangeMin,
                          Double rangeMax, Double defaultAlarmThreshold, Integer multiplier, String name){

        this.idSensingElement=new SimpleStringProperty(idSensingElement);
        this.rSense= new SimpleIntegerProperty(rSense.intValue());
        this.inGain=new SimpleIntegerProperty(inGain.intValue());
        this.outGain=new SimpleIntegerProperty(outGain.intValue());
        this.contacts=new SimpleStringProperty(contacts);
        this.frequency=new SimpleIntegerProperty(frequency.intValue());
        this.harmonic=new SimpleStringProperty(harmonic);
        this.dcBias=new SimpleIntegerProperty(dcBias.intValue());
        this.modeVI=new SimpleStringProperty(modeVI);
        this.measureTechnique=new SimpleStringProperty(measureTechnique);
        this.measureType=new SimpleStringProperty(measureType);
        this.filter=new SimpleIntegerProperty(filter.intValue());
        this.phaseShiftMode=new SimpleStringProperty(phaseShiftMode);
        this.phaseShift=new SimpleIntegerProperty(phaseShift.intValue());
        this.iq=new SimpleStringProperty(iq);
        this.conversionRate=new SimpleIntegerProperty(conversionRate.intValue());
        this.inPortADC=new SimpleStringProperty(inPortADC);
        this.nData=new SimpleIntegerProperty(nData.intValue());
        this.measureUnit=new SimpleStringProperty(measureUnit);
        this.rangeMin=new SimpleDoubleProperty(rangeMin);
        this.rangeMax=new SimpleDoubleProperty(rangeMax);
        this.defaultAlarmThreshold=new SimpleDoubleProperty(defaultAlarmThreshold);
        this.multiplier=new SimpleIntegerProperty(multiplier.intValue());
        this.name=new SimpleStringProperty(name);
    }

    //Constructor for ReadDB
    public SensingElement(String idSensingElement){

        this.idSensingElement=new SimpleStringProperty(idSensingElement);
        this.rSense= null;
        this.inGain=null;
        this.outGain=null;
        this.contacts=new SimpleStringProperty("");
        this.frequency=null;
        this.harmonic=new SimpleStringProperty("");
        this.dcBias=null;
        this.modeVI=new SimpleStringProperty("");
        this.measureTechnique=new SimpleStringProperty("");
        this.measureType=new SimpleStringProperty("");
        this.filter=null;
        this.phaseShiftMode=new SimpleStringProperty("");
        this.phaseShift=null;
        this.iq=new SimpleStringProperty("");
        this.conversionRate=null;
        this.inPortADC=new SimpleStringProperty("");
        this.nData=null;
        this.measureUnit=new SimpleStringProperty("");
        this.rangeMin=null;
        this.rangeMax=null;
        this.defaultAlarmThreshold=null;
        this.multiplier=null;
        this.name=new SimpleStringProperty("");

    }

    //Constructor for FamilyConfig
    public SensingElement(String family_id, String family_Name, String port_Name, Boolean port_internal){
        this.family_id=new SimpleStringProperty(family_id);
        this.family_Name=new SimpleStringProperty(family_Name);
        this.port_Name=new SimpleStringProperty(port_Name);
        this.port_internal=new SimpleBooleanProperty(port_internal);
    }

    public String getIdSensingElement() {
        return idSensingElement.get();
    }

    public StringProperty idSensingElementProperty() {
        return idSensingElement;
    }

    public void setIdSensingElement(String idSensingElement) {
        this.idSensingElement.set(idSensingElement);
    }

    public Integer getrSense() {
        if (rSense == null){
            rSense = new SimpleIntegerProperty();
        }
        return rSense.get();
    }

    public void setrSense(Integer rSense) {
        if (this.rSense == null){
            this.rSense = new SimpleIntegerProperty();
        }
        this.rSense.set(rSense);
    }

    public Integer getInGain() {
        if (inGain == null){
            inGain = new SimpleIntegerProperty();
        }
        return inGain.get();
    }

    public void setInGain(Integer inGain) {
        if (this.inGain == null){
            this.inGain = new SimpleIntegerProperty();
        }
        this.inGain.set(inGain);
    }

    public Integer getOutGain() {
        if (outGain == null){
            outGain = new SimpleIntegerProperty();
        }
        return outGain.get();
    }

    public void setOutGain(Integer outGain) {
        if (this.outGain == null){
            this.outGain = new SimpleIntegerProperty();
        }
        this.outGain.set(outGain);
    }

    public String getContacts() {
        return contacts.get();
    }

    public StringProperty contactsProperty() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts.set(contacts);
    }

    public Integer getFrequency() {
        if (frequency == null){
            frequency = new SimpleIntegerProperty();
        }
        return frequency.get();
    }

    public void setFrequency(Integer frequency) {
        if (this.frequency == null){
            this.frequency = new SimpleIntegerProperty();
        }
        this.frequency.set(frequency);
    }

    public String getHarmonic() {
        return harmonic.get();
    }

    public StringProperty harmonicProperty() {
        return harmonic;
    }

    public void setHarmonic(String harmonic) {
        this.harmonic.set(harmonic);
    }

    public Integer getDcBias() {
        if (dcBias == null){
            dcBias = new SimpleIntegerProperty();
        }
        return dcBias.get();
    }

    public void setDcBias(Integer dcBias) {
        if (this.dcBias == null){
            this.dcBias = new SimpleIntegerProperty();
        }
        this.dcBias.set(dcBias);
    }

    public String getModeVI() {
        return modeVI.get();
    }

    public StringProperty modeVIProperty() {
        return modeVI;
    }

    public void setModeVI(String modeVI) {
        this.modeVI.set(modeVI);
    }

    public String getmeasureTechnique() {
        return measureTechnique.get();
    }

    public StringProperty measureTechniqueProperty() {
        return measureTechnique;
    }

    public void setmeasureTechnique(String measureTechnique) {
        this.measureTechnique.set(measureTechnique);
    }

    public String getMeasureType() {
        return measureType.get();
    }

    public StringProperty measureTypeProperty() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType.set(measureType);
    }

    public Integer getFilter() {
        if (filter == null){
            filter = new SimpleIntegerProperty();
        }
        return filter.get();
    }

    public void setFilter(Integer filter) {
        if (this.filter == null){
            this.filter = new SimpleIntegerProperty();
        }
        this.filter.set(filter);
    }

    public String getPhaseShiftMode() {
        return phaseShiftMode.get();
    }

    public StringProperty phaseShiftModeProperty() {
        return phaseShiftMode;
    }

    public void setPhaseShiftMode(String phaseShiftMode) {
        this.phaseShiftMode.set(phaseShiftMode);
    }

    public Integer getPhaseShift() {
        if (phaseShift == null){
            phaseShift = new SimpleIntegerProperty();
        }
        return phaseShift.get();
    }

    public void setPhaseShift(Integer phaseShift) {
        if (this.phaseShift == null){
            this.phaseShift = new SimpleIntegerProperty();
        }
        this.phaseShift.set(phaseShift);
    }

    public String getIq() {
        return iq.get();
    }

    public StringProperty iqProperty() {
        return iq;
    }

    public void setIq(String iq) {
        this.iq.set(iq);
    }

    public Integer getConversionRate() {
        if (conversionRate == null){
            conversionRate = new SimpleIntegerProperty();
        }
        return conversionRate.get();
    }

    public void setConversionRate(Integer conversionRate) {
        if (this.conversionRate == null){
            this.conversionRate = new SimpleIntegerProperty();
        }
        this.conversionRate.set(conversionRate);
    }

    public String getInPortADC() {
        return inPortADC.get();
    }

    public StringProperty inPortADCProperty() {
        return inPortADC;
    }

    public void setInPortADC(String inPortADC) {
        this.inPortADC.set(inPortADC);
    }

    public Integer getnData() {
        if (nData == null){
            nData = new SimpleIntegerProperty();
        }
        return nData.get();
    }

    public void setnData(Integer nData) {
        if (this.nData == null){
            this.nData = new SimpleIntegerProperty();
        }
        this.nData.set(nData);
    }

    public String getMeasureUnit() {
        return measureUnit.get();
    }

    public StringProperty measureUnitProperty() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit.set(measureUnit);
    }

    public double getRangeMin() {
        return rangeMin.get();
    }

    public DoubleProperty rangeMinProperty() {
        return rangeMin;
    }

    public void setRangeMin(double rangeMin) {
        this.rangeMin.set(rangeMin);
    }

    public double getRangeMax() {
        return rangeMax.get();
    }

    public DoubleProperty rangeMaxProperty() {
        return rangeMax;
    }

    public void setRangeMax(double rangeMax) {
        this.rangeMax.set(rangeMax);
    }

    public double getDefaultAlarmThreshold() {
        return defaultAlarmThreshold.get();
    }

    public DoubleProperty defaultAlarmThresholdProperty() {
        return defaultAlarmThreshold;
    }

    public void setDefaultAlarmThreshold(double defaultAlarmThreshold) {
        this.defaultAlarmThreshold.set(defaultAlarmThreshold);
    }

    public int getMultiplier() {
        return multiplier.get();
    }

    public IntegerProperty multiplierProperty() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier.set(multiplier);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFamily_id() {
        return family_id.get();
    }

    public StringProperty family_idProperty() {
        return family_id;
    }

    public void setFamily_id(String family_id) {
        this.family_id.set(family_id);
    }

    public String getFamily_Name() {
        return family_Name.get();
    }

    public StringProperty family_NameProperty() {
        return family_Name;
    }

    public void setFamily_Name(String family_Name) {
        this.family_Name.set(family_Name);
    }

    public String getPort_Name() {
        return port_Name.get();
    }

    public StringProperty port_NameProperty() {
        return port_Name;
    }

    public void setPort_Name(String port_Name) {
        this.port_Name.set(port_Name);
    }

    public boolean isPort_internal() {
        return port_internal.get();
    }

    public BooleanProperty port_internalProperty() {
        return port_internal;
    }

    public void setPort_internal(boolean port_internal) {
        this.port_internal.set(port_internal);
    }

    @Override
    public String toString(){
        return idSensingElement.getValue();
    }
}
