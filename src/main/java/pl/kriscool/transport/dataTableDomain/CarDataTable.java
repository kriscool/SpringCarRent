package pl.kriscool.transport.dataTableDomain;

import pl.kriscool.transport.domain.Car;

public class CarDataTable {
	public CarDataTable() {}
	
	public CarDataTable(Car car,String buttons) {
		this.marka=car.getMarka();
		this.tablica=car.getTablica();
		this.buttons=buttons;
	}
	private String marka;
	private String tablica;
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getTablica() {
		return tablica;
	}
	public void setTablica(String tablica) {
		this.tablica = tablica;
	}
	public String getButtons() {
		return buttons;
	}
	public void setButtons(String buttons) {
		this.buttons = buttons;
	}
	private String buttons;

}
