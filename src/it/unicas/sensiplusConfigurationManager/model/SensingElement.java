package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
    private StringProperty measureTecnique;
    private StringProperty measureType;
    private IntegerProperty filter;
    private StringProperty phaseShiftMode;
    private IntegerProperty phaseShift;
    private StringProperty iq;
    private IntegerProperty conversionRate;
    private StringProperty inPortADC;
    private IntegerProperty nData;
    private StringProperty measureUnit;

    //Default constructor
    public SensingElement(){

        this(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
    }

    public SensingElement(String idSensingElement, Integer rSense, Integer inGain, Integer outGain, String contacts, Integer frequency, String harmonic, Integer dcBias, String modeVI, String measureTecnique, String measureType, Integer filter, String phaseShiftMode, Integer phaseShift, String iq, Integer conversionRate, String inPortADC, Integer nData, String measureUnit){
        this.idSensingElement=new SimpleStringProperty(idSensingElement);
        this.rSense=new SimpleIntegerProperty(rSense);
        this.inGain=new SimpleIntegerProperty(inGain);
        this.outGain=new SimpleIntegerProperty(outGain);
        this.contacts=new SimpleStringProperty(contacts);
        this.frequency=new SimpleIntegerProperty(frequency);
        this.harmonic=new SimpleStringProperty(harmonic);
        this.dcBias=new SimpleIntegerProperty(dcBias);
        this.modeVI=new SimpleStringProperty(modeVI);
        this.measureTecnique=new SimpleStringProperty(measureTecnique);
        this.measureType=new SimpleStringProperty(measureType);
        this.filter=new SimpleIntegerProperty(filter);
        this.phaseShiftMode=new SimpleStringProperty(phaseShiftMode);
        this.phaseShift=new SimpleIntegerProperty(phaseShift);
        this.iq=new SimpleStringProperty(iq);
        this.conversionRate=new SimpleIntegerProperty(conversionRate);
        this.inPortADC=new SimpleStringProperty(inPortADC);
        this.nData=new SimpleIntegerProperty(nData);
        this.measureUnit=new SimpleStringProperty(measureUnit);
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

    public int getrSense() {
        return rSense.get();
    }

    public IntegerProperty rSenseProperty() {
        return rSense;
    }

    public void setrSense(int rSense) {
        this.rSense.set(rSense);
    }

    public int getInGain() {
        return inGain.get();
    }

    public IntegerProperty inGainProperty() {
        return inGain;
    }

    public void setInGain(int inGain) {
        this.inGain.set(inGain);
    }

    public int getOutGain() {
        return outGain.get();
    }

    public IntegerProperty outGainProperty() {
        return outGain;
    }

    public void setOutGain(int outGain) {
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

    public int getFrequency() {
        return frequency.get();
    }

    public IntegerProperty frequencyProperty() {
        return frequency;
    }

    public void setFrequency(int frequency) {
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

    public int getDcBias() {
        return dcBias.get();
    }

    public IntegerProperty dcBiasProperty() {
        return dcBias;
    }

    public void setDcBias(int dcBias) {
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

    public String getMeasureTecnique() {
        return measureTecnique.get();
    }

    public StringProperty measureTecniqueProperty() {
        return measureTecnique;
    }

    public void setMeasureTecnique(String measureTecnique) {
        this.measureTecnique.set(measureTecnique);
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

    public int getFilter() {
        return filter.get();
    }

    public IntegerProperty filterProperty() {
        return filter;
    }

    public void setFilter(int filter) {
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

    public int getPhaseShift() {
        return phaseShift.get();
    }

    public IntegerProperty phaseShiftProperty() {
        return phaseShift;
    }

    public void setPhaseShift(int phaseShift) {
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

    public int getConversionRate() {
        return conversionRate.get();
    }

    public IntegerProperty conversionRateProperty() {
        return conversionRate;
    }

    public void setConversionRate(int conversionRate) {
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

    public int getnData() {
        return nData.get();
    }

    public IntegerProperty nDataProperty() {
        return nData;
    }

    public void setnData(int nData) {
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

    @Override
    public String toString(){
        return idSensingElement.getValue();
    }
}
