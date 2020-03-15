package simulator.model;

public enum VehicleStatus {
	PENDING, TRAVELING, WAITING, ARRIVED;
}

/*
 * 
* PENDING - no ha entrado en la primera carretera de su itinerario
* TRAVELING - circulando por la carretera
* WAITING - esperando en un cruce
* ARRIVED - ha completado su itinerario
*  
*/