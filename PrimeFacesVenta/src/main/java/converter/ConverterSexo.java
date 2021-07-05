package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converterSexo") //Seria su apodo para luego llamar en el XHTML
public class ConverterSexo implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	String sexo = "";
		
		if (value != null) {
			sexo = (String) value;
			switch (sexo) {
			case "M": {
				sexo = "MASCULINO";
				break;
			}
			case "F":{
				sexo = "FEMENINO";
				break;
			}
			default:
				throw new IllegalArgumentException("Error en el switch: sexo:" + value.toString());
			}
		}
		return sexo;
	}
}
