package util;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	// Returns the ExtentReports instance, creating it if necessary
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance("test-output/extent.html");

		return extent;
	}

	// Creates a new ExtentReports instance with the given file name
	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		// Configures the HTML report's theme, title, encoding, and name
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}
	// Update the given HTML report file with new CSS and JS nodes as the generated nodes are now blocked in egypt and if not replaced html file generated is corrupted.
	
	public static void updateReportFile(String file_Path) throws IOException {

		File htmlFile = new File(file_Path);

		Document htmlDoc = Jsoup.parse(htmlFile, "UTF-8");

		// Get head (which includes the CSS new link) and body (which includes the JS
		// new link) nodes
		Element headNode = htmlDoc.selectFirst("html head");
		Element bodyNode = htmlDoc.selectFirst("html body");

		// Set new CSS and JS nodes to be added
		Element newCSSNode = Jsoup.parse(
				"<link href='https://www.extentreports.com/resx/v3html/css/extent.css' type='text/css' rel='stylesheet' />")
				.selectFirst("link");
		Element newScriptNode = Jsoup.parse(
				"<script src='https://www.extentreports.com/resx/v3html/js/extent.js' type='text/javascript'></script>")
				.selectFirst("script");

		// Get the old CSS and JS nodes that need to be replaced with new ones
		Element cssNode = htmlDoc.selectFirst("link[href*=https://cdn.jsdelivr]");
		Element jsNode = htmlDoc.selectFirst("script[src*=https://cdn.rawgit]");

		// Remove the nodes
		if (cssNode != null) {
			cssNode.remove();
		}
		if (jsNode != null) {
			jsNode.remove();
		}

		// Add the new nodes
		headNode.appendChild(newCSSNode);
		bodyNode.appendChild(newScriptNode);

		// Saving the file
		String htmlString = htmlDoc.outerHtml();
		FileUtils.writeStringToFile(htmlFile, htmlString, "UTF-8");

	}




}