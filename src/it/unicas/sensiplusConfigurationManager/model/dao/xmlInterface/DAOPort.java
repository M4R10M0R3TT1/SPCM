package it.unicas.sensiplusConfigurationManager.model.dao.xmlInterface;

import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLPort;

import java.util.List;

public interface DAOPort<T> {
    List<XMLPort> selectPort(int a);
}
