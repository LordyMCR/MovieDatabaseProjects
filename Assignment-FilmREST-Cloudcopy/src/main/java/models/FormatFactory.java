package models;
/**
 * <h1>FormatFactory factory class</h1>
 * <p>The FormatFactory class is a factory that creates instances of classes that implements the
 * Formats interface. It has a method, getFormat, that takes in a string parameter and returns the
 * appropriate class based on the string value.</p>
 */
public class FormatFactory {
	/**
	 * Returns an instance of the appropriate class that implements the Formats interface based on
	 * the formatType parameter
	 * @param formatType a string representing the desired format (e.g. "application/json",
	 * "application/xml", "text/plain")
	 * @return an instance of the corresponding class (e.g. JSON, XML, PlainText) if the formatType
	 * matches one of the expected values, otherwise returns null
	 */
	public Formats getFormat(String formatType) {
		if(formatType == null) {
			return null;
		}
		if(formatType.equalsIgnoreCase("application/json")) {
			return new JSON();
		}
		else if(formatType.equalsIgnoreCase("application/xml")) {
			return new XML();
		}
		else if(formatType.equalsIgnoreCase("text/plain")) {
			return new PlainText();
		}
		return null;
	}
}