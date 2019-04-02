
package pe.worktime.model.service.writer;

/**
 * @author Alpha - FL3X SOFT SAC
 */
public abstract class AbstractWriterXML {

    protected static final String Version = "1.0";
    protected static final String Encoding = "utf-8";

    protected static final int TYPE_GET = 0;
    protected static final int TYPE_SET = 1;
    
    private String getHeader() {
        return "<?xml version=\"" + Version + "\" encoding=\"" + Encoding + "\"?>";
    }

    protected abstract String writeBodyXML();

    public String toXml() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getHeader());
        buffer.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        buffer.append("<soap:Body>");
        buffer.append(writeBodyXML());
        buffer.append("</soap:Body>");
        buffer.append("</soap:Envelope>");        
        return buffer.toString();
    }

    public String getAtribute(String name, String Value) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(" ");
        buffer.append(name);
        buffer.append("=");
        {
            buffer.append("\"");
            {
                buffer.append(Value);
            }
            buffer.append("\"");

        }
        return buffer.toString();
    }

    public String getAtribute(String name, int Value) {
        return getAtribute(name, String.valueOf(Value));
    }

    public String getAtribute(String name, double Value) {
        return getAtribute(name, String.valueOf(Value));
    }

}