package base;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogFormato extends Formatter{
	
	@Override
	public String format(LogRecord record) {
	return "se ha seleccionado la opción: "+record.getMessage();
	}
	@Override
	 public String getHead(Handler h) {
	 return "COMIENZO DEL LOG\n";
	 }
	@Override
	 public String getTail(Handler h) {
	 return "\nFIN DEL LOG\n";
	 }

}
