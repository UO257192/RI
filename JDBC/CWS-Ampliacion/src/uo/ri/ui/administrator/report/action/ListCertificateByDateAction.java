package uo.ri.ui.administrator.report.action;

import java.util.Date;
import java.util.List;

import alb.util.console.Console;
import alb.util.date.Dates;
import alb.util.menu.Action;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.serviceLayer.training.CourseReportService;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;

public class ListCertificateByDateAction implements Action {

	@Override
	public void execute() throws Exception {
		Date date = Dates.fromString(Console.readString("Date"));
		CourseReportService rs = Factory.service.getCourseReportService();
		List<CertificateDto> rows = rs.findCertificateFromDate(date);
		rows.forEach( c -> Printer.printCertificateRow(c));
	}

}
