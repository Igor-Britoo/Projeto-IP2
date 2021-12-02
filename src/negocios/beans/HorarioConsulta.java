package negocios.beans;

import java.time.LocalTime;

public enum HorarioConsulta {
	H1(LocalTime.of(7, 30)), H2(LocalTime.of(8, 0)),
	H3(LocalTime.of(8, 30)), H4(LocalTime.of(9, 0)),
	H5(LocalTime.of(9, 30)), H6(LocalTime.of(10, 0)),
	H7(LocalTime.of(10, 30)), H8(LocalTime.of(11, 0)),
	H9(LocalTime.of(11, 30)), H10(LocalTime.of(12, 0)),
	H11(LocalTime.of(12, 30)), H12(LocalTime.of(13, 0)),
	H13(LocalTime.of(13, 30)), H14(LocalTime.of(14, 0)),
	H15(LocalTime.of(14, 30)), H16(LocalTime.of(15, 0)),
	H17(LocalTime.of(15, 30)), H18(LocalTime.of(16, 0)),
	H19(LocalTime.of(16, 30)), H20(LocalTime.of(17, 0));
	
	private LocalTime horarioConsulta;

	HorarioConsulta(LocalTime horarioConsulta) {
		this.horarioConsulta = horarioConsulta;
	}

	public LocalTime getHorarioConsulta() {
		return this.horarioConsulta;
	}

	public void setHorarioConsulta(LocalTime horarioConsulta) {
		this.horarioConsulta = horarioConsulta;
	}
	
}
