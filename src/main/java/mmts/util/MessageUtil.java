package mmts.util;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.application.FacesMessage.SEVERITY_WARN;

import javax.faces.application.FacesMessage.Severity;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service; 

@Named
@Singleton
@Lazy(false)
public class MessageUtil {
	private static MessageUtil instance;

	public static MessageUtil getInstance() {
		return instance;
	}

	public static String toCssFriendly(Severity severity) {
		if (severity.equals(SEVERITY_INFO)) {
			return "info";
		} else if (severity.equals(SEVERITY_WARN)) {
			return "warn";
		} else if (severity.equals(SEVERITY_ERROR)) {
			return "error";
		} else if (severity.equals(SEVERITY_FATAL)) {
			return "fatal";
		}

		throw new IllegalStateException("Unexpected message severity: " + severity.toString());
	}

	// -- info
	public void info(String summaryKey, Object... args) {
		addFacesMessageUsingKey(SEVERITY_INFO, summaryKey, args);
	}

	// -- error
	public void error(String summaryKey, Object... args) {
		addFacesMessageUsingKey(SEVERITY_ERROR, summaryKey, args);
	}

	private void addFacesMessageUsingKey(Severity severity, String summaryKey, Object arg) {
		addFacesMessageUsingKey(severity, summaryKey, new Object[] { arg });
	}
}
