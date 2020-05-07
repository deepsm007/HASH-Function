/**
 * 
 */
package com.deepsm007;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

/**
 * @author Deep Mistry
 *
 *         This class computes the hashes using MD5 and SHA-1 algorithms and the
 *         output result is converted into Base64 and Hex string.
 */
public class ComputeHashes extends HttpServlet {

	private static final long serialVersionUID = 566034731890609294L;
	private static final String MD5 = "MD5"; 
	private static final String SHA = "SHA-1";
	private static String input = "";
	private static String result;

	/**
	 * This method performs the POST request and redirects it to GET method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * This method performs the GET request. The input from the browser is stored in
	 * input string variable. The result variable calls for the methods to generate
	 * the HEX string and BASE64 for the MD5 and SHA-1 algorithm. Then the request
	 * is forwarded back to the same page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		input = request.getParameter("input");
		try {
			result = "Hashes of the string \"" + input + "\": \n SHA-1 (Hex): "
					+ getHexString(generateHashes(SHA, input)) + "\n SHA-1 (Base 64): "
					+ getBase64String(generateHashes(SHA, input)) + "\n MD5 (Hex): "
					+ getHexString(generateHashes(MD5, input)) + "\n MD5 (Base 64): "
					+ getBase64String(generateHashes(MD5, input));
			request.setAttribute("output", result.toString());
			request.getRequestDispatcher("/").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method generates the HEX string by converting the byte array to integer.
	 * 
	 * @param b
	 * @return String
	 * @throws Exception
	 */
	public static String getHexString(byte[] b) throws Exception {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	/**
	 * This method generates the BASE64 string by using the BASE64 Encoder from
	 * sun.misc.BASE64Encoder class.
	 * 
	 * @param b
	 * @return
	 * @throws Exception
	 */
	public static String getBase64String(byte[] b) throws Exception {
		return new BASE64Encoder().encode(b);
	}

	/**
	 * This method generate HASHES by using Message Digest and passing instances of
	 * MD5 and SHA-1.
	 * 
	 * @param instance
	 * @param input
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] generateHashes(String instance, String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(instance); // SHA-1 or MD5
		byte[] hashInBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
		return hashInBytes;
	}

}
